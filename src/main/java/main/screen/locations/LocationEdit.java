package main.screen.locations;

import io.jmix.ui.screen.*;
import main.entity.locations.Location;

@UiController("Location.edit")
@UiDescriptor("location-edit.xml")
@EditedEntityContainer("locationDc")
public class LocationEdit extends StandardEditor<Location> {
}