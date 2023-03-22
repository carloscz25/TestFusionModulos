package main.entity.central;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "LEGAL_REPRESENTATION")
@Entity
public class LegalRepresentation {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "REPRESENTER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Person representer;

    @JoinColumn(name = "REPRESENTEE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Person representee;

    @Column(name = "LEGAL_REPRESENTATION_TYPE")
    private String legalRepresentationType;

    public LegalRepresentationType getLegalRepresentationType() {
        return legalRepresentationType == null ? null : LegalRepresentationType.fromId(legalRepresentationType);
    }

    public void setLegalRepresentationType(LegalRepresentationType legalRepresentationType) {
        this.legalRepresentationType = legalRepresentationType == null ? null : legalRepresentationType.getId();
    }

    public Person getRepresentee() {
        return representee;
    }

    public void setRepresentee(Person representee) {
        this.representee = representee;
    }

    public Person getRepresenter() {
        return representer;
    }

    public void setRepresenter(Person representer) {
        this.representer = representer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}