package main.screen.bridge;

import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.screen.*;
import main.entity.bridge.Allocation;
import main.entity.central.Document;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Allocation.edit")
@UiDescriptor("allocation-edit.xml")
@EditedEntityContainer("allocationDc")
public class AllocationEdit extends StandardEditor<Allocation> {
    @Autowired
    private EntityPicker<Document> allocatableDocumentField;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if (this.isEntityModifiedInParentContext()){
            allocatableDocumentField.setEnabled(false);
        }
    }



}