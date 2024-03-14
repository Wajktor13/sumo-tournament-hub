package com.sumotournamenthub.backend;

public enum Gender {
    M, F;

    public String displayString() {
        return name(); // Returns the name of the enum constant
    }

    public static Gender fromString(String gender) {
        for (Gender g : Gender.values()) {
            if (g.name().equalsIgnoreCase(gender)) {
                return g;
            }
        }
        throw new IllegalArgumentException("No constant with name " + gender + " found in enum Gender");
    }
}

