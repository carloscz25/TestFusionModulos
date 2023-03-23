package main.entity.central;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum AdditionalConceptType implements EnumClass<String> {

    IVA("Iva"),
    IRPF("Irpf");

    private String id;

    AdditionalConceptType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static AdditionalConceptType fromId(String id) {
        for (AdditionalConceptType at : AdditionalConceptType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}