package main.screen.locations;

import io.jmix.ui.screen.*;
import main.entity.locations.CoefficientType;

@UiController("CoefficientType.edit")
@UiDescriptor("coefficient-type-edit.xml")
@EditedEntityContainer("coefficientTypeDc")
public class CoefficientTypeEdit extends StandardEditor<CoefficientType> {
}