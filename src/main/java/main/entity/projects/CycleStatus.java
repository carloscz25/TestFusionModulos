package main.entity.projects;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum CycleStatus implements EnumClass<String> {

    ACTIVE("Active"),
    FILED("Filed");

    private String id;

    CycleStatus(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static CycleStatus fromId(String id) {
        for (CycleStatus at : CycleStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}