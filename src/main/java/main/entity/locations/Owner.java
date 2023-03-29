package main.entity.locations;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import main.entity.central.BankAccount;
import main.entity.central.Person;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "OWNER")
@Entity
public class Owner {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "ABBREVIATION_FOR_DOCUMENTS", length = 50)
    private String abbreviationForDocuments;

    @Column(name = "CLIENT_CODE")
    private String clientCode;

    @JoinColumn(name = "BANK_ACCOUNT_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private BankAccount bankAccount;

    @Column(name = "IS_PROSPECT")
    private Boolean isProspect;

    @JoinColumn(name = "PERSON_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Boolean getIsProspect() {
        return isProspect;
    }

    public void setIsProspect(Boolean isProspect) {
        this.isProspect = isProspect;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getAbbreviationForDocuments() {
        return abbreviationForDocuments;
    }

    public void setAbbreviationForDocuments(String abbreviationForDocuments) {
        this.abbreviationForDocuments = abbreviationForDocuments;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}