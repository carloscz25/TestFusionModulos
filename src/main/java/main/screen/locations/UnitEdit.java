package main.screen.locations;

import io.jmix.ui.screen.*;
import main.entity.locations.Unit;

@UiController("Unit.edit")
@UiDescriptor("unit-edit.xml")
@EditedEntityContainer("unitDc")
public class UnitEdit extends StandardEditor<Unit> {
}