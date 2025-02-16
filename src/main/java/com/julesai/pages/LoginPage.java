package com.julesai.pages;

import com.julesai.utilities.BasePageObject;
import com.microsoft.playwright.Page;
import org.testng.Assert;

public class LoginPage extends BasePageObject {
    public LoginPage(Page page) {
        super(page);
    }

    private String email = "//input[@name='email']";
    private String password = "//input[@name='password']";
    private String loginbutton = "//button[@data-test-id='signin']/span[1]";
    private String toasterMessage = "//*[@data-test-id='toaster-message']";
    private String firstRow = "//*[@class='MuiTableRow-root MuiTableRow-hover'][1]";
    private String purchaseListTitle = "//*[@class='sc-kDDrLX kWzRMQ']//preceding::div[1]";
    private String errorMessageForEmail = "//*[@data-test-id='input-email']/following::div[text()='REPLACE_VALUE'][1]";
    private String errorMessageForPassword = "//*[@data-test-id='input-password']/following::div[1]";

    public void openUrl(String url) {
        navigateTo(url);
    }

    public void enterEmail(String emailValue) {
        enterInputValue(email, emailValue);
    }

    public void enterPassword(String passwordValue) {
        enterInputValue(password, passwordValue);
        pressKeys("Tab");
    }

    public void clickOnLoginButton() {
        click(loginbutton);
    }

    public void verifyHeadingText(String text) {
        waitForElementToVisible(firstRow, 20000);
        Assert.assertEquals(getText(purchaseListTitle), text, "User Logged in successfully.");
    }

    public String verifyEmailErrorMessage(String errorMessage) {
        String message = "";
        if(page.isVisible(errorMessageForEmail.replace("REPLACE_VALUE", errorMessage))){
            return getText(errorMessageForEmail.replace("REPLACE_VALUE", errorMessage));
        }
        return message;
    }

    public String verifyPasswordErrorMessage() {
        return getText(errorMessageForPassword);
    }

    public String verifyToasterMessage() {
        return getText(toasterMessage);
    }


}
