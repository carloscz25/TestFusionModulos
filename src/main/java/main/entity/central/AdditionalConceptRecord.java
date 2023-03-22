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

    @JoinColumn(name = "ALLOCATABLE_DOCUMENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Document allocatableDocument;

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