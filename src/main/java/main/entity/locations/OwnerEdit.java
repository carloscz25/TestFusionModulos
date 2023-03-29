package main.entity.locations;

import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import main.entity.central.BankAccount;
import main.entity.central.Person;
import main.entity.locations.Owner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@UiController("Owner.edit")
@UiDescriptor("owner-edit.xml")
@EditedEntityContainer("ownerDc")
public class OwnerEdit extends StandardEditor<Owner> {


    @Autowired
    private DataManager dataManager;
    @Autowired
    private InstanceContainer<Owner> ownerDc;
    @Autowired
    private CollectionLoader<BankAccount> bankAccsDl;

    @Install(to = "bankAccsDl", target = Target.DATA_LOADER)
    private List<BankAccount> bankAccsDlLoadDelegate(LoadContext<BankAccount> loadContext) {
        if (ownerDc.getItem()!=null) {
            if (ownerDc.getItem().getPerson() != null) {
                List<BankAccount> bbaa = dataManager.load(BankAccount.class)
                        .query("select ba from BankAccount ba where ba.person.id = :pid")
                        .parameter("pid", ownerDc.getItem().getPerson().getId())
                        .list();
                return bbaa;
            } else {
                return new ArrayList();
            }
        }else{
            return new ArrayList();
        }
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        bankAccsDl.load();
    }



    @Subscribe("personField")
    public void onPersonFieldValueChange(HasValue.ValueChangeEvent<Person> event) {
        bankAccsDl.load();
    }







}