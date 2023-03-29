package main.entity.projects;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import main.entity.central.Supplier;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "TASK_APPOINTMENT")
@Entity
public class TaskAppointment {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "TASK_ORDER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TaskOrder taskOrder;

    @JoinColumn(name = "SUPPLIER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;

    @Column(name = "BUDGET_MANAGEMENT_TYPE")
    private String budgetManagementType;

    @Column(name = "SCHEDULED_START_DATE")
    @Temporal(TemporalType.DATE)
    private Date scheduledStartDate;

    @Column(name = "SCHEDULED_FINALISATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date scheduledFinalisationDate;

    @Column(name = "FINALISATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date finalisationDate;

    @Column(name = "SUPPLIER_OBSERVATIONS")
    @Lob
    private String supplierObservations;

    public String getSupplierObservations() {
        return supplierObservations;
    }

    public void setSupplierObservations(String supplierObservations) {
        this.supplierObservations = supplierObservations;
    }

    public Date getFinalisationDate() {
        return finalisationDate;
    }

    public void setFinalisationDate(Date finalisationDate) {
        this.finalisationDate = finalisationDate;
    }

    public Date getScheduledFinalisationDate() {
        return scheduledFinalisationDate;
    }

    public void setScheduledFinalisationDate(Date scheduledFinalisationDate) {
        this.scheduledFinalisationDate = scheduledFinalisationDate;
    }

    public Date getScheduledStartDate() {
        return scheduledStartDate;
    }

    public void setScheduledStartDate(Date scheduledStartDate) {
        this.scheduledStartDate = scheduledStartDate;
    }

    public BudgetManagementType getBudgetManagementType() {
        return budgetManagementType == null ? null : BudgetManagementType.fromId(budgetManagementType);
    }

    public void setBudgetManagementType(BudgetManagementType budgetManagementType) {
        this.budgetManagementType = budgetManagementType == null ? null : budgetManagementType.getId();
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public TaskOrder getTaskOrder() {
        return taskOrder;
    }

    public void setTaskOrder(TaskOrder taskOrder) {
        this.taskOrder = taskOrder;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}