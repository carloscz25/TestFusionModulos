package main.screen.central;

import io.jmix.ui.screen.*;
import main.entity.central.ContactInfoItem;

@UiController("ContactInfoItem.edit")
@UiDescriptor("contact-info-item-edit.xml")
@EditedEntityContainer("contactInfoItemDc")
public class ContactInfoItemEdit extends StandardEditor<ContactInfoItem> {
}