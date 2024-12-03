package com.sixinone.automation.pages;

public class PageFactory {
    private static ThreadLocal<PageFactory> factory = new ThreadLocal<>();
    public LoginPage loginPage;
    public FirmCreationPage firmCreationPage;


    private PageFactory() {

        loginPage = new LoginPage();
        firmCreationPage = new FirmCreationPage();

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

    public static FirmCreationPage firmCreationPage() {
        return factory.get().firmCreationPage;
    }

}


