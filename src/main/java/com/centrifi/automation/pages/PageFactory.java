package com.centrifi.automation.pages;

public class PageFactory {
    private static ThreadLocal<PageFactory> factory = new ThreadLocal<>();
    public LoginPage loginPage;
    private AddClientPage addClientPage;

    private PageFactory() {

        loginPage = new LoginPage();
        addClientPage = new AddClientPage();
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

    public static AddClientPage addClientPage() {
        return factory.get().addClientPage;
    }
}
