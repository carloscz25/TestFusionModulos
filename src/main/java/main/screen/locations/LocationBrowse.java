package main.screen.locations;

import io.jmix.ui.screen.*;
import main.entity.locations.Location;

@UiController("Location.browse")
@UiDescriptor("location-browse.xml")
@LookupComponent("locationsTable")
public class LocationBrowse extends StandardLookup<Location> {
}