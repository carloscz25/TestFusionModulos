package main.screen.central;

import io.jmix.ui.component.TextField;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import main.entity.central.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("BankAccount.edit")
@UiDescriptor("bank-account-edit.xml")
@EditedEntityContainer("bankAccountDc")
public class BankAccountEdit extends StandardEditor<BankAccount> {
    @Autowired
    private TextField<String> swiftCountryCodeField;
    @Autowired
    private TextField<String> swiftControlDigitsField;
    @Autowired
    private TextField<String> financialInstitucionPartField;
    @Autowired
    private TextField<String> officePartField;
    @Autowired
    private TextField<String> controlDigitsField;
    @Autowired
    private TextField<String> accountNumberField;
    @Autowired
    private TextField<String> fullBankAccountCodeField;

    @Subscribe(id = "bankAccountDc", target = Target.DATA_CONTAINER)
    public void onBankAccountDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<BankAccount> event) {
        updateFullBankAccountCode();
    }

    private void updateFullBankAccountCode(){
        String s = swiftCountryCodeField.getValue();
        s += " " + swiftControlDigitsField.getValue();
        s += " " + financialInstitucionPartField.getValue();
        s += " " + officePartField.getValue();
        s += " " + controlDigitsField.getValue();
        s += " " + accountNumberField.getValue();
        fullBankAccountCodeField.setValue(s);
    }


}