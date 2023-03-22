package main.entity.central;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum PersonType implements EnumClass<String> {

    PHYSICAL("Physical"),
    LEGAL("Legal");

    private String id;

    PersonType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static PersonType fromId(String id) {
        for (PersonType at : PersonType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}