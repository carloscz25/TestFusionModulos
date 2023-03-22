package main.screen.central;

import io.jmix.ui.screen.*;
import main.entity.central.IdentificationNumber;

@UiController("IdentificationNumber.edit")
@UiDescriptor("identification-number-edit.xml")
@EditedEntityContainer("identificationNumberDc")
public class IdentificationNumberEdit extends StandardEditor<IdentificationNumber> {
}