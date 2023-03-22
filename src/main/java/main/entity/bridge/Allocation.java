package main.entity.bridge;

import main.entity.central.Document;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import main.entity.projects.Cycle;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "ALLOCATION")
@Entity
public class Allocation {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "ALLOCATABLE_DOCUMENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Document allocatableDocument;

    @InstanceName
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ALLOCATION_AMOUNT")
    private Double allocationAmount;

    @Column(name = "ALLOCATION_PERCENT")
    private Double allocationPercent;

    @JoinColumn(name = "CYCLE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cycle cycle;



    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }

    public Double getAllocationPercent() {
        return allocationPercent;
    }

    public void setAllocationPercent(Double allocationPercent) {
        this.allocationPercent = allocationPercent;
    }

    public Double getAllocationAmount() {
        return allocationAmount;
    }

    public void setAllocationAmount(Double allocationAmount) {
        this.allocationAmount = allocationAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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