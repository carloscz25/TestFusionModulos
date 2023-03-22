package main.entity.projects;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum CycleType implements EnumClass<String> {

    OPERATIVE("Operative"),
    ADMINISTRATIVE("Administrative");

    private String id;

    CycleType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static CycleType fromId(String id) {
        for (CycleType at : CycleType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}