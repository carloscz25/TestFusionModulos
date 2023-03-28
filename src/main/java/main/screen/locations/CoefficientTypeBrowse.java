package main.screen.locations;

import io.jmix.ui.screen.*;
import main.entity.locations.CoefficientType;

@UiController("CoefficientType.browse")
@UiDescriptor("coefficient-type-browse.xml")
@LookupComponent("coefficientTypesTable")
public class CoefficientTypeBrowse extends StandardLookup<CoefficientType> {
}