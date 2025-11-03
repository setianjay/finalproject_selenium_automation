package com.setianjay.repository.bookingpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BookingPageRepository {
    public By priceInformationLocator = By.xpath("(//span[@data-component='mob-price-desc-text'])[last()]");

    public BookingPageRepository(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public By airlineLocator(String airline) {
        String AIRLINE_INFORMATION = "//div[@data-component='pacFlightDetail']//span[contains(text(), '%s')]";
        return By.xpath(String.format(AIRLINE_INFORMATION, airline));
    }
}
