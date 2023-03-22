package main.entity.central;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "IDENTIFICATION_NUMBER")
@Entity
public class IdentificationNumber {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "VALUE_")
    private String value;

    @Column(name = "IDENTIFICATION_NUMBER_TYPE")
    private String identificationNumberType;

    @Column(name = "OTHER_NAME")
    private String otherName;

    @JoinColumn(name = "PERSON_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @Column(name = "IS_DEFAULT")
    private Boolean isDefault;

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public IdentificationNumberType getIdentificationNumberType() {
        return identificationNumberType == null ? null : IdentificationNumberType.fromId(identificationNumberType);
    }

    public void setIdentificationNumberType(IdentificationNumberType identificationNumberType) {
        this.identificationNumberType = identificationNumberType == null ? null : identificationNumberType.getId();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}