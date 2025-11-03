package com.setianjay.repository.landingpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class LandingPageRepository {

    public By listTabsLocator = By.xpath("//ul[contains(@class,'lcnzEG')]/descendant::h6");
    public By buttonSearchLocator = By.xpath("//button[contains(@class,'fDMIuA')]");
    public By boxModalMessageLocator = By.xpath("//div[@data-element-name='search-box-modal-message']");

    public LandingPageRepository(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
