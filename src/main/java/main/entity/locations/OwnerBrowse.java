package main.entity.locations;

import io.jmix.ui.screen.*;
import main.entity.locations.Owner;

@UiController("Owner.browse")
@UiDescriptor("owner-browse.xml")
@LookupComponent("ownersTable")
public class OwnerBrowse extends StandardLookup<Owner> {



}