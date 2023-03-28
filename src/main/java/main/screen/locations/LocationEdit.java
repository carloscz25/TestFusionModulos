package main.screen.locations;

import io.jmix.core.DataManager;
import io.jmix.core.EntityStates;
import io.jmix.core.LoadContext;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.TextField;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.model.InstancePropertyContainer;
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

    @Install(to = "coefficientTypesDl", target = Target.DATA_LOADER)
    private List<CoefficientType> coefficientTypesDlLoadDelegate(LoadContext<CoefficientType> loadContext) {
        return new ArrayList();
    }

    @Install(to = "coefficientTypesOptDl", target = Target.DATA_LOADER)
    private List<CoefficientType> coefficientTypesOptDlLoadDelegate(LoadContext<CoefficientType> loadContext) {
        List<CoefficientType> cctt = dataManager.load(CoefficientType.class)
                .query("select ct from CoefficientType ct").list();
        return cctt;
    }

    @Install(to = "coefficientsDl", target = Target.DATA_LOADER)
    private List<Coefficient> coefficientsDlLoadDelegate(LoadContext<Coefficient> loadContext) {
        return new ArrayList();
    }









}