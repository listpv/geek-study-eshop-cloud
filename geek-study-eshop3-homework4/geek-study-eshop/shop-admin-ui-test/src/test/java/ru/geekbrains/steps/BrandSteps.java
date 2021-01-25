package ru.geekbrains.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.DriverInitializer;

import static org.assertj.core.api.Assertions.assertThat;

public class BrandSteps {

    private WebDriver webDriver = null;

    @Given("^I open web browser$")
    public void iOpenFirefoxBrowser() throws Throwable {
        webDriver = DriverInitializer.getDriver();
    }

    @When("^I navigate to login page$")
    public void iNavigateToLoginHtmlPage() throws Throwable {
        webDriver.get(DriverInitializer.getProperty("login.url"));
    }


    @When("^I provide username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void iProvideUsernameAsAndPasswordAs(String username, String password) throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("inp-username"));
        webElement.sendKeys(username);
        Thread.sleep(2000);
        webElement = webDriver.findElement(By.id("inp-password"));
        webElement.sendKeys(password);
        Thread.sleep(2000);
    }

    @When("^I click on login button$")
    public void iClickOnLoginButton() throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("btn-login"));
        webElement.click();
    }

    @When("^I click on brand li$")
    public void iClickOnBrandLi() throws Throwable{
        WebElement webElement = webDriver.findElement(By.cssSelector("body > div > div.sidebar.sidebar-dark.bg-dark > ul > li:nth-child(4) > a"));
        Thread.sleep(1000);
        webElement.click();
    }

    @When("^I click on create button$")
    public void iClickOnCreateButton() throws Throwable{
        WebElement webElement = webDriver.findElement(By.cssSelector("body > div > div.content.p-4 > div > div > form > button"));
        Thread.sleep(1000);
        webElement.click();
    }

    @Then("^name should be \"([^\"]*)\"$")
    public void nameShouldBe(String name) throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("dd_user"));
        assertThat(webElement.getText()).isEqualTo(name);
    }

    @When("^I provide brand as \"([^\"]*)\"$")
    public void iProvideBrandAs(String brand) throws Throwable{
        WebElement webElement = webDriver.findElement(By.id("name"));
        Thread.sleep(1000);
//        webElement.sendKeys("adidas");
        webElement.sendKeys(brand);
        Thread.sleep(2000);

    }

    @When("^I click on submit button$")
    public void iClickOnSubmitButton() throws Throwable{
        WebElement webElement = webDriver.findElement(By.cssSelector("body > div > div.content.p-4 > form > div > div.card-footer.bg-white > button"));
        Thread.sleep(1000);
        webElement.click();
        Thread.sleep(5000);
    }

    @Then("^brand should be \"([^\"]*)\"$")
    public void brandShouldBe(String brand) throws Throwable{
        int i = 1;
        while (true){
            try {
                webDriver.findElement(By.cssSelector("#brands > tbody > tr:nth-child(" + i + ")"));
                i++;
            }catch (Exception e){
                break;
            }
        }
        WebElement webElement = webDriver.findElement(By.cssSelector("#brands > tbody > tr:nth-child(" + (i - 1) + ") > td:nth-child(2)"));
        assertThat(webElement.getText()).isEqualTo(brand);
//        assertThat(webElement.getText()).isEqualTo("adidas");

    }

    @After
    public void quitBrowser() {
        webDriver.quit();
    }
}
