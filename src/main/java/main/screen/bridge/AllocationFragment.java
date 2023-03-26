package main.screen.bridge;

import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.UiComponents;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.Table;
import io.jmix.ui.component.TextField;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import main.entity.bridge.Allocation;
import main.entity.bridge.BridgeBean;
import main.entity.bridge.CycleUnitBridge;
import main.entity.central.Document;
import main.entity.locations.Unit;
import main.entity.projects.Cycle;
import main.screen.central.DocumentEdit;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Persistence;

@UiController("AllocationFragment")
@UiDescriptor("allocation-fragment.xml")
public class AllocationFragment extends ScreenFragment {

    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private BridgeBean bridgeBean;
    @Autowired
    private Table<Allocation> tableAllocations;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private DataContext dataContext;

    Document document = null;

    @Subscribe
    public void onAttach(AttachEvent event) {
        DocumentEdit de = (DocumentEdit) event.getSource().getHostController();
        de.addAfterShowListener(e->{
            document = de.getEditedEntity();
        });

    }




    @Install(to = "tableAllocations.unitName", subject = "columnGenerator")
    private Component tableAllocationsUnitNameColumnGenerator(Allocation allocation) {
        TextField tf = uiComponents.create(TextField.NAME);
        Cycle c = allocation.getCycle();
        CycleUnitBridge cub = bridgeBean.get(c);
        Unit u = cub.getUnit();
        tf.setValue(u.getFullDescriptiveName());
        return tf;

    }

    @Install(to = "tableAllocations.editAllocation", subject = "enabledRule")
    private boolean tableAllocationsEditAllocationEnabledRule() {
        return tableAllocations.getSingleSelected()!=null;
    }

    @Subscribe("tableAllocations.editAllocation")
    public void onTableAllocationsEditAllocation(Action.ActionPerformedEvent event) {
        screenBuilders.editor(Allocation.class, this).editEntity(tableAllocations.getSingleSelected())
                .withListComponent(tableAllocations)
                .withOpenMode(OpenMode.DIALOG)
                .withParentDataContext(dataContext)
                .build().show();
    }

    @Subscribe("tableAllocations.createAllocation")
    public void onTableAllocationsCreateAllocation(Action.ActionPerformedEvent event) {
        screenBuilders.editor(Allocation.class, this).newEntity()
                .withListComponent(tableAllocations)
                .withOpenMode(OpenMode.DIALOG)
                .withParentDataContext(dataContext)
                .withInitializer(e->{
                    e.setAllocatableDocument(document);
                })
                .build().show();
    }






}