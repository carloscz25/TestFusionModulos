package main.entity.central;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum ContactInfoItemType implements EnumClass<String> {

    LANDPHONE("Land Phone"),
    MOBILE("Mobile Phone"),
    EMAIL("Email");

    private String id;

    ContactInfoItemType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static ContactInfoItemType fromId(String id) {
        for (ContactInfoItemType at : ContactInfoItemType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}