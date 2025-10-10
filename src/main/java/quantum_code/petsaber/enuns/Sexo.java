package quantum_code.petsaber.enuns;

public enum Sexo {
    MACHO("Macho"),
    FEMEA("Fêmea");

    private final String label;

    Sexo(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
