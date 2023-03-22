package main.entity.projects;

import main.entity.central.AttachmentCollection;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "CYCLE_")
@Entity(name = "Cycle_")
public class Cycle {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "CODE", length = 50)
    private String code;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Column(name = "CYCLE_TITLE")
    private String cycleTitle;

    @OneToMany(mappedBy = "cycle")
    private List<Event> events;

    @InstanceName
    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    @JoinColumn(name = "ATTACHMENT_COLLECTION_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private AttachmentCollection attachmentCollection;

    @Column(name = "CYCLE_TYPE")
    private String cycleType;

    @Column(name = "EXCLUDE_TASK_ORDER_SRCH_RESULT")
    private Boolean excludeFromTaskOrderSearchResults;

    @OneToMany(mappedBy = "cycle")
    private List<Entry> entries;

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public Boolean getExcludeFromTaskOrderSearchResults() {
        return excludeFromTaskOrderSearchResults;
    }

    public void setExcludeFromTaskOrderSearchResults(Boolean excludeFromTaskOrderSearchResults) {
        this.excludeFromTaskOrderSearchResults = excludeFromTaskOrderSearchResults;
    }

    public CycleType getCycleType() {
        return cycleType == null ? null : CycleType.fromId(cycleType);
    }

    public void setCycleType(CycleType cycleType) {
        this.cycleType = cycleType == null ? null : cycleType.getId();
    }

    public AttachmentCollection getAttachmentCollection() {
        return attachmentCollection;
    }

    public void setAttachmentCollection(AttachmentCollection attachmentCollection) {
        this.attachmentCollection = attachmentCollection;
    }

    public String getCycleTitle() {
        return cycleTitle;
    }

    public void setCycleTitle(String cycleTitle) {
        this.cycleTitle = cycleTitle;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}