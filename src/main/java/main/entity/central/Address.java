package main.entity.central;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "ADDRESS")
@Entity
public class Address {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "POSTAL_CODE", length = 25)
    private String postalCode;

    @Column(name = "COMPLETE_ADDRESS")
    private String completeAddress;

    @Column(name = "BLOCK", length = 10)
    private String block;

    @InstanceName
    @Column(name = "NAME")
    private String name;

    @Column(name = "WAY_NAME")
    private String wayName;

    @Column(name = "WAY_NUMBER", length = 25)
    private String wayNumber;

    @Column(name = "OBSERVATIONS")
    private String observations;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "LEVEL_", length = 10)
    private String level;

    @Column(name = "REGION")
    private String region;

    @Column(name = "WAY", length = 10)
    private String way;

    @Column(name = "TOWN")
    private String town;

    @Column(name = "PROVINCE")
    private String province;

    @Column(name = "DOOR", length = 10)
    private String door;

    @JoinColumn(name = "PERSON_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @Column(name = "ADDRESS_IS_MAIL_DESTINATION")
    private Boolean addressIsMailDestination;

    public Boolean getAddressIsMailDestination() {
        return addressIsMailDestination;
    }

    public void setAddressIsMailDestination(Boolean addressIsMailDestination) {
        this.addressIsMailDestination = addressIsMailDestination;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getWayNumber() {
        return wayNumber;
    }

    public void setWayNumber(String wayNumber) {
        this.wayNumber = wayNumber;
    }

    public String getWayName() {
        return wayName;
    }

    public void setWayName(String wayName) {
        this.wayName = wayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getCompleteAddress() {
        return completeAddress;
    }

    public void setCompleteAddress(String completeAddress) {
        this.completeAddress = completeAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}