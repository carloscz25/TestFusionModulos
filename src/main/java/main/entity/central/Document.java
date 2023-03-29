package main.entity.central;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import main.entity.bridge.Allocation;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "DOCUMENT")
@Entity
public class Document {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "TYPE_")
    private String type;

    @JoinColumn(name = "SUPPLIER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;

    @Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;

    @Column(name = "ISSUE_DATE")
    @Temporal(TemporalType.DATE)
    private Date issueDate;

    @InstanceName
    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    @Column(name = "TOTAL_BASE")
    private Double totalBase;

    @Column(name = "TOTAL_AFTER_ADD_CONCEPTS")
    private Double totalAfterAdditionalConcepts;

    @JoinColumn(name = "ATTACHMENT_COLLECTION_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private AttachmentCollection attachmentCollection;

    @Column(name = "OMMIT_DOCUMENT_AT_PMT_CONTROL")
    private Boolean ommitDocumentAtPaymentControl;

    @OneToMany(mappedBy = "allocatableDocument")
    private List<AdditionalConceptRecord> additionalConcepts;

    @OneToMany(mappedBy = "allocatableDocument")
    private List<Allocation> allocations;

    @Transient
    @JmixProperty
    public String getDescriptiveText() {
        return this.supplier.getCommercialName() + " " + this.getDocumentNumber();
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<Allocation> allocations) {
        this.allocations = allocations;
    }

    public List<AdditionalConceptRecord> getAdditionalConcepts() {
        return additionalConcepts;
    }

    public void setAdditionalConcepts(List<AdditionalConceptRecord> additionalConcepts) {
        this.additionalConcepts = additionalConcepts;
    }

    public Boolean getOmmitDocumentAtPaymentControl() {
        return ommitDocumentAtPaymentControl;
    }

    public void setOmmitDocumentAtPaymentControl(Boolean ommitDocumentAtPaymentControl) {
        this.ommitDocumentAtPaymentControl = ommitDocumentAtPaymentControl;
    }

    public AttachmentCollection getAttachmentCollection() {
        return attachmentCollection;
    }

    public void setAttachmentCollection(AttachmentCollection attachmentCollection) {
        this.attachmentCollection = attachmentCollection;
    }

    public Double getTotalAfterAdditionalConcepts() {
        return totalAfterAdditionalConcepts;
    }

    public void setTotalAfterAdditionalConcepts(Double totalAfterAdditionalConcepts) {
        this.totalAfterAdditionalConcepts = totalAfterAdditionalConcepts;
    }

    public Double getTotalBase() {
        return totalBase;
    }

    public void setTotalBase(Double totalBase) {
        this.totalBase = totalBase;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public DocumentType getType() {
        return type == null ? null : DocumentType.fromId(type);
    }

    public void setType(DocumentType type) {
        this.type = type == null ? null : type.getId();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}