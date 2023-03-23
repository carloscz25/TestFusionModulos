package main.screen.central;

import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.ComboBox;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.VBoxLayout;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import main.entity.central.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@UiController("Person.edit")
@UiDescriptor("person-edit.xml")
@EditedEntityContainer("personDc")
public class PersonEdit extends StandardEditor<Person> {

    @Autowired
    private InstanceContainer<Person> personDc;
    @Autowired
    private VBoxLayout vbxPhysical;
    @Autowired
    private ComboBox<PersonType> cboPersonType;
    @Autowired
    private VBoxLayout vbxLegal;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private DataContext dataContext;
    @Autowired
    private GroupTable<Address> tblAddresses;
    @Autowired
    private GroupTable<BankAccount> tblBankAccs;
    @Autowired
    private GroupTable<IdentificationNumber> tblIds;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private GroupTable<LegalRepresentation> tblLLRR;
    @Autowired
    private CollectionLoader<LegalRepresentation> legalRepresentationsDl;

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        Person p = personDc.getItem();

            if (p.getPersonType() == PersonType.PHYSICAL) {
                p.setCompleteName(p.getName1().trim() + " " + p.getSurname1().trim() + " " + p.getSurname2().trim());
            } else {
                p.setCompleteName(p.getOfficialCompanyName().trim());
            }

    }




    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if (personDc.getItem().getAttachmentCollection()==null){
            personDc.getItem().setAttachmentCollection(dataContext.create(AttachmentCollection.class));
            personDc.getItem().getAttachmentCollection().setName("Collection 1");


        }
    }




    @Subscribe("cboPersonType")
    public void onCboPersonTypeValueChange(HasValue.ValueChangeEvent event) {
        if (event.isUserOriginated()){
            if (cboPersonType.getValue() == PersonType.PHYSICAL){
                vbxPhysical.setVisible(true);
                vbxLegal.setVisible(false);
            }
            if (cboPersonType.getValue() == PersonType.LEGAL){
                vbxPhysical.setVisible(false);
                vbxLegal.setVisible(true);
            }

        }
    }

    @Subscribe("tblAddresses.createAct")
    public void onTblAddressesCreateAct(Action.ActionPerformedEvent event) {
        screenBuilders.editor(Address.class, this)
                .newEntity()
                .withOpenMode(OpenMode.DIALOG)
                .withParentDataContext(dataContext)
                .withListComponent(tblAddresses)
                .build().show();
    }

    @Subscribe("tblAddresses.editAct")
    public void onTblAddressesEditAct(Action.ActionPerformedEvent event) {
        screenBuilders.editor(Address.class, this)
                .editEntity(tblAddresses.getSingleSelected())
                .withOpenMode(OpenMode.DIALOG)
                .withListComponent(tblAddresses)
                .withParentDataContext(dataContext)
                .build().show();
    }

    @Install(to = "tblAddresses.editAct", subject = "enabledRule")
    private boolean tblAddressesEditActEnabledRule() {
        return tblAddresses.getSingleSelected()!=null;
    }

    @Install(to = "tblBankAccs.editAct", subject = "enabledRule")
    private boolean tblBankAccsEditActEnabledRule() {
        return tblBankAccs.getSingleSelected()!=null;
    }

    @Subscribe("tblBankAccs.createAct")
    public void onTblBankAccsCreateAct(Action.ActionPerformedEvent event) {
        screenBuilders.editor(BankAccount.class, this).newEntity()
                .withOpenMode(OpenMode.DIALOG)
                .withListComponent(tblBankAccs)
                .withParentDataContext(dataContext)
                .build().show();
    }

    @Subscribe("tblBankAccs.editAct")
    public void onTblBankAccsEditAct(Action.ActionPerformedEvent event) {
        screenBuilders.editor(BankAccount.class, this).editEntity(tblBankAccs.getSingleSelected())
                .withOpenMode(OpenMode.DIALOG)
                .withListComponent(tblBankAccs)
                .withParentDataContext(dataContext)
                .build().show();
    }

    @Subscribe("tblIds.createIdAct")
    public void onTblIdsCreateAct(Action.ActionPerformedEvent event) {
        screenBuilders.editor(IdentificationNumber.class, this).newEntity()
                .withOpenMode(OpenMode.DIALOG)
                .withListComponent(tblIds)
                .withParentDataContext(dataContext)
                .build().show();
    }

    @Subscribe("tblIds.editIdAct")
    public void onTblIdsEditAct(Action.ActionPerformedEvent event) {
        screenBuilders.editor(IdentificationNumber.class, this).editEntity(tblIds.getSingleSelected())
                .withOpenMode(OpenMode.DIALOG)
                .withListComponent(tblIds)
                .withParentDataContext(dataContext)
                .build().show();
    }



    @Install(to = "tblIds.editIdAct", subject = "enabledRule")
    private boolean tblIdsEditActEnabledRule() {
        return tblIds.getSingleSelected()!=null;
    }

    @Autowired
    private GroupTable<ContactInfoItem> tblContactInfo;

    @Subscribe("tblContactInfo.createCIAct")
    public void onTblContactInfoCreateCIAct(Action.ActionPerformedEvent event) {
        screenBuilders.editor(ContactInfoItem.class, this).newEntity()
                .withOpenMode(OpenMode.DIALOG)
                .withListComponent(tblContactInfo)
                .withParentDataContext(dataContext)
                .build().show();
    }

    @Subscribe("tblContactInfo.editCIAct")
    public void onTblContactInfoEditCIAct(Action.ActionPerformedEvent event) {
        screenBuilders.editor(ContactInfoItem.class, this).editEntity(tblContactInfo.getSingleSelected())
                .withOpenMode(OpenMode.DIALOG)
                .withListComponent(tblContactInfo)
                .withParentDataContext(dataContext)
                .build().show();
    }

    @Install(to = "tblContactInfo.editCIAct", subject = "enabledRule")
    private boolean tblContactInfoEditCIActEnabledRule() {
        return tblContactInfo.getSingleSelected()!=null;
    }

    @Subscribe("tblLLRR.createLRAct")
    public void onTblLLRRCreateLRAct(Action.ActionPerformedEvent event) {
        screenBuilders.editor(LegalRepresentation.class, this).newEntity()
                .withOpenMode(OpenMode.DIALOG)
                .withListComponent(tblLLRR)
                .withParentDataContext(dataContext)
                .build().show();
    }

    @Install(to = "tblLLRR.editLRAct", subject = "enabledRule")
    private boolean tblLLRREditLRActEnabledRule() {
        return tblLLRR.getSingleSelected()!=null;
    }

    @Subscribe("tblLLRR.editLRAct")
    public void onTblLLRREditLRAct(Action.ActionPerformedEvent event) {
        screenBuilders.editor(LegalRepresentation.class, this).editEntity(tblLLRR.getSingleSelected())
                .withOpenMode(OpenMode.DIALOG)
                .withListComponent(tblLLRR)
                .withParentDataContext(dataContext)
                .build().show();
    }

    @Install(to = "legalRepresentationsDl", target = Target.DATA_LOADER)
    private List<LegalRepresentation> legalRepresentationsDlLoadDelegate(LoadContext<LegalRepresentation> loadContext) {
        Person p = this.getEditedEntity();
        List<LegalRepresentation> llrr = dataManager.load(LegalRepresentation.class)
                .query("select lr from LegalRepresentation lr where lr.representer.id = :pid " +
                        "or lr.representee.id = :pid").parameter("pid", p.getId())
                .list();
        return llrr;
    }


















}