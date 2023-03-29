package main.screen.locations;

import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.Table;
import io.jmix.ui.component.TextField;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.model.InstancePropertyContainer;
import io.jmix.ui.screen.*;
import main.entity.central.Address;
import main.entity.central.AttachmentCollection;
import main.entity.locations.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Unit.edit")
@UiDescriptor("unit-edit.xml")
@EditedEntityContainer("unitDc")
public class UnitEdit extends StandardEditor<Unit> {
    @Autowired
    private InstanceContainer<Unit> unitDc;
    @Autowired
    private DataContext dataContext;
    @Autowired
    private InstancePropertyContainer<AttachmentCollection> attachmentCollectionDc;
    @Autowired
    private EntityPicker<Owner> ownerField;
    @Autowired
    private TextField<String> landRegistryInformationField;
    @Autowired
    private EntityPicker<Location> locationField;
    @Autowired
    private TextField<String> levelField;
    @Autowired
    private TextField<String> doorField;
    @Autowired
    private TextField<String> fullDescriptiveNameField;
    @Autowired
    private Table<OccupancyCertificate> tblOccupancyCertificates;
    @Autowired
    private Table<EnergyCertificate> tblEnergyCertificates;
    @Autowired
    private ScreenBuilders screenBuilders;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {

        if (this.isEntityModifiedInParentContext()){
            locationField.setEnabled(false);
        }
        if (unitDc.getItem().getAttachmentCollection()==null){
            AttachmentCollection ac = dataContext.create(AttachmentCollection.class);
            ac.setName("Collection 1");
            attachmentCollectionDc.setItem(ac);
            unitDc.getItem().setAttachmentCollection(ac);
        }
        if (unitDc.getItem().getLocation().getVerticalProperty()){
            ownerField.setEnabled(!unitDc.getItem().getLocation().getVerticalProperty());
            landRegistryInformationField.setEnabled(!unitDc.getItem().getLocation().getVerticalProperty());
        }

        updateFullDescriptiveName();

    }

    private void updateFullDescriptiveName(){
        String dn = unitDc.getItem().getLocation().getName() + " " ;
        dn += levelField.getValue() + " " + doorField.getValue();
        fullDescriptiveNameField.setValue(dn);
    }

    @Subscribe(id = "unitDc", target = Target.DATA_CONTAINER)
    public void onUnitDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Unit> event) {
        this.updateFullDescriptiveName();
    }

    @Subscribe("tblOccupancyCertificates.createOC")
    public void onTblOccupancyCertificatesCreateOC(Action.ActionPerformedEvent event) {
        screenBuilders.editor(OccupancyCertificate.class, this).newEntity()
                .withOpenMode(OpenMode.DIALOG)
                .withInitializer(e->{e.setUnit(this.unitDc.getItem());})
                .withParentDataContext(dataContext)
                .withListComponent(tblOccupancyCertificates)
                .build().show();
    }

    @Install(to = "tblOccupancyCertificates.editOC", subject = "enabledRule")
    private boolean tblOccupancyCertificatesEditOCEnabledRule() {
        return tblOccupancyCertificates.getSingleSelected()!=null;
    }

    @Subscribe("tblOccupancyCertificates.editOC")
    public void onTblOccupancyCertificatesEditOC(Action.ActionPerformedEvent event) {
        screenBuilders.editor(OccupancyCertificate.class, this).editEntity(tblOccupancyCertificates.getSingleSelected())
                .withOpenMode(OpenMode.DIALOG)
                .withParentDataContext(dataContext)
                .withListComponent(tblOccupancyCertificates)
                .build().show();
    }

    @Subscribe("tblEnergyCertificates.createEC")
    public void onTblEnergyCertificatesCreateEC(Action.ActionPerformedEvent event) {
        screenBuilders.editor(EnergyCertificate.class, this).newEntity()
                .withOpenMode(OpenMode.DIALOG)
                .withInitializer(e->{e.setUnit(this.unitDc.getItem());})
                .withParentDataContext(dataContext)
                .withListComponent(tblEnergyCertificates)
                .build().show();
    }

    @Install(to = "tblEnergyCertificates.editEC", subject = "enabledRule")
    private boolean tblEnergyCertificatesEditECEnabledRule() {
        return tblEnergyCertificates.getSingleSelected()!=null;
    }

    @Subscribe("tblEnergyCertificates.editEC")
    public void onTblEnergyCertificatesEditEC(Action.ActionPerformedEvent event) {
        screenBuilders.editor(EnergyCertificate.class, this).editEntity(tblEnergyCertificates.getSingleSelected())
                .withOpenMode(OpenMode.DIALOG)
                .withParentDataContext(dataContext)
                .withListComponent(tblEnergyCertificates)
                .build().show();
    }







}