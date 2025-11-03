package com.setianjay.page.landingpage;

import com.setianjay.page.base.BasePage;
import com.setianjay.repository.landingpage.LandingPageRepository;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class LandingPage extends BasePage {
    private final LandingPageRepository landingPageRepository;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.landingPageRepository = new LandingPageRepository(driver);
    }

    public void clickTab(String tabName) {
        List<WebElement> listTabs = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(landingPageRepository.listTabsLocator));
        for (WebElement tab : listTabs) {
            if (tab.getText().equals(tabName)) {
                tab.click();
                return;
            }
        }
        throw new NoSuchElementException("No such tab with name " + tabName);
    }

    public String getBoxModalMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(landingPageRepository.boxModalMessageLocator)).getText();
    }

    public void clickButtonSearch() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(landingPageRepository.buttonSearchLocator)).click();
    }
}
