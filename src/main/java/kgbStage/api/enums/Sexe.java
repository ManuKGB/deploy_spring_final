package kgbStage.api.enums;


public enum Sexe {
    M("M"),
    F("F");

    private final String label;

    Sexe(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
