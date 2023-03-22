package main.entity.central;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "SUPPLIER")
@Entity
public class Supplier {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "PERSON_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Person person;

    @Column(name = "ACTIVITY_DESCRIPTION")
    private String activityDescription;

    @Column(name = "OBSERVATIONS")
    private String observations;

    @Column(name = "COMMERCIAL_NAME")
    private String commercialName;

    @Column(name = "SUPPORTS_TELEMATIC_PAYMENT")
    private Boolean supportsTelematicPayment;

    @Column(name = "SUPPORTS_DIRECT_DEBIT")
    private String supportsDirectDebit;

    @Column(name = "SEND_EMAIL_AT_INVOICE_APPROVAL")
    private Boolean sendEmailAtInvoiceApproval;

    @Column(name = "SEND_EMAIL_AT_INVOICE_PAYMENT")
    private Boolean sendEmailAtInvoicePayment;

    @JoinColumn(name = "BANK_ACC_TELEMATIC_PAYMENTS_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private BankAccount bankAccountForTelematicPayments;

    @JoinColumn(name = "BANK_ACC_DIRECTDEBIT_PMTS_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private BankAccount bankAccountForDirectDebitPayments;

    public BankAccount getBankAccountForDirectDebitPayments() {
        return bankAccountForDirectDebitPayments;
    }

    public void setBankAccountForDirectDebitPayments(BankAccount bankAccountForDirectDebitPayments) {
        this.bankAccountForDirectDebitPayments = bankAccountForDirectDebitPayments;
    }

    public BankAccount getBankAccountForTelematicPayments() {
        return bankAccountForTelematicPayments;
    }

    public void setBankAccountForTelematicPayments(BankAccount bankAccountForTelematicPayments) {
        this.bankAccountForTelematicPayments = bankAccountForTelematicPayments;
    }

    public Boolean getSendEmailAtInvoicePayment() {
        return sendEmailAtInvoicePayment;
    }

    public void setSendEmailAtInvoicePayment(Boolean sendEmailAtInvoicePayment) {
        this.sendEmailAtInvoicePayment = sendEmailAtInvoicePayment;
    }

    public Boolean getSendEmailAtInvoiceApproval() {
        return sendEmailAtInvoiceApproval;
    }

    public void setSendEmailAtInvoiceApproval(Boolean sendEmailAtInvoiceApproval) {
        this.sendEmailAtInvoiceApproval = sendEmailAtInvoiceApproval;
    }

    public String getSupportsDirectDebit() {
        return supportsDirectDebit;
    }

    public void setSupportsDirectDebit(String supportsDirectDebit) {
        this.supportsDirectDebit = supportsDirectDebit;
    }

    public Boolean getSupportsTelematicPayment() {
        return supportsTelematicPayment;
    }

    public void setSupportsTelematicPayment(Boolean supportsTelematicPayment) {
        this.supportsTelematicPayment = supportsTelematicPayment;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}