package main.entity.central;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum LegalRepresentationType implements EnumClass<String> {

    ADMINISTRATOR("Administrator"),
    DELEGATE("Delegate");

    private String id;

    LegalRepresentationType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static LegalRepresentationType fromId(String id) {
        for (LegalRepresentationType at : LegalRepresentationType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}