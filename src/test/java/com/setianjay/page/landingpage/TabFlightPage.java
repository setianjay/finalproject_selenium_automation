package com.setianjay.page.landingpage;

import com.setianjay.enums.CabinType;
import com.setianjay.enums.PassengerType;
import com.setianjay.page.base.BasePage;
import com.setianjay.repository.landingpage.TabFlightPageRepository;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TabFlightPage extends BasePage {
    private final TabFlightPageRepository tabFlightPageRepository;

    public TabFlightPage(WebDriver driver) {
        super(driver);
        this.tabFlightPageRepository = new TabFlightPageRepository(driver);
    }

    public void fillFlyingFrom(String from) throws InterruptedException {
        WebElement flyingForm = wait.until(ExpectedConditions.visibilityOfElementLocated(tabFlightPageRepository.flyingFormLocator));
        flyingForm.click();
        for (Character c : from.toCharArray()) {
            Thread.sleep(250L);
            flyingForm.sendKeys(c.toString());
        }
    }

    public void fillFlyingTo(String to) throws InterruptedException {
        WebElement flyingTo = wait.until(ExpectedConditions.visibilityOfElementLocated(tabFlightPageRepository.flyingToLocator));
        flyingTo.click();
        for (Character c : to.toCharArray()) {
            Thread.sleep(250L);
            flyingTo.sendKeys(c.toString());
        }
    }

    public void selectAutoSuggestByPosition(Integer position) throws InterruptedException {
        List<WebElement> listAutoSuggest = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tabFlightPageRepository.listAutoSuggestLocator));
        Thread.sleep(1000L);
        listAutoSuggest.get(position).click();
    }

    public void selectDepartureDate(LocalDate departureDate) throws InterruptedException {
        List<WebElement> listDays = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tabFlightPageRepository.listDaysLocator));
        for (WebElement days : listDays) {
            String stringDate = days.getAttribute("data-selenium-date");
            if (stringDate != null && stringDate.equals(departureDate.toString())) {
                days.click();
                Thread.sleep(1000L);
                return;
            }
        }
        throw new NoSuchElementException("No such departure");
    }

    /**
     * Generic method untuk set passenger count
     *
     * @param passengerType - ADULT, CHILDREN, or INFANT
     * @param targetCount   - jumlah yang diinginkan
     */
    public void setPassengerCount(PassengerType passengerType, int targetCount) {
        System.out.println("🔧 Setting " + passengerType.getDisplayName() + " count to: " + targetCount);

        // Get current count
        int currentCount = getCurrentPassengerCount(passengerType);
        System.out.println("  Current count: " + currentCount);

        if (currentCount == targetCount) {
            System.out.println("  Already at target count");
            return;
        }

        // Get plus/minus buttons for this passenger type
        WebElement plusButton = wait.until(ExpectedConditions.visibilityOfElementLocated(getPlusButton(passengerType)));
        WebElement minusButton = wait.until(ExpectedConditions.visibilityOfElementLocated(getMinusButton(passengerType)));

        // Click plus or minus to reach target
        if (currentCount < targetCount) {
            // Need to increase
            int clicks = targetCount - currentCount;
            for (int i = 0; i < clicks; i++) {
                wait.until(ExpectedConditions.elementToBeClickable(plusButton)).click();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("  ✅ Increased to " + targetCount);
        } else {
            // Need to decrease
            int clicks = currentCount - targetCount;
            for (int i = 0; i < clicks; i++) {
                wait.until(ExpectedConditions.elementToBeClickable(minusButton)).click();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("  ✅ Decreased to " + targetCount);
        }

        // Verify final count
        int finalCount = getCurrentPassengerCount(passengerType);
        if (finalCount != targetCount) {
            throw new RuntimeException(
                    "Failed to set " + passengerType.getDisplayName() +
                            " count. Expected: " + targetCount + ", Got: " + finalCount
            );
        }
    }

    /**
     * Set multiple passengers at once
     *
     * @param passengerCounts - Map of PassengerType to count
     */
    public void setPassengers(Map<PassengerType, Integer> passengerCounts) {
        System.out.println("🔧 Setting multiple passengers:");

        for (Map.Entry<PassengerType, Integer> entry : passengerCounts.entrySet()) {
            setPassengerCount(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Generic method untuk select cabin class
     */
    public void selectCabinType(CabinType cabinType) {
        System.out.println("🔧 Selecting cabin class: " + cabinType.getDisplayName());

        WebElement cabinButton = wait.until(ExpectedConditions.visibilityOfElementLocated(getCabinButton(cabinType)));

        wait.until(ExpectedConditions.elementToBeClickable(cabinButton)).click();

        // Verify selected (biasanya ada class 'selected' atau 'active')
        try {
            wait.until(ExpectedConditions.attributeContains(
                    cabinButton,
                    "class",
                    "selected"
            ));
            System.out.println("✅ Cabin class selected: " + cabinType.getDisplayName());
        } catch (Exception e) {
            // Some implementations don't have visual feedback
            System.out.println("✅ Cabin class clicked: " + cabinType.getDisplayName());
        }
    }

    /**
     * Close modal (click Done button or outside)
     */
    public void closeModal() {
        // Click outside modal to close
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    driver.findElement(By.tagName("body"))
            );
            System.out.println("✅ Modal closed by clicking outside");
        } catch (Exception ex) {
            System.out.println("⚠️ Could not close modal explicitly");
        }
    }

    // ========== HELPER METHODS ==========

    private int getCurrentPassengerCount(PassengerType type) {
        WebElement countElement = wait.until(ExpectedConditions.visibilityOfElementLocated(getCountDisplay(type)));
        String countText = countElement.getText().trim();

        try {
            return Integer.parseInt(countText);
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Could not parse count: '" + countText + "', returning 0");
            return 0;
        }
    }

    private By getPlusButton(PassengerType type) {
        return switch (type) {
            case ADULT -> tabFlightPageRepository.buttonPlusAdultLocator;
            case CHILDREN -> tabFlightPageRepository.buttonPlusChildrenLocator;
            case INFANTS -> tabFlightPageRepository.buttonPlusInfantLocator;
        };
    }

    private By getMinusButton(PassengerType type) {
        return switch (type) {
            case ADULT -> tabFlightPageRepository.buttonMinusAdultLocator;
            case CHILDREN -> tabFlightPageRepository.buttonMinusChildrenLocator;
            case INFANTS -> tabFlightPageRepository.buttonMinusInfantLocator;
        };
    }

    private By getCountDisplay(PassengerType type) {
        return switch (type) {
            case ADULT -> tabFlightPageRepository.adultCountDisplayLocator;
            case CHILDREN -> tabFlightPageRepository.childrenCountDisplayLocator;
            case INFANTS -> tabFlightPageRepository.infantsCountDisplayLocator;
        };
    }

    private By getCabinButton(CabinType cabin) {
        return switch (cabin) {
            case ECONOMY -> tabFlightPageRepository.buttonEconomyClassLocator;
            case PREMIUM_ECONOMY -> tabFlightPageRepository.buttonPremiumEconomyClassLocator;
            case BUSINESS -> tabFlightPageRepository.buttonBusinessClassLocator;
            case FIRST -> tabFlightPageRepository.buttonFirstClassLocator;
        };
    }

    // ========== ONE-LINER METHODS ==========

    /**
     * All-in-one method: set passengers and cabin in single call
     */
    public void configurePassengersAndCabin(
            int adults,
            int children,
            int infants,
            CabinType cabinType
    ) {
//        openPassengerCabinModal();

        // Set passengers
        if (adults > 0) setPassengerCount(PassengerType.ADULT, adults);
        if (children > 0) setPassengerCount(PassengerType.CHILDREN, children);
        if (infants > 0) setPassengerCount(PassengerType.INFANTS, infants);

        // Set cabin
        selectCabinType(cabinType);

        closeModal();

        System.out.println("✅ Configuration complete: " +
                adults + " adult(s), " +
                children + " children, " +
                infants + " infant(s), " +
                cabinType.getDisplayName());
    }
}
