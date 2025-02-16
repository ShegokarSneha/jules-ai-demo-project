package com.julesai.utilities;

import com.microsoft.playwright.Page;

public class BasePageObject {

    public Page page;

    public BasePageObject(Page page) {
        this.page = page;
    }

    public void navigateTo(String url){
        this.page.navigate(url);
    }

    public void enterInputValue(String locator, String value){
        page.locator(locator).fill(value);
    }

    public void pressKeys(String key){
        page.keyboard().press("Tab");
    }

    public void click(String locator){
        page.click(locator);
    }

    public String getText(String locator){
        return page.locator(locator).innerText();
    }

    public boolean waitForElementToVisible(String locator, double duration){
       return page.waitForSelector(locator, (new Page.WaitForSelectorOptions().setTimeout(duration))).isVisible();
    }

    public void captureScreenshot(String message){
        Screenshots.addStepWithScreenshotInReport(this.page, message);
    }
}
