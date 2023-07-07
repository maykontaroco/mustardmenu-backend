package br.com.taroco.mustardmenu.domain.model.user;

public enum ProfileEnum {
    ADMINISTRADOR("ADMINISTRADOR"),
    OPERATOR("OPERATOR");

    private String description;

    ProfileEnum(String description) {
        this.description = description;
    }
}
