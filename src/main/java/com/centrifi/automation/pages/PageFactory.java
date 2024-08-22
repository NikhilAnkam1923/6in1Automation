package com.centrifi.automation.pages;

public class PageFactory {
    private static ThreadLocal<PageFactory> factory = new ThreadLocal<>();
    public LoginPage loginPage;

    private PageFactory() {
        loginPage = new LoginPage();
    }

    public static void init() {
        if (factory.get() == null) {
            PageFactory pageFactory = new PageFactory();
            factory.set(pageFactory);
        }
    }

    public static LoginPage loginPage() {
        return factory.get().loginPage;
    }
}
