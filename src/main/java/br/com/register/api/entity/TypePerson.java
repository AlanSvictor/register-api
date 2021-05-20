package br.com.register.api.entity;

public enum TypePerson {
    LEGAL_PERSON("J"),
    NATURAL_PERSON("F");

    String enumValue;

    TypePerson(String enumValue) {
        this.enumValue = enumValue;
    }

}
