package com.julesai.factory;

import com.julesai.configprovider.ConfigProvider;
import com.julesai.pageobjectmanager.PageObjectManager;
import com.microsoft.playwright.*;

public abstract class AbstractSteps {

    Browser browser;
    Playwright playwright;
    BrowserContext browserContext;
    private static Page page;
    protected static PageObjectManager pageObjectManager;


    public void startDriver() {
        playwright = Playwright.create();
        if (ConfigProvider.getAsString("browser").equalsIgnoreCase("chromium")) {
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(ConfigProvider.getAsString("headless"))));
        } else if (ConfigProvider.getAsString("browser").equalsIgnoreCase("firefox")) {
            browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(ConfigProvider.getAsString("headless"))));
        }else if (ConfigProvider.getAsString("browser").equalsIgnoreCase("safari")) {
            browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(ConfigProvider.getAsString("headless"))));
        }else if (ConfigProvider.getAsString("browser").equalsIgnoreCase("chrome")) {
            browser = playwright.chromium().launch((new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(Boolean.parseBoolean(ConfigProvider.getAsString("headless")))));
        }
        browserContext = browser.newContext((new Browser.NewContextOptions()));
        page = browserContext.newPage();
        pageObjectManager = new PageObjectManager(page);
    }
}
