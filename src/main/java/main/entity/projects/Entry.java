package main.entity.projects;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "ENTRY_")
@Entity(name = "Entry_")
public class Entry {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "CYCLE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cycle cycle;

    @Column(name = "ENTRY_CONTENT")
    @Lob
    private String entryContent;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @JoinColumn(name = "EVENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;

    @JoinColumn(name = "TASK_ORDER_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private TaskOrder taskOrder;

    public TaskOrder getTaskOrder() {
        return taskOrder;
    }

    public void setTaskOrder(TaskOrder taskOrder) {
        this.taskOrder = taskOrder;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getEntryContent() {
        return entryContent;
    }

    public void setEntryContent(String entryContent) {
        this.entryContent = entryContent;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}