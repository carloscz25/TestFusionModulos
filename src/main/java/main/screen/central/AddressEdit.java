package main.screen.central;

import io.jmix.ui.screen.*;
import main.entity.central.Address;

@UiController("Address.edit")
@UiDescriptor("address-edit.xml")
@EditedEntityContainer("addressDc")
public class AddressEdit extends StandardEditor<Address> {
}