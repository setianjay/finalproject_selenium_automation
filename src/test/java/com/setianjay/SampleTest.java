package com.setianjay;

import com.setianjay.enums.CabinType;
import com.setianjay.enums.TabLandingPage;
import com.setianjay.page.bookingpage.BookingPage;
import com.setianjay.page.flightpage.FlightSearchPage;
import com.setianjay.page.landingpage.LandingPage;
import com.setianjay.page.landingpage.TabFlightPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class SampleTest {

    @Test
    public void testTabFlight() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        LandingPage landingPage = new LandingPage(driver);
        TabFlightPage tabFlightPage = new TabFlightPage(driver);
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        BookingPage bookingPage = new BookingPage(driver);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
//        driver.manage().timeouts().implicitlyWait(Duration.of(10L, ChronoUnit.SECONDS));
        driver.get("https://www.agoda.com/");

        // 1. Click Tab flight
        landingPage.clickTab(TabLandingPage.TAB_FLIGHT.getTabName());
        Thread.sleep(3000L);

        // 2. Make sure the order is one way

        // 3. User fill input flying from
        tabFlightPage.fillFlyingFrom("Jakarta");
        // 3.1. User select the auto suggest
        tabFlightPage.selectAutoSuggestByPosition(0);

        // 4. User fill input flying to
        tabFlightPage.fillFlyingTo("Singapore");
        // 4.1. User select the auto suggest
        tabFlightPage.selectAutoSuggestByPosition(0);

        Thread.sleep(3000L);

        // 5. User select departure date with next day
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        tabFlightPage.selectDepartureDate(tomorrow);

        // 6. User set the total of passenger and select the class of the flight
        tabFlightPage.configurePassengersAndCabin(1, 0, 0, CabinType.ECONOMY);

        // 7. User click button search flight
        landingPage.clickButtonSearch();

        // 8. User chose Air Asia
        flightSearchPage.waitUntilContainerFlightSearchPageIsAvailable();
        flightSearchPage.selectAirline("AirAsia");
        Thread.sleep(3000L);
//        flightSearchPage.waitUntilAirlineIsSelected();

        // 9. User pilih tiket penerbangan paling murah lalu get maskapai dan harganya untuk di validasi pada halaman order detail
        flightSearchPage.selectSortBy("Cheapest first");
        Thread.sleep(3000L);
        flightSearchPage.selectFlightByPosition(0);
        Thread.sleep(3000L);


        // 10. Move to order detail
        flightSearchPage.clickBookButton();
        Thread.sleep(3000L);

        // 11. Verifikasi maskapai yang dipilih sudah sesuai atau belum
//        bookingPage.validateAirline(flightSearchPage.getYourAirline());
        Thread.sleep(3000L);
        // 12. Verifikasi total harga yang dipilih sudah sesuai atau belum
//        bookingPage.validatePrice(flightSearchPage.getYourPrice());

        driver.quit();
    }
}
