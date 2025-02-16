package com.julesai.stepdefinition;

import com.julesai.configprovider.ConfigProvider;
import com.julesai.factory.AbstractSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class ShadowXpathStepDefinition extends AbstractSteps {

    @Given("User launches the browser with shadow url")
    public void user_launches_the_browser_with_shadow_url() {
        startDriver();
        System.out.println("URL : " + ConfigProvider.getAsString("shadow_xpath_url"));
        pageObjectManager.getShadowXpathPage().openUrl(ConfigProvider.getAsString("shadow_xpath_url"));
    }

    @And("User enter value in shadowDom Element")
    public void userEnterValueInShadowDomElement() {
        pageObjectManager.getShadowXpathPage().enterTextInPizzaNameTextBox();
    }
}
