package main.screen.central;


import io.jmix.ui.Fragments;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.*;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import main.entity.bridge.Bridges;
import main.entity.bridge.Modules;
import main.entity.central.AdditionalConceptRecord;
import main.entity.central.Attachment;
import main.entity.central.AttachmentCollection;
import main.entity.central.Document;
import main.screen.bridge.AllocationFragment;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

@UiController("Document.edit")
@UiDescriptor("document-edit.xml")
@EditedEntityContainer("documentDc")
public class DocumentEdit extends StandardEditor<Document> {

    @Autowired
    private GroupTable<AdditionalConceptRecord> tblAdditionalConcepts;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private DataContext dataContext;
    @Autowired
    private InstanceContainer<Document> documentDc;
    @Autowired
    private Fragment attachmentCollectionFragment;
    @Autowired
    private BrowserFrame browserPreview;
    @Autowired
    private Bridges bridges;
    @Autowired
    private VBoxLayout vbxBridgedAllocations;
    @Autowired
    private Fragments fragments;

    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        if (bridges.isModuleActive(Modules.PROJECTS)){
            AllocationFragment af = fragments.create(this, AllocationFragment.class);
            vbxBridgedAllocations.add(af.getFragment());
        }
    }




    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if (documentDc.getItem().getAttachmentCollection()==null){
            AttachmentCollection ac = dataContext.create(AttachmentCollection.class);
            ac.setName("Document");
            documentDc.getItem().setAttachmentCollection(ac);
        }
        AttachmentCollectionFragment acfr = (AttachmentCollectionFragment) attachmentCollectionFragment.getFrameOwner();
        GroupTable<Attachment> attachmentsTable = acfr.getAttachmentsTable();
        attachmentsTable.addSelectionListener(e->{
            ArrayList<Attachment> al = new ArrayList(e.getSelected());
            Attachment att = al.get(0);
            browserPreview.setSource(StreamResource.class)
                    .setStreamSupplier(() -> new ByteArrayInputStream(att.getSerial()))
                    .setMimeType("application/pdf");
        });



    }



    @Subscribe("tblAdditionalConcepts.createAC")
    public void onTblAdditionalConceptsCreateAC(Action.ActionPerformedEvent event) {
        screenBuilders.editor(AdditionalConceptRecord.class, this).newEntity()
                .withOpenMode(OpenMode.DIALOG)
                .withListComponent(tblAdditionalConcepts)
                .withParentDataContext(dataContext)
                .build().show();
    }

    @Install(to = "tblAdditionalConcepts.editAC", subject = "enabledRule")
    private boolean tblAdditionalConceptsEditACEnabledRule() {
        return tblAdditionalConcepts.getSingleSelected()!=null;
    }

    @Subscribe("tblAdditionalConcepts.editAC")
    public void onTblAdditionalConceptsEditAC(Action.ActionPerformedEvent event) {
        screenBuilders.editor(AdditionalConceptRecord.class, this).editEntity(tblAdditionalConcepts.getSingleSelected())
                .withOpenMode(OpenMode.DIALOG)
                .withListComponent(tblAdditionalConcepts)
                .withParentDataContext(dataContext)
                .build().show();
    }


}