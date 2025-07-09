package com.example.AcceptedAssessment.models;

public enum SportType {
    FOOTBALL(1),
    BASKETBALL(1);

    private int value;

    private SportType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static SportType fromId(int id) {
        for (SportType type : SportType.values()) {
            if (type.getValue() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown SportType ID: " + id);
    }
}
