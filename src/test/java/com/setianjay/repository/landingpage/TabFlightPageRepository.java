package com.setianjay.repository.landingpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TabFlightPageRepository {
    public By flyingFormLocator = By.id("flight-origin-search-input");
    public By flyingToLocator = By.id("flight-destination-search-input");
    public By listAutoSuggestLocator = By.xpath("//li[@data-selenium='autosuggest-item']");
    public By listDaysLocator = By.className("PriceSurgePicker-Day__label");
    public By buttonMinusAdultLocator = By.xpath("//button[contains(@data-element-name, 'flight-occupancy-adult-decrease')]");
    public By buttonPlusAdultLocator = By.xpath("//button[contains(@data-element-name, 'flight-occupancy-adult-increase')]");
    public By adultCountDisplayLocator = By.xpath("//span[@data-component='flight-occupancy-adult-number']");
    public By buttonMinusChildrenLocator = By.xpath("//button[contains(@data-element-name, 'flight-occupancy-children-decrease')]");
    public By buttonPlusChildrenLocator = By.xpath("//button[contains(@data-element-name, 'flight-occupancy-children-increase')]");
    public By childrenCountDisplayLocator = By.xpath("//span[@data-component='flight-occupancy-children-number']");
    public By buttonMinusInfantLocator = By.xpath("//button[contains(@data-element-name, 'flight-occupancy-infant-decrease')]");
    public By buttonPlusInfantLocator = By.xpath("//button[contains(@data-element-name, 'flight-occupancy-infant-increase')]");
    public By infantsCountDisplayLocator = By.xpath("//span[@data-component='flight-occupancy-infants-number']");
    public By buttonEconomyClassLocator = By.xpath("//button[@data-element-object-id='economy']");
    public By buttonPremiumEconomyClassLocator = By.xpath("//button[@data-element-object-id='premium-economy']");
    public By buttonBusinessClassLocator = By.xpath("//button[@data-element-object-id='business']");
    public By buttonFirstClassLocator = By.xpath("//button[@data-element-object-id='first']");


    public TabFlightPageRepository(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
