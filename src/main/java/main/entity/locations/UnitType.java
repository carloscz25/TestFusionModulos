package main.entity.locations;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum UnitType implements EnumClass<String> {

    RESIDENCE("RESIDENCE"),
    COMMERCIAL("COMMERCIAL"),
    INDUSTRIAL("INDUSTRIAL"),
    OTHER("OTHER");

    private String id;

    UnitType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static UnitType fromId(String id) {
        for (UnitType at : UnitType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}