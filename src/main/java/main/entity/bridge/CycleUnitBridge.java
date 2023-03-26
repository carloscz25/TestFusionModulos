package main.entity.bridge;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import main.entity.locations.Unit;
import main.entity.projects.Cycle;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "CYCLE_UNIT_BRIDGE")
@Entity
public class CycleUnitBridge {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "CYCLE_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Cycle cycle;

    @JoinColumn(name = "UNIT_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Unit unit;





    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }





}