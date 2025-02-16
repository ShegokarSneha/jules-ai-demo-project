package com.julesai.pages;

import com.julesai.utilities.BasePageObject;
import com.julesai.utilities.Screenshots;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ShadowXpathPage extends BasePageObject {
    public ShadowXpathPage(Page page) {
        super(page);
    }

    public void openUrl(String url) {
        navigateTo(url);
    }

    public void enterTextInPizzaNameTextBox(){
        Locator firstShadowRoot = page.locator("div#userName");
        Locator secondShadowRoot = firstShadowRoot.locator("div#app2");
        secondShadowRoot.locator("input#pizza").fill("Test Pizza");
        captureScreenshot("Shadow XPath found Value Entered : "+ "Test Pizza");
    }
}
