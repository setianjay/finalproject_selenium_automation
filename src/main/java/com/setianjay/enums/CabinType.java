package com.setianjay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CabinType {
    ECONOMY("Economy"),
    PREMIUM_ECONOMY("Premium Economy"),
    BUSINESS("Business"),
    FIRST("First");

    private final String displayName;

    public static CabinType fromDisplayName(String displayName) {
        for (CabinType cabinType : CabinType.values()) {
            if (cabinType.displayName.equalsIgnoreCase(displayName)) {
                return cabinType;
            }
        }
        return null;
    }
}
