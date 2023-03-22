package main.entity.central;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum IdentificationNumberType implements EnumClass<String> {

    PASSPORT("Passport"),
    NATIONAL_ID("National Id."),
    FOREIGNERS_ID("Foreigner Id."),
    OTHER("Other");

    private String id;

    IdentificationNumberType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static IdentificationNumberType fromId(String id) {
        for (IdentificationNumberType at : IdentificationNumberType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}