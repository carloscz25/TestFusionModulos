package main.entity.projects;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "TASK_DIARY_NOTE")
@Entity
public class TaskDiaryNote {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "TASK_ORDER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TaskDiaryNote taskOrder;

    @Column(name = "CONTENT")
    @Lob
    private String content;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TaskDiaryNote getTaskOrder() {
        return taskOrder;
    }

    public void setTaskOrder(TaskDiaryNote taskOrder) {
        this.taskOrder = taskOrder;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}