package main.entity.central;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "ATTACHMENT_COLLECTION")
@Entity
public class AttachmentCollection {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME")
    private String name = "";

    @JoinColumn(name = "ATTACHM_COLLECTION_PARENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private AttachmentCollection attachmentCollectionParent;

    @OneToMany(mappedBy = "attachmentCollectionParent")
    private List<AttachmentCollection> attachmentCollections = new ArrayList<AttachmentCollection>();

    @OneToMany(mappedBy = "attachmentCollection")
    private List<Attachment> attachments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public List<AttachmentCollection> getAttachmentCollections() {
        return attachmentCollections;
    }

    public void setAttachmentCollections(List<AttachmentCollection> attachmentCollections) {
        this.attachmentCollections = attachmentCollections;
    }

    public AttachmentCollection getAttachmentCollectionParent() {
        return attachmentCollectionParent;
    }

    public void setAttachmentCollectionParent(AttachmentCollection attachmentCollectionParent) {
        this.attachmentCollectionParent = attachmentCollectionParent;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}