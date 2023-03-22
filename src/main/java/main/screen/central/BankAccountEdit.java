package main.screen.central;

import io.jmix.ui.screen.*;
import main.entity.central.BankAccount;

@UiController("BankAccount.edit")
@UiDescriptor("bank-account-edit.xml")
@EditedEntityContainer("bankAccountDc")
public class BankAccountEdit extends StandardEditor<BankAccount> {
}