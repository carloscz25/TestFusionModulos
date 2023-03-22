package main.entity.central;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "ATTACHMENT")
@Entity
public class Attachment {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "ATTACHMENT_COLLECTION_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private AttachmentCollection attachmentCollection;

    @InstanceName
    @Column(name = "NAME")
    private String name;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "EXTENSION", length = 10)
    private String extension;

    @Column(name = "SIZE_")
    private Integer size;

    @Column(name = "SERIAL")
    private byte[] serial;

    public byte[] getSerial() {
        return serial;
    }

    public void setSerial(byte[] serial) {
        this.serial = serial;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AttachmentCollection getAttachmentCollection() {
        return attachmentCollection;
    }

    public void setAttachmentCollection(AttachmentCollection attachmentCollection) {
        this.attachmentCollection = attachmentCollection;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}