package sme;

public enum Level {
    LOW, HIGH;

    public boolean higherThan(Level other) {
        return this == HIGH && other == LOW;
    }
}
