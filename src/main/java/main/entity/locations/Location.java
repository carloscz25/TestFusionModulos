package main.entity.locations;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import main.entity.central.Address;
import main.entity.central.Attachment;
import main.entity.central.AttachmentCollection;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "LOCATION")
@Entity
public class Location {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME")
    private String name;

    @JoinColumn(name = "ADDRESS_ID", nullable = false)
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Address address;

    @Column(name = "LOCATION_ABBREVIATION", length = 25)
    private String locationAbbreviation;

    @Column(name = "VERTICAL_PROPERTY")
    private Boolean verticalProperty;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;

    @Column(name = "NUMBER_OF_LIFTS")
    private Integer numberOfLifts;

    @JoinColumn(name = "OWNER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Owner owner;

    @JoinColumn(name = "ATTACHMENT_COLLECTION_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private AttachmentCollection attachmentCollection;

    @Column(name = "LAND_REGISTRY_INFORMATION")
    private String landRegistryInformation;

    @JoinColumn(name = "FACADE_PICTURE_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Attachment facadePicture;

    @OneToMany(mappedBy = "location")
    private List<Coefficient> coefficients;

    @OneToMany(mappedBy = "location")
    private List<Unit> units;

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public List<Coefficient> getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(List<Coefficient> coefficients) {
        this.coefficients = coefficients;
    }

    public Attachment getFacadePicture() {
        return facadePicture;
    }

    public void setFacadePicture(Attachment facadePicture) {
        this.facadePicture = facadePicture;
    }

    public String getLandRegistryInformation() {
        return landRegistryInformation;
    }

    public void setLandRegistryInformation(String landRegistryInformation) {
        this.landRegistryInformation = landRegistryInformation;
    }

    public AttachmentCollection getAttachmentCollection() {
        return attachmentCollection;
    }

    public void setAttachmentCollection(AttachmentCollection attachmentCollection) {
        this.attachmentCollection = attachmentCollection;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Integer getNumberOfLifts() {
        return numberOfLifts;
    }

    public void setNumberOfLifts(Integer numberOfLifts) {
        this.numberOfLifts = numberOfLifts;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Boolean getVerticalProperty() {
        return verticalProperty;
    }

    public void setVerticalProperty(Boolean verticalProperty) {
        this.verticalProperty = verticalProperty;
    }

    public String getLocationAbbreviation() {
        return locationAbbreviation;
    }

    public void setLocationAbbreviation(String locationAbbreviation) {
        this.locationAbbreviation = locationAbbreviation;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}