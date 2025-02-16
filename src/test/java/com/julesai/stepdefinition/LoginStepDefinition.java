package com.julesai.stepdefinition;

import com.julesai.configprovider.ConfigProvider;
import com.julesai.factory.AbstractSteps;
import com.julesai.utilities.Screenshots;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinition extends AbstractSteps {

    @Given("User launches the browser with url")
    public void user_launches_the_browser_with_url() {
        startDriver();
        System.out.println("URL : " + ConfigProvider.getAsString("application_url"));
        pageObjectManager.getLoginPage().openUrl(ConfigProvider.getAsString("application_url"));
    }

    @When("User enters email and password and Clicks on Login")
    public void user_enters_email_and_password_and_clicks_on_login() {
        pageObjectManager.getLoginPage().enterEmail(ConfigProvider.getAsString("email"));
        Screenshots.addStepInReport(true, "Email Entered " + ConfigProvider.getAsString("email"));
        pageObjectManager.getLoginPage().captureScreenshot("URl Opened : " + ConfigProvider.getAsString("application_url"));
        pageObjectManager.getLoginPage().enterPassword(ConfigProvider.getAsString("password"));
        Screenshots.addStepInReport(true, "Password Entered " + ConfigProvider.getAsString("email"));
        pageObjectManager.getLoginPage().clickOnLoginButton();
    }

    @Then("Verify user logged in successfully")
    public void verifyUserLoggedInSuccessfully() {
        pageObjectManager.getLoginPage().verifyHeadingText("Purchase & Opportunity list");
        pageObjectManager.getLoginPage().captureScreenshot("User Logged in Successfully");
    }

    @When("User not enters email and password and Clicks on Login")
    public void userNotEntersEmailAndPasswordAndClicksOnLogin() {
        pageObjectManager.getLoginPage().clickOnLoginButton();
    }

    @And("Verify error message as {string} for email field")
    public void verifyErrorMessageAsForEmailField(String errorMessage) {
        String actualMessage = pageObjectManager.getLoginPage().verifyEmailErrorMessage(errorMessage);
        Screenshots.addStepInReport(actualMessage.equals(errorMessage), "Error Message Displayed for Email </br><b>Actual Error: </b>" + actualMessage + " </br><b>Expected Error: </b>" + errorMessage);
    }

    @Then("Verify error message for {string} password field")
    public void verifyErrorMessageForPasswordField(String errorMessage) {
        String actualMessage = pageObjectManager.getLoginPage().verifyPasswordErrorMessage();
        Screenshots.addStepInReport(actualMessage.equals(errorMessage), "Error Message Displayed for Password </br><b> Actual Error: </b>" + actualMessage + " </br><b>Expected Error: </b>" + errorMessage);
        pageObjectManager.getLoginPage().captureScreenshot("");
    }

    @When("User enters email as {string}")
    public void userEntersEmailAs(String email) {
        pageObjectManager.getLoginPage().enterEmail(email);
    }

    @And("User enters password as {string}")
    public void userEntersPasswordAs(String password) {
        pageObjectManager.getLoginPage().enterPassword(password);
    }

    @And("User Clicks on Login")
    public void userClicksOnLogin() {
        pageObjectManager.getLoginPage().clickOnLoginButton();
    }

    @And("Verify error message as {string}")
    public void verifyErrorMessageAs(String message) {
        String actualMessage = pageObjectManager.getLoginPage().verifyToasterMessage();
        Screenshots.addStepInReport(actualMessage.equals(message), "Error Message Displayed </br><b>Actual Error : </b>" + actualMessage + "</br><b> Expected Error : </b>" + message);
        pageObjectManager.getLoginPage().captureScreenshot("");
    }
}
