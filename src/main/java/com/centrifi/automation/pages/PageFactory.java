package com.centrifi.automation.pages;

public class PageFactory {
    private static ThreadLocal<PageFactory> factory = new ThreadLocal<>();
    public LoginPage loginPage;
    private AddClientPage addClientPage;
    private AuthorizationsPage authorizationsPage;
    private AddClientIntegrationPage addClientIntegrationPage;

    private PageFactory() {

        loginPage = new LoginPage();
        addClientPage = new AddClientPage();
        authorizationsPage=new AuthorizationsPage();
        addClientIntegrationPage=new AddClientIntegrationPage();
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
    public static AuthorizationsPage authorizationsPage() {
        return factory.get().authorizationsPage;
    }
    public static AddClientIntegrationPage addClientIntegrationPage() {
        return factory.get().addClientIntegrationPage;
    }
}
