package main.screen.locations;

import io.jmix.ui.screen.*;
import main.entity.locations.Coefficient;

@UiController("Coefficient.edit")
@UiDescriptor("coefficient-edit.xml")
@EditedEntityContainer("coefficientDc")
public class CoefficientEdit extends StandardEditor<Coefficient> {
}