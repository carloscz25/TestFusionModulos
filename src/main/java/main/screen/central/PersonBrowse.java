package main.screen.central;

import io.jmix.ui.screen.*;
import main.entity.central.Person;

@UiController("Person.browse")
@UiDescriptor("person-browse.xml")
@LookupComponent("personsTable")
public class PersonBrowse extends StandardLookup<Person> {
}