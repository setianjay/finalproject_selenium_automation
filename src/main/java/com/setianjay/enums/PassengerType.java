package com.setianjay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PassengerType {
    ADULT("Adult"),
    CHILDREN("Children"),
    INFANTS("Infants");

    private final String displayName;

    public PassengerType fromDisplayName(String displayName) {
        for (PassengerType passengerType : PassengerType.values()) {
            if (passengerType.getDisplayName().equalsIgnoreCase(displayName)) {
                return passengerType;
            }
        }
        return null;
    }
}
