package com.sixinone.automation.pages;

import com.sixinone.automation.glue.EstateContactsSteps;

import java.io.IOException;

public class PageFactory {
    private static ThreadLocal<PageFactory> factory = new ThreadLocal<>();
    public LoginPage loginPage;
    public GlobalContactPage globalContactPage;
    public EstateCreationPage estateCreationPage;
    public EstateContactsPage estateContactsPage;
    public ProbateFormsRW03Page probateFormsRW03Page;

    private PageFactory() throws IOException {

        loginPage = new LoginPage();
        globalContactPage = new GlobalContactPage();
        estateCreationPage = new EstateCreationPage();
        estateContactsPage = new EstateContactsPage();
        probateFormsRW03Page = new ProbateFormsRW03Page();
    }

    public static void init() throws IOException {
        if (factory.get() == null) {
            PageFactory pageFactory = new PageFactory();
            factory.set(pageFactory);
        }
    }

    public static LoginPage loginPage() {
        return factory.get().loginPage;
    }

    public static GlobalContactPage globalContactPage() {
        return factory.get().globalContactPage;
    }

    public static EstateCreationPage estateCreationPage() {
        return factory.get().estateCreationPage;
    }

    public static EstateContactsPage estateContactsPage() {
        return factory.get().estateContactsPage;
    }

    public static ProbateFormsRW03Page probateFormsRW03Page() {
        return factory.get().probateFormsRW03Page;
    }


}


