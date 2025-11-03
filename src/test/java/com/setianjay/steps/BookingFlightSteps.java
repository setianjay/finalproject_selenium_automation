package com.setianjay.steps;

import com.setianjay.context.TestContext;
import com.setianjay.enums.CabinType;
import com.setianjay.enums.TabLandingPage;
import com.setianjay.page.bookingpage.BookingPage;
import com.setianjay.page.flightpage.FlightSearchPage;
import com.setianjay.page.landingpage.LandingPage;
import com.setianjay.page.landingpage.TabFlightPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BookingFlightSteps {
    private WebDriver driver;
    private final TestContext testContext;
    private LandingPage landingPage;
    private TabFlightPage tabFlightPage;
    private FlightSearchPage flightSearchPage;
    private BookingPage bookingPage;
    private final String FLIGHT_PRICE_KEY = "flight_price_key";
    private final String FLIGHT_AIRLINE_KEY = "flight_airline_key";

    public BookingFlightSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        this.testContext.setDriver(driver);
    }

    @Given("user navigate to Agoda homepage")
    public void userNavigateToAgodaHomepage() {
        driver.get("https://www.agoda.com/");
        landingPage = new LandingPage(testContext.getDriver());
    }

    @When("user click on Flight tab")
    public void userClickOnFlightTab() throws InterruptedException {
        landingPage.clickTab(TabLandingPage.TAB_FLIGHT.getTabName());
        Thread.sleep(3000L);
    }

    @And("user enter origin {string}")
    public void userEnterOrigin(String origin) throws InterruptedException {
        tabFlightPage = new TabFlightPage(testContext.getDriver());
        tabFlightPage.fillFlyingFrom(origin);
        Thread.sleep(1500L);
        tabFlightPage.selectAutoSuggestByPosition(0);
    }

    @And("user enter destination {string}")
    public void userEnterDestination(String destination) throws InterruptedException {
        tabFlightPage.fillFlyingTo(destination);
        Thread.sleep(1500L);
        tabFlightPage.selectAutoSuggestByPosition(0);
        Thread.sleep(3000L);
    }

    @And("user select tomorrow's departure date")
    public void userSelectTomorrowDepartureDate() throws InterruptedException {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        tabFlightPage.selectDepartureDate(tomorrow);
    }

    @And("user set for {int} adult, {int} children, and {int} infants passenger with {string} class")
    public void userSetFor1PassengerWithEconomyClass(int adult, int children, int infants, String cabin) throws InterruptedException {
        tabFlightPage.configurePassengersAndCabin(adult, children, infants, CabinType.fromDisplayName(cabin));
    }

    @And("user click search button")
    public void userClickSearchButton() throws InterruptedException {
        landingPage.clickButtonSearch();
    }

    @And("user see the flight result page")
    public void userSeeTheFlightResultPage() throws InterruptedException {
        flightSearchPage = new FlightSearchPage(testContext.getDriver());
        flightSearchPage.waitUntilContainerFlightSearchPageIsAvailable();
    }

    @And("user choose {string} airline")
    public void userChooseAirline(String airline) throws InterruptedException {
        flightSearchPage.selectAirline(airline);
        Thread.sleep(3000L);
    }

    @And("user sort the list with the {string}")
    public void userSortTheListWithTheCheapestPrice(String sortBy) throws InterruptedException {
        flightSearchPage.selectSortBy(sortBy);
        Thread.sleep(3000L);
    }

    @And("user select the cheapest flight")
    public void userSelectTheCheapestFlight() throws InterruptedException {
        WebElement flightCard = flightSearchPage.selectFlightByPosition(0);
        // Get price from THIS flight card
        String flightPrice = flightSearchPage.getFlightPriceByFlightCard(flightCard);
        String flightAirline = flightSearchPage.getFlightAirlineByFlightCard(flightCard);
        testContext.set(FLIGHT_PRICE_KEY, flightPrice);
        testContext.set(FLIGHT_AIRLINE_KEY, flightAirline);
        Thread.sleep(3000L);
        flightSearchPage.clickBookButton();
    }

    @Then("user see the flight booking order page and validate the airline and the price")
    public void userSeeTheFlightBookingOrderPage() throws InterruptedException {
        bookingPage = new BookingPage(testContext.getDriver());
        assertTrue(bookingPage.validatePrice((String) testContext.get(FLIGHT_PRICE_KEY)));
        assertTrue(bookingPage.validateAirline((String) testContext.get(FLIGHT_AIRLINE_KEY)));
    }

    @Then("user see a notification with the message {string}")
    public void userSeeANotificationWithMessage(String message) throws InterruptedException {
        assertEquals(landingPage.getBoxModalMessage(), message);
        Thread.sleep(3000L);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
