package main.entity.central;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "BANK_ACCOUNT")
@Entity
public class BankAccount {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "FULL_BANK_ACCOUNT_CODE")
    private String fullBankAccountCode;

    @JoinColumn(name = "PERSON_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @Column(name = "FINANCIAL_INSTITUCION_PART", length = 10)
    private String financialInstitucionPart;

    @Column(name = "OFFICE_PART", length = 10)
    private String officePart;

    @Column(name = "CONTROL_DIGITS", length = 10)
    private String controlDigits;

    @Column(name = "ACCOUNT_NUMBER", length = 25)
    private String accountNumber;

    @OneToMany(mappedBy = "bankAccount")
    private List<ContactInfoItem> contactInfoItems;

    @JoinColumn(name = "OFFICE_ADDRESS_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Address officeAddress;

    @Column(name = "FINANCIAL_INSTITUTION_NAME")
    private String financialInstitutionName;

    @Column(name = "SWIFT_BANK_CODE", length = 25)
    private String swiftBankCode;

    @Column(name = "SWIFT_COUNTRY_CODE", length = 10)
    private String swiftCountryCode;

    @Column(name = "SWIFT_CONTROL_DIGITS", length = 10)
    private String swiftControlDigits;

    public String getSwiftControlDigits() {
        return swiftControlDigits;
    }

    public void setSwiftControlDigits(String swiftControlDigits) {
        this.swiftControlDigits = swiftControlDigits;
    }

    public String getSwiftCountryCode() {
        return swiftCountryCode;
    }

    public void setSwiftCountryCode(String swiftCountryCode) {
        this.swiftCountryCode = swiftCountryCode;
    }

    public String getSwiftBankCode() {
        return swiftBankCode;
    }

    public void setSwiftBankCode(String swiftBankCode) {
        this.swiftBankCode = swiftBankCode;
    }

    public String getFinancialInstitutionName() {
        return financialInstitutionName;
    }

    public void setFinancialInstitutionName(String financialInstitutionName) {
        this.financialInstitutionName = financialInstitutionName;
    }

    public Address getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(Address officeAddress) {
        this.officeAddress = officeAddress;
    }

    public List<ContactInfoItem> getContactInfoItems() {
        return contactInfoItems;
    }

    public void setContactInfoItems(List<ContactInfoItem> contactInfoItems) {
        this.contactInfoItems = contactInfoItems;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getControlDigits() {
        return controlDigits;
    }

    public void setControlDigits(String controlDigits) {
        this.controlDigits = controlDigits;
    }

    public String getOfficePart() {
        return officePart;
    }

    public void setOfficePart(String officePart) {
        this.officePart = officePart;
    }

    public String getFinancialInstitucionPart() {
        return financialInstitucionPart;
    }

    public void setFinancialInstitucionPart(String financialInstitucionPart) {
        this.financialInstitucionPart = financialInstitucionPart;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getFullBankAccountCode() {
        return fullBankAccountCode;
    }

    public void setFullBankAccountCode(String fullBankAccountCode) {
        this.fullBankAccountCode = fullBankAccountCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}