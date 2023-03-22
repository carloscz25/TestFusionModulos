package main.screen.central;

import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Frame;
import io.jmix.ui.component.Tree;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import main.entity.central.Attachment;
import main.entity.central.AttachmentCollection;
import org.eclipse.persistence.internal.oxm.XMLBinaryAttachmentHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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

    @Subscribe
    public void onAttach(AttachEvent event) {
        FrameOwner fr = event.getSource().getHostController();
        PersonEdit pe = (PersonEdit) fr;
        pe.addAfterShowListener(e->{
            AttachmentCollection ac = pe.getEditedEntity().getAttachmentCollection();
            List<AttachmentCollection> aacc = getCollectionsFromTopCollection(ac);
            attachmentCollectionsDc.getMutableItems().clear();
            attachmentCollectionsDc.getMutableItems().addAll(aacc);
        });

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
        AttachmentCollection ac = dataManager.create(AttachmentCollection.class);
        ac.setName("Collection " + String.valueOf(this.counter));
        attachmentCollectionsDc.getMutableItems().add(ac);
    }







}