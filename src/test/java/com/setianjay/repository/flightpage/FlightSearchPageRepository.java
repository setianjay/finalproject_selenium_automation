package com.setianjay.repository.flightpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class FlightSearchPageRepository {
    public By titleFlightSearchListLocator = By.xpath("//h2[@data-component='mob-flight-result-title']");
    public By listAirlinesLocator = By.xpath("//*[@data-testid='flight-filter-item-airline']");
    public By butonSortByLocator = By.xpath("//button[@data-testid='flights-quick-sort-item-other-button']");
    public By sortListLocator = By.xpath("//div[@data-testid='floater-container']//descendant::span");
    public By flightCardsLocator = By.xpath("//div[@data-testid='flightCard-flight-detail']");
    public By flightPriceLocator = By.xpath("//span[@data-element-name='flight-price-breakdown']//descendant::span[not(contains(text(), 'Rp'))]");
    public By flightAirlineLocator = By.xpath("(.//p)[1]");
    public By flightBookButtonLocator = By.xpath(".//button[@data-component='flight-card-bookButton']");

    public FlightSearchPageRepository(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


}
