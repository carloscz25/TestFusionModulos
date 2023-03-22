package main.entity.central;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import main.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "PERSON")
@Entity
public class Person {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "PHYSICAL_LEGAL")
    private String personType;

    @JoinColumn(name = "ATTACHMENT_COLLECTION_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private AttachmentCollection attachmentCollection;

    @JoinColumn(name = "USER_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "person")
    private List<Address> addresses;

    @OneToMany(mappedBy = "person")
    private List<BankAccount> bankAccounts;

    @OneToMany(mappedBy = "person")
    private List<ContactInfoItem> contactInfoItems;

    @OneToMany(mappedBy = "person")
    private List<IdentificationNumber> identificationNumbers;

    @Column(name = "COMPLETE_NAME")
    private String completeName;

    @Column(name = "NAME1")
    private String name1;

    @InstanceName
    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "SURNAME1")
    private String surname1;

    @Column(name = "SURNAME2")
    private String surname2;

    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "COMPANY_NAME")
    private String officialCompanyName;

    @Column(name = "PUBLIC_COMPANY_NAME")
    private String publicCompanyName;

    @Column(name = "COMPANY_ACTIVITIES_DESCRIPTION")
    private String companyActivitiesDescription;

    @Column(name = "CONSTITUTION_DATE")
    private String constitutionDate;

    @Transient
    @JmixProperty
    public String getDefaultId() throws Exception{

        List<IdentificationNumber> ids = this.getIdentificationNumbers();
        for (int i = 0; i < ids.size(); i++) {
            IdentificationNumber id = ids.get(i);
            if (id.getIsDefault()){
                return id.getValue();
            }
        }
        //throw new IncompleteDataException("No default id value found for person : " + this.getCompleteName() + " Entity : Person.class / UUID : " + this.getId().toString());
        return "";
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType == null ? null : personType.getId();
    }

    public PersonType getPersonType() {
        return personType == null ? null : PersonType.fromId(personType);
    }

    public AttachmentCollection getAttachmentCollection() {
        return attachmentCollection;
    }

    public void setAttachmentCollection(AttachmentCollection attachmentCollection) {
        this.attachmentCollection = attachmentCollection;
    }

    public String getConstitutionDate() {
        return constitutionDate;
    }

    public void setConstitutionDate(String constitutionDate) {
        this.constitutionDate = constitutionDate;
    }

    public String getCompanyActivitiesDescription() {
        return companyActivitiesDescription;
    }

    public void setCompanyActivitiesDescription(String companyActivitiesDescription) {
        this.companyActivitiesDescription = companyActivitiesDescription;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPublicCompanyName() {
        return publicCompanyName;
    }

    public void setPublicCompanyName(String publicCompanyName) {
        this.publicCompanyName = publicCompanyName;
    }

    public String getOfficialCompanyName() {
        return officialCompanyName;
    }

    public void setOfficialCompanyName(String officialCompanyName) {
        this.officialCompanyName = officialCompanyName;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    public List<IdentificationNumber> getIdentificationNumbers() {
        return identificationNumbers = new ArrayList<IdentificationNumber>();
    }

    public void setIdentificationNumbers(List<IdentificationNumber> identificationNumbers) {
        this.identificationNumbers = identificationNumbers;
    }

    public List<ContactInfoItem> getContactInfoItems() {
        return contactInfoItems;
    }

    public void setContactInfoItems(List<ContactInfoItem> contactInfoItems) {
        this.contactInfoItems = contactInfoItems;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}