package main.entity.locations;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import io.jmix.core.metamodel.annotation.NumberFormat;
import main.entity.central.Attachment;
import main.entity.central.AttachmentCollection;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "UNIT")
@Entity
public class Unit {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "LEVEL_", length = 10)
    private String level;

    @Column(name = "DOOR", length = 10)
    private String door;

    @OneToMany(mappedBy = "unit")
    private List<OccupancyCertificate> occupancyCertificates;

    @OneToMany(mappedBy = "unit")
    private List<EnergyCertificate> energyCertificates;

    @Column(name = "TYPE_")
    private String type;

    @NumberFormat(pattern = "##,00")
    @Column(name = "AREA")
    private Double area;

    @Column(name = "ABBREVIATION_LEVEL_DOOR", length = 10)
    private String abbreviationLevelDoor;

    @Column(name = "LAND_REGISTRY_INFORMATION")
    private String landRegistryInformation;

    @Column(name = "OBSERVATIONS")
    private String observations;

    @JoinColumn(name = "ATTACHMENT_COLLECTION_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private AttachmentCollection attachmentCollection;

    @JoinColumn(name = "OWNER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Owner owner;

    @JoinColumn(name = "UNIT_PLAN_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Attachment unitPlan;

    @Column(name = "EXCLUDE_FROM_EMPTY_UNIT_SEARCH")
    private Boolean excludeFromEmptyUnitSearch;

    @OneToMany(mappedBy = "unit")
    private List<Coefficient> coefficients;

    @JmixProperty
    @Transient
    private String fullDescriptiveName;
    @JoinColumn(name = "LOCATION_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Coefficient> getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(List<Coefficient> coefficients) {
        this.coefficients = coefficients;
    }

    public Boolean getExcludeFromEmptyUnitSearch() {
        return excludeFromEmptyUnitSearch;
    }

    public void setExcludeFromEmptyUnitSearch(Boolean excludeFromEmptyUnitSearch) {
        this.excludeFromEmptyUnitSearch = excludeFromEmptyUnitSearch;
    }

    public Attachment getUnitPlan() {
        return unitPlan;
    }

    public void setUnitPlan(Attachment unitPlan) {
        this.unitPlan = unitPlan;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public AttachmentCollection getAttachmentCollection() {
        return attachmentCollection;
    }

    public void setAttachmentCollection(AttachmentCollection attachmentCollection) {
        this.attachmentCollection = attachmentCollection;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getLandRegistryInformation() {
        return landRegistryInformation;
    }

    public void setLandRegistryInformation(String landRegistryInformation) {
        this.landRegistryInformation = landRegistryInformation;
    }

    public String getAbbreviationLevelDoor() {
        return abbreviationLevelDoor;
    }

    public void setAbbreviationLevelDoor(String abbreviationLevelDoor) {
        this.abbreviationLevelDoor = abbreviationLevelDoor;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public UnitType getType() {
        return type == null ? null : UnitType.fromId(type);
    }

    public void setType(UnitType type) {
        this.type = type == null ? null : type.getId();
    }

    public List<EnergyCertificate> getEnergyCertificates() {
        return energyCertificates;
    }

    public void setEnergyCertificates(List<EnergyCertificate> energyCertificates) {
        this.energyCertificates = energyCertificates;
    }

    public List<OccupancyCertificate> getOccupancyCertificates() {
        return occupancyCertificates;
    }

    public void setOccupancyCertificates(List<OccupancyCertificate> occupancyCertificates) {
        this.occupancyCertificates = occupancyCertificates;
    }

    public String getFullDescriptiveName() {
        return fullDescriptiveName;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}