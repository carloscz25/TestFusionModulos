package main.screen.locations;

import io.jmix.ui.screen.*;
import main.entity.locations.Unit;

@UiController("Unit.browse")
@UiDescriptor("unit-browse.xml")
@LookupComponent("unitsTable")
public class UnitBrowse extends StandardLookup<Unit> {
}