package com.julesai.pageobjectmanager;

import com.julesai.pages.LoginPage;
import com.microsoft.playwright.Page;

public class PageObjectManager {
    private final Page page;
    private LoginPage loginPage;

    public PageObjectManager(Page page){
        this.page = page;
    }

    public LoginPage getLoginPage(){
        return (loginPage == null) ? loginPage = new LoginPage(page) : loginPage;
    }
}
