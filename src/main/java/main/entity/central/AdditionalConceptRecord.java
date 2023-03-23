package main.entity.central;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "ADDITIONAL_CONCEPT_RECORD")
@Entity
public class AdditionalConceptRecord {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "BASE_")
    private Double base;

    @Column(name = "PERCENTAGE")
    private Double percentage;

    @Column(name = "QUOTE")
    private Double quote;

    @Column(name = "ADDITIONAL_CONCEPT_TYPE")
    private String additionalConceptType;

    @JoinColumn(name = "ALLOCATABLE_DOCUMENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Document allocatableDocument;

    public AdditionalConceptType getAdditionalConceptType() {
        return additionalConceptType == null ? null : AdditionalConceptType.fromId(additionalConceptType);
    }

    public void setAdditionalConceptType(AdditionalConceptType additionalConceptType) {
        this.additionalConceptType = additionalConceptType == null ? null : additionalConceptType.getId();
    }

    public Double getQuote() {
        return quote;
    }

    public void setQuote(Double quote) {
        this.quote = quote;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getBase() {
        return base;
    }

    public void setBase(Double base) {
        this.base = base;
    }

    public Document getAllocatableDocument() {
        return allocatableDocument;
    }

    public void setAllocatableDocument(Document document) {
        this.allocatableDocument = document;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}