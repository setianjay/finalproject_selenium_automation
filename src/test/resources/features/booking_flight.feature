Feature: Booking Flight

  Background:
    Given user navigate to Agoda homepage

  @positive
  Scenario: Booking Flight from Jakarta to Singapore for 1 passenger with Economy class
    When user click on Flight tab
    And user enter origin "Jakarta"
    And user enter destination "Singapore"
    And user select tomorrow's departure date
    And user set for 1 adult, 0 children, and 0 infants passenger with "Economy" class
    And user click search button
    And user see the flight result page
    And user choose "AirAsia" airline
    And user sort the list with the "Cheapest first"
    And user select the cheapest flight
    Then user see the flight booking order page and validate the airline and the price

  @negative
  Scenario: Booking Flight from Jakarta to Jakarta
    When user click on Flight tab
    And user enter origin "Jakarta"
    And user enter destination "Jakarta"
    And user select tomorrow's departure date
    And user set for 1 adult, 0 children, and 0 infants passenger with "Economy" class
    And user click search button
    Then user see a notification with the message "Please make sure you select origin and destination in different cities."