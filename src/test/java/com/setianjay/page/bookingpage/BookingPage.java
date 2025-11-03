package com.setianjay.page.bookingpage;

import com.setianjay.page.base.BasePage;
import com.setianjay.repository.bookingpage.BookingPageRepository;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BookingPage extends BasePage {
    private final BookingPageRepository bookingPageRepository;

    public BookingPage(WebDriver driver) {
        super(driver);
        bookingPageRepository = new BookingPageRepository(driver);
    }

    public Boolean validatePrice(String price) {
        String orderPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(bookingPageRepository.priceInformationLocator)).getText();
        System.out.println("💵 Order Price: " + orderPrice);
        return orderPrice.contains(price);
    }

    public Boolean validateAirline(String airline) {
        String orderAirline = wait.until(ExpectedConditions.visibilityOfElementLocated(bookingPageRepository.airlineLocator(airline))).getText();
        System.out.println("✈ Order Airline: " + orderAirline);
        return orderAirline.contains(airline);
    }
}
