package main.screen.central;

import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.ui.screen.*;
import main.entity.central.Person;
import main.entity.central.Supplier;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("Supplier.edit")
@UiDescriptor("supplier-edit.xml")
@EditedEntityContainer("supplierDc")
public class SupplierEdit extends StandardEditor<Supplier> {


    @Autowired
    private DataManager dataManager;

    @Install(to = "personsDl", target = Target.DATA_LOADER)
    private List<Person> personsDlLoadDelegate(LoadContext<Person> loadContext) {
        String sql = "select p from Person p";
        List<Person> pp = dataManager.load(Person.class).query(sql).list();
        return pp;
    }



}