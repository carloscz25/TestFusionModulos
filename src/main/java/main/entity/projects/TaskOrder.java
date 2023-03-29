package main.entity.projects;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import main.entity.central.Person;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "TASK_ORDER")
@Entity
public class TaskOrder {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @JoinColumn(name = "PROPOSED_BY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Person proposedBy;

    @InstanceName
    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    @Column(name = "APPROXIMATE_DURATION_OF_TASK")
    private String approximateDurationOfTask;

    @Column(name = "TIME_SLOT_PROPOSAL")
    private String timeSlotProposal;

    @Column(name = "FINALISATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date finalisationDate;

    public Date getFinalisationDate() {
        return finalisationDate;
    }

    public void setFinalisationDate(Date finalisationDate) {
        this.finalisationDate = finalisationDate;
    }

    public String getTimeSlotProposal() {
        return timeSlotProposal;
    }

    public void setTimeSlotProposal(String timeSlotProposal) {
        this.timeSlotProposal = timeSlotProposal;
    }

    public String getApproximateDurationOfTask() {
        return approximateDurationOfTask;
    }

    public void setApproximateDurationOfTask(String approximateDurationOfTask) {
        this.approximateDurationOfTask = approximateDurationOfTask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getProposedBy() {
        return proposedBy;
    }

    public void setProposedBy(Person proposedBy) {
        this.proposedBy = proposedBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}