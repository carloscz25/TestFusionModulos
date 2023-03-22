package main.entity.locations;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "LOCATION_COEFFICIENT")
@Entity
public class LocationCoefficient {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @JoinColumn(name = "LOCATION_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;
    @JoinColumn(name = "UNIT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Unit unit;

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}