package main.screen.central;

import io.jmix.ui.screen.*;
import main.entity.central.Supplier;

@UiController("Supplier.browse")
@UiDescriptor("supplier-browse.xml")
@LookupComponent("suppliersTable")
public class SupplierBrowse extends StandardLookup<Supplier> {
}