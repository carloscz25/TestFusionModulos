package main.entity.bridge;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum Modules implements EnumClass<String> {

    CENTRAL("Central"),
    LOCATIONS("Locations"),
    PROJECTS("Projects");

    private String id;

    Modules(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Modules fromId(String id) {
        for (Modules at : Modules.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}