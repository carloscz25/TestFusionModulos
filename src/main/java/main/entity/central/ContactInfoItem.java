package main.entity.central;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "CONTACT_INFO_ITEM")
@Entity
public class ContactInfoItem {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "DATA_")
    private String data;

    @Column(name = "DATA_DESCRIPTION")
    private String dataDescription;

    @Column(name = "TYPE_")
    private String type;

    @JoinColumn(name = "PERSON_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @Column(name = "MAIL_FOR_PASSWORD_RECOVERY")
    private Boolean mailForPasswordRecovery;

    @JoinColumn(name = "BANK_ACCOUNT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private BankAccount bankAccount;

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Boolean getMailForPasswordRecovery() {
        return mailForPasswordRecovery;
    }

    public void setMailForPasswordRecovery(Boolean mailForPasswordRecovery) {
        this.mailForPasswordRecovery = mailForPasswordRecovery;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ContactInfoItemType getType() {
        return type == null ? null : ContactInfoItemType.fromId(type);
    }

    public void setType(ContactInfoItemType type) {
        this.type = type == null ? null : type.getId();
    }

    public String getDataDescription() {
        return dataDescription;
    }

    public void setDataDescription(String dataDescription) {
        this.dataDescription = dataDescription;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}