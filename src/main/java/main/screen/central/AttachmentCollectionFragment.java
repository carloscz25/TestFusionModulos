package main.screen.central;


import io.jmix.core.DataManager;
import io.jmix.core.FileRef;
import io.jmix.core.FileStorage;
import io.jmix.core.LoadContext;
import io.jmix.ui.Dialogs;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.DialogAction;
import io.jmix.ui.app.inputdialog.DialogActions;
import io.jmix.ui.app.inputdialog.InputParameter;
import io.jmix.ui.component.*;
import io.jmix.ui.component.inputdialog.InputDialogAction;
import io.jmix.ui.download.Downloader;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import io.jmix.ui.upload.TemporaryStorage;
import main.entity.central.Attachment;
import main.entity.central.AttachmentCollection;
import org.eclipse.persistence.internal.oxm.XMLBinaryAttachmentHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

@UiController("AttachmentCollectionFragment")
@UiDescriptor("attachment-collection-fragment.xml")
public class AttachmentCollectionFragment extends ScreenFragment {

    Integer counter = 1;

    @Autowired
    private CollectionLoader<Attachment> attachmentsDl;
    @Autowired
    private Tree<AttachmentCollection> treeAttachmentCollections;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private CollectionContainer<AttachmentCollection> attachmentCollectionsDc;
    @Autowired
    private FileMultiUploadField multiUploadFieldAttachmentCollections;
    @Autowired
    private TemporaryStorage temporaryStorage;
    @Autowired
    private Notifications notifications;
    @Autowired
    private FileStorage fileStorage;
    @Autowired
    private CollectionContainer<Attachment> attachmentsDc;
    @Autowired
    private InstanceContainer<AttachmentCollection> attachmentCollectionDc;
    @Autowired
    private Dialogs dialogs;
    @Autowired
    private Button btnRenameColleccion;
    @Autowired
    private GroupTable<Attachment> attachmentsTable;
    @Autowired
    Downloader downloader;
    @Autowired
    private DataContext dataContext;

    public GroupTable<Attachment> getAttachmentsTable(){
        return this.attachmentsTable;
    }

    private boolean nameExistsInTree(String name){
        AttachmentCollection ac = attachmentCollectionDc.getItem();
        if (ac.getName().compareTo(name)==0){
            return true;
        }
        //traversing all collections
        List<Iterator> its = new ArrayList<Iterator>();
        if (ac.getAttachmentCollections()!=null){
            Iterator<AttachmentCollection> i = ac.getAttachmentCollections().iterator();
            while(true) {
                while (i.hasNext()) {
                    AttachmentCollection ac_ = i.next();
                    if (ac_.getName().compareTo(name) == 0) {
                        return true;
                    } else {
                        if (ac_.getAttachmentCollections() != null) {
                            its.add(i);
                            i = ac_.getAttachmentCollections().iterator();
                        }
                    }
                }
                if (its.size()>0){
                    i = its.get(its.size()-1);
                    its.remove(i);
                }else{
                    break;
                }
            }
        }
        return false;

    }

    private String determineNewNameInTree(){
        Integer counter = 1;
        String name = "Collection " + String.valueOf(counter);
        while(nameExistsInTree(name)){
            counter++;
            name = "Collection " + String.valueOf(counter);
        }
        return name;
    }


    @Subscribe("treeAttachmentCollections")
    public void onTreeAttachmentCollectionsSelection1(Tree.SelectionEvent<AttachmentCollection> event) {
        if (event.isUserOriginated()){
            if (event.getSelected().size()==0){
                multiUploadFieldAttachmentCollections.setEnabled(false);
            }else{
                multiUploadFieldAttachmentCollections.setEnabled(true);
                AttachmentCollection ac = new ArrayList<AttachmentCollection>(event.getSelected()).get(0);
                attachmentsDc.getMutableItems().clear();
                if (ac.getAttachments()!=null) {
                    attachmentsDc.getMutableItems().addAll(ac.getAttachments());
                }

                btnRenameColleccion.setEnabled(true);
            }



        }
    }



    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        multiUploadFieldAttachmentCollections.setEnabled(false);
        btnRenameColleccion.setEnabled(false);

        multiUploadFieldAttachmentCollections.addQueueUploadCompleteListener(queueUploadCompleteEvent -> {
            for (Map.Entry<UUID, String> entry : multiUploadFieldAttachmentCollections.getUploadsMap().entrySet()) {
                UUID fileId = entry.getKey();
                String fileName = entry.getValue();
                FileRef fileRef = temporaryStorage.putFileIntoStorage(fileId, fileName);
                String storageFileName = fileRef.getFileName();
                InputStream is = fileStorage.openStream(fileRef);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int b = -1;

                try {
                    while ((b = is.read()) != -1) {
                        baos.write(b);
                    }
                }catch(Exception exc){

                }
                fileStorage.removeFile(fileRef);
                AttachmentCollection attc = treeAttachmentCollections.getSingleSelected();
                if (attc.getAttachments()==null){
                    attc.setAttachments(new ArrayList<Attachment>());
                }
                Attachment att = dataContext.create(Attachment.class);
                att.setAttachmentCollection(treeAttachmentCollections.getSingleSelected());
                attc.getAttachments().add(att);
                att.setExtension(storageFileName.substring(storageFileName.lastIndexOf(".")+1));
                att.setFileName(storageFileName);
                att.setName(att.getFileName());
                att.setSerial(baos.toByteArray());
                att.setSize(baos.size());
                attachmentsDc.getMutableItems().add(att);
            }
            notifications.create()
                    .withCaption("Uploaded files: " + multiUploadFieldAttachmentCollections.getUploadsMap().values())
                    .show();
            multiUploadFieldAttachmentCollections.clearUploads();
        });
        multiUploadFieldAttachmentCollections.addFileUploadErrorListener(queueFileUploadErrorEvent ->
                notifications.create()
                        .withCaption("File upload error")
                        .show());
    }



    @Subscribe
    public void onAttach(AttachEvent event) {
        FrameOwner fr = event.getSource().getHostController();
        if (fr instanceof PersonEdit) {
            PersonEdit pe = (PersonEdit) fr;
            pe.addAfterShowListener(e -> {
                AttachmentCollection ac = pe.getEditedEntity().getAttachmentCollection();
                List<AttachmentCollection> aacc = getCollectionsFromTopCollection(ac);
                attachmentCollectionsDc.getMutableItems().clear();
                attachmentCollectionsDc.getMutableItems().addAll(aacc);
            });
        }
        if (fr instanceof DocumentEdit) {
            DocumentEdit pe = (DocumentEdit) fr;
            pe.addAfterShowListener(e -> {
                AttachmentCollection ac = pe.getEditedEntity().getAttachmentCollection();
                List<AttachmentCollection> aacc = getCollectionsFromTopCollection(ac);
                attachmentCollectionsDc.getMutableItems().clear();
                attachmentCollectionsDc.getMutableItems().addAll(aacc);
            });
        }

    }

    private List<AttachmentCollection> getCollectionsFromTopCollection(AttachmentCollection ac){
        List<AttachmentCollection> l = new ArrayList<AttachmentCollection>();
        l.add(ac);
        //pending
        return l;
    }





    @Subscribe("treeAttachmentCollections")
    public void onTreeAttachmentCollectionsSelection(Tree.SelectionEvent<AttachmentCollection> event) {
        attachmentsDl.load();
    }

    @Install(to = "attachmentsDl", target = Target.DATA_LOADER)
    private List<Attachment> attachmentsDlLoadDelegate(LoadContext<Attachment> loadContext) {
        AttachmentCollection ach = treeAttachmentCollections.getSingleSelected();
        List<Attachment> attchs = dataManager.load(Attachment.class)
                .query("SELECT a from Attachment a WHERE a.attachmentCollection.id = :acid")
                .parameter("acid", ach.getId())
                .list();
        return attchs;
    }

    @Subscribe("btnNewCollection")
    public void onBtnNewCollectionClick(Button.ClickEvent event) {
        AttachmentCollection acparent = treeAttachmentCollections.getSingleSelected();
        AttachmentCollection ac = dataContext.create(AttachmentCollection.class);
        if (acparent!=null){
            ac.setAttachmentCollectionParent(acparent);
            if (acparent.getAttachmentCollections()==null){
                acparent.setAttachmentCollections(new ArrayList<AttachmentCollection>());
            }
            acparent.getAttachmentCollections().add(ac);
        }
        ac.setName(determineNewNameInTree());
        attachmentCollectionsDc.getMutableItems().add(ac);
    }



    @Subscribe("btnNewCollection")
    public void onBtnNewCollectionClick1(Button.ClickEvent event) {

    }

    @Subscribe("btnRenameColleccion")
    public void onBtnRenameColleccionClick(Button.ClickEvent event) {
        AttachmentCollection ac = treeAttachmentCollections.getSingleSelected();
        dialogs.createInputDialog(this)
                .withCaption("Enter new name for Attachment Collection")
                .withParameter(InputParameter.stringParameter("collectionName"))
                .withActions(
                        new InputDialogAction("ok").withCaption("Ok").withHandler(e->{
                            String cn = e.getInputDialog().getValue("collectionName");
                            ac.setName(cn);
                            e.getInputDialog().closeWithDefaultAction();
                        }),
                        new InputDialogAction("cancel").withCaption("Cancel").withHandler(e->{
                            //do nothing
                            e.getInputDialog().closeWithDefaultAction();
                        })

                ).build().show();


    }

    @Subscribe("btnDownload")
    public void onBtnDownloadClick(Button.ClickEvent event) {
        Attachment att = attachmentsTable.getSingleSelected();
        downloader.download(att.getSerial(), att.getFileName());
    }











}