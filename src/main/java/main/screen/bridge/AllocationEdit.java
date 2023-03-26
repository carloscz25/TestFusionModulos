package main.screen.bridge;

import io.jmix.ui.screen.*;
import main.entity.bridge.Allocation;

@UiController("Allocation.edit")
@UiDescriptor("allocation-edit.xml")
@EditedEntityContainer("allocationDc")
public class AllocationEdit extends StandardEditor<Allocation> {
}