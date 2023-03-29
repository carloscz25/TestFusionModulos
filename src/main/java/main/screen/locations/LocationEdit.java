package main.screen.locations;

import io.jmix.core.DataManager;
import io.jmix.core.EntityStates;
import io.jmix.core.LoadContext;
import io.jmix.core.Messages;
import io.jmix.ui.Dialogs;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.action.DialogAction;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import main.entity.central.Address;
import main.entity.central.AttachmentCollection;
import main.entity.locations.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@UiController("Location.edit")
@UiDescriptor("location-edit.xml")
@EditedEntityContainer("locationDc")
public class LocationEdit extends StandardEditor<Location> {
    @Autowired
    private EntityStates entityStates;
    @Autowired
    private InstanceContainer<Location> locationDc;
    @Autowired
    private DataContext dataContext;
    @Autowired
    private EntityPicker<Owner> ownerField;
    @Autowired
    private TextField landRegistryInformationField;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private GroupTable<Unit> tblUnits;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private InstancePropertyContainer<AttachmentCollection> attachmentCollectionDc;
    @Autowired
    private Dialogs dialogs;
    @Autowired
    private Messages messages;
    @Autowired
    private SingleSelectList<CoefficientType> tblCoefficientTypes;
    @Autowired
    private CollectionContainer<Coefficient> coefficientsDc;
    @Autowired
    private CollectionLoader<Coefficient> coefficientsDl;
    @Autowired
    private Table<Coefficient> tblCoefficients;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {

        if (entityStates.isNew(locationDc.getItem())){
            Address addr = dataContext.create(Address.class);
            this.locationDc.getItem().setAddress(addr);

        }
        if (locationDc.getItem().getVerticalProperty()==null){
            locationDc.getItem().setVerticalProperty(false);

        }
        if (locationDc.getItem().getAttachmentCollection()==null){
            AttachmentCollection ac = dataContext.create(AttachmentCollection.class);
            ac.setName("Collection 1");
            attachmentCollectionDc.setItem(ac);
            locationDc.getItem().setAttachmentCollection(ac);
        }
        ownerField.setEnabled(locationDc.getItem().getVerticalProperty());
        landRegistryInformationField.setEnabled(locationDc.getItem().getVerticalProperty());
    }

    @Subscribe("verticalPropertyField")
    public void onVerticalPropertyFieldValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        ownerField.setEnabled(event.getValue());
        landRegistryInformationField.setEnabled(event.getValue());
    }

    @Subscribe("tblUnits.createUnit")
    public void onTblUnitsCreateUnit(Action.ActionPerformedEvent event) {
        screenBuilders.editor(Unit.class, this).newEntity()
                .withOpenMode(OpenMode.NEW_TAB)
                .withInitializer(e->{
                    e.setLocation(this.locationDc.getItem());
                })
                .withListComponent(tblUnits)
                .withParentDataContext(dataContext)
                .build().show();
    }

    @Install(to = "tblUnits.editUnit", subject = "enabledRule")
    private boolean tblUnitsEditUnitEnabledRule() {
        return tblUnits.getSingleSelected()!=null;
    }

    @Subscribe("tblUnits.editUnit")
    public void onTblUnitsEditUnit(Action.ActionPerformedEvent event) {

        screenBuilders.editor(Unit.class, this).editEntity(tblUnits.getSingleSelected())
                .withOpenMode(OpenMode.NEW_TAB)
                .withListComponent(tblUnits)
                .withParentDataContext(dataContext)
                .build().show();
    }

    @Install(to = "coefficientTypesOptDl", target = Target.DATA_LOADER)
    private List<CoefficientType> coefficientTypesOptDlLoadDelegate(LoadContext<CoefficientType> loadContext) {
        List<CoefficientType> cctt = dataManager.load(CoefficientType.class)
                .query("select ct from CoefficientType ct").list();
        return cctt;
    }

    @Install(to = "coefficientsDl", target = Target.DATA_LOADER)
    private List<Coefficient> coefficientsDlLoadDelegate(LoadContext<Coefficient> loadContext) {
        CoefficientType ct = tblCoefficientTypes.getValue();
        if (ct!=null){
            List<Coefficient> cc = dataManager.load(Coefficient.class).query("select c from Coefficient c where c.location.id = :lid and c.coefficientType.id = :ctid")
                    .parameter("lid", locationDc.getItem().getId())
                    .parameter("ctid", ct.getId())
                    .list();
            return cc;
        }
        return new ArrayList<>();
    }

    @Subscribe("tblCoefficientTypes")
    public void onTblCoefficientTypesValueChange(HasValue.ValueChangeEvent<CoefficientType> event) {
        coefficientsDl.load();
    }



    @Subscribe("rstCoefficients")
    public void onRstCoefficientsClick(Button.ClickEvent event) {
        dialogs.createOptionDialog().withCaption(messages.getMessage("confirmationRequired"))
                .withMessage(messages.getMessage("confirmationRequiredDeletionCoefficients"))
                .withActions(
                        new DialogAction(DialogAction.Type.YES).withHandler(e->{
                            CoefficientType ct = tblCoefficientTypes.getValue();
                            resetCoefficientsForSelectedType(ct);
                        }),
                        new DialogAction(DialogAction.Type.NO).withHandler(e->{
                            //do nothing
                        })
                )
                .show();

    }

    private void resetCoefficientsForSelectedType(CoefficientType ct){

        /*
        Deleteing previously existing coefficients
         */

        List<Coefficient> cc = new ArrayList<>();


        for (int i = 0; i < locationDc.getItem().getUnits().size(); i++) {
            Unit u = locationDc.getItem().getUnits().get(i);
            List<Coefficient> l = u.getCoefficients();
            if (l!=null) {
                for (int j = 0; j < l.size(); j++) {
                    Coefficient c = l.get(j);
                    if (c.getCoefficientType().getId().compareTo(ct.getId()) == 0) {
                        cc.add(c);
                    }
                }
            }
        }
        for (int i = 0; i < cc.size(); i++) {
            Coefficient c = cc.get(i);
            c.getUnit().getCoefficients().remove(c);
            c.setUnit(null);

            dataManager.remove(c);
            coefficientsDc.getMutableItems().remove(c);
        }

        /*
        Creating new coefficients and assigning
         */

        for (int i = 0; i < locationDc.getItem().getUnits().size(); i++) {
            Unit u = locationDc.getItem().getUnits().get(i);
            Coefficient c = dataContext.create(Coefficient.class);
            c.setUnit(u);
            c.setLocation(u.getLocation());
            c.setCoefficientType(ct);
            c.setValue(1.0);
            if (u.getCoefficients()==null){
                u.setCoefficients(new ArrayList());
            }
            u.getCoefficients().add(c);
            coefficientsDc.getMutableItems().add(c);
        }

    }

    @Subscribe("tblCoefficients.edtCoefficientAction")
    public void onTblCoefficientsEdtCoefficientAction(Action.ActionPerformedEvent event) {
        screenBuilders.editor(Coefficient.class, this).editEntity(tblCoefficients.getSingleSelected())
                .withOpenMode(OpenMode.DIALOG)
                .build().show();
    }

    @Install(to = "tblCoefficients.edtCoefficientAction", subject = "enabledRule")
    private boolean tblCoefficientsEdtCoefficientActionEnabledRule() {
        return tblCoefficients.getSingleSelected()!=null;
    }

















}