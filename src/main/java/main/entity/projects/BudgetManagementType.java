package main.entity.projects;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum BudgetManagementType implements EnumClass<String> {

    UNNEEDED("Unneded"),
    AWAITING("Awaiting"),
    PENDING_APPROVAL("Pending Approval"),
    APPROVED("Approved");

    private String id;

    BudgetManagementType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static BudgetManagementType fromId(String id) {
        for (BudgetManagementType at : BudgetManagementType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}