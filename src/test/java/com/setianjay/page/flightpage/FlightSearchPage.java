package com.setianjay.page.flightpage;

import com.setianjay.page.base.BasePage;
import com.setianjay.repository.flightpage.FlightSearchPageRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class FlightSearchPage extends BasePage {
    private final FlightSearchPageRepository flightSearchPageRepository;

    public FlightSearchPage(WebDriver driver) {
        super(driver);
        this.flightSearchPageRepository = new FlightSearchPageRepository(driver);
    }

    public void waitUntilContainerFlightSearchPageIsAvailable() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(flightSearchPageRepository.titleFlightSearchListLocator));
    }

    public void selectAirline(String airline) {
        List<WebElement> listAirlines = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(flightSearchPageRepository.listAirlinesLocator));
        System.out.println(listAirlines.size());
        for (WebElement item : listAirlines) {
            WebElement labelItem = item.findElement(By.tagName("p"));
            System.out.println(labelItem.getText());
            if (labelItem.getText().contains(airline)) {
                item.click();
                return;
            }
        }
    }

    public void selectSortBy(String sortBy) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(flightSearchPageRepository.butonSortByLocator)).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(flightSearchPageRepository.sortListLocator))
                .stream()
                .filter(element -> element.getText().equals(sortBy))
                .findFirst()
                .map(webElement -> wait.until(ExpectedConditions.visibilityOf(webElement.findElement(By.xpath("//ancestor::li[@role='presentation']")))))
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("sort by element not found"))
                .click();
    }

    public WebElement selectFlightByPosition(Integer position) {
        System.out.println("🎯 Selecting flight at position: " + position);
        WebElement flightCard = getFlightCardByPosition(position);
        // Click the flight card
        flightCard.click();
        return flightCard;
    }

    public void clickBookButton() {
        wait.until(ExpectedConditions.elementToBeClickable(flightSearchPageRepository.flightBookButtonLocator)).click();
    }

    private WebElement getFlightCardByPosition(Integer position) {
        System.out.println("📋 Getting flight card at position: " + position);

        List<WebElement> cards = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(flightSearchPageRepository.flightCardsLocator)
        );

        if (position >= cards.size()) {
            throw new IndexOutOfBoundsException(
                    "Position " + position + " out of bounds. Total cards: " + cards.size()
            );
        }

        return cards.get(position);
    }

    public String getFlightAirlineByFlightCard(WebElement flightCard) {
        try {
            // Find price element WITHIN this flight card (relative search)
            WebElement airlineElement = flightCard.findElement(flightSearchPageRepository.flightAirlineLocator);

            String airlineText = airlineElement.getText().trim();

            System.out.println("💰 Airline found: " + airlineText);

            return airlineText;

        } catch (Exception e) {
            System.out.println("❌ Error getting airline: " + e.getMessage());
            throw new RuntimeException("Could not get airline flight", e);
        }
    }

    public String getFlightPriceByFlightCard(WebElement flightCard) {
        try {
            // Find price element WITHIN this flight card (relative search)
            WebElement priceElement = flightCard.findElement(flightSearchPageRepository.flightPriceLocator);

            String priceText = priceElement.getText().trim();

            System.out.println("💰 Price found: " + priceText);

            return priceText;

        } catch (Exception e) {
            System.out.println("❌ Error getting price: " + e.getMessage());
            throw new RuntimeException("Could not get flight price", e);
        }
    }

}
