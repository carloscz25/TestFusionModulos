package main.entity.central;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum DocumentType implements EnumClass<String> {

    INVOICE("Invoice"),
    DELIVERY_NOTE("Delivery Note"),
    BUDGET("Budget");

    private String id;

    DocumentType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static DocumentType fromId(String id) {
        for (DocumentType at : DocumentType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}