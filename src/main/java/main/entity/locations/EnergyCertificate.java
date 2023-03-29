package main.entity.locations;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "ENERGY_CERTIFICATE")
@Entity
public class EnergyCertificate {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "UNIT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Unit unit;

    @Column(name = "REGISTRY_NUMBER")
    private String registryNumber;

    @Column(name = "ISSUE_DATE")
    @Temporal(TemporalType.DATE)
    private Date issueDate;

    @Column(name = "EXPIRATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @Column(name = "OBSERVATIONS")
    @Lob
    private String observations;

    @Column(name = "EMISSIONS_QUALIFICATION", length = 10)
    private String emissionsQualification;

    @Column(name = "ENERGY_CONSUMPTION_QUALIF", length = 10)
    private String energyConsumptionQualification;

    @Column(name = "SCAN")
    private byte[] scan;

    public byte[] getScan() {
        return scan;
    }

    public void setScan(byte[] scan) {
        this.scan = scan;
    }

    public String getEnergyConsumptionQualification() {
        return energyConsumptionQualification;
    }

    public void setEnergyConsumptionQualification(String energyConsumptionQualification) {
        this.energyConsumptionQualification = energyConsumptionQualification;
    }

    public String getEmissionsQualification() {
        return emissionsQualification;
    }

    public void setEmissionsQualification(String emissionsQualification) {
        this.emissionsQualification = emissionsQualification;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getRegistryNumber() {
        return registryNumber;
    }

    public void setRegistryNumber(String registryNumber) {
        this.registryNumber = registryNumber;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}