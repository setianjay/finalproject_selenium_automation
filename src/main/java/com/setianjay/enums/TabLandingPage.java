package com.setianjay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TabLandingPage {
    TAB_HOTEL("Hotels", 0),
    TAB_HOME_AND_APARTMENT("Homes & Apts", 1),
    TAB_FLIGHT_AND_HOTEL("Flight & Hotel", 2),
    TAB_FLIGHT("Flights", 3),
    TAB_ACTIVITIES("Activities", 4),
    TAB_AIRPORT_TRANSFER("Airport Transfer", 5);

    private final String tabName;
    private final Integer tabPosition;

    public TabLandingPage fromName(String tabName) {
        for (TabLandingPage tab : TabLandingPage.values()) {
            if (tab.tabName.equalsIgnoreCase(tabName)) {
                return tab;
            }
        }
        return null;
    }

    public TabLandingPage fromPosition(Integer tabPosition) {
        return TabLandingPage.values()[tabPosition];
    }
}
