package com.sixinone.automation.pages;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class PageFactory {
    private static ThreadLocal<PageFactory> factory = new ThreadLocal<>();
    public LoginPage loginPage;
    public GlobalContactPage globalContactPage;
    public EstateCreationPage estateCreationPage;
    public EstateContactsPage estateContactsPage;
    public ProbateFormsRW03Page probateFormsRW03Page;
    public ProbateFormsRW01Page probateFormsRW01Page;
    public ProbateFormsRW02Page probateFormsRW02Page;
    public ProbateFormsRW04Page probateFormsRW04Page;
    public ProbateFormsRW05Page probateFormsRW05Page;
    public ProbateFormsRW06Page probateFormsRW06Page;
    public ProbateFormsRWxxPage probateFormsRWxxPage;
    public ProbateFormsRW07Page probateFormsRW07Page;
    public ProbateFormsRW08Page probateFormsRW08Page;
    public ProbateFormsRW10Page probateFormsRW10Page;
    public ProbateFormsOC01Page probateFormsOC01Page;
    public ProbateFormsOC02Page probateFormsOC02Page;

    private PageFactory() throws IOException, ParseException {

        loginPage = new LoginPage();
        globalContactPage = new GlobalContactPage();
        estateCreationPage = new EstateCreationPage();
        estateContactsPage = new EstateContactsPage();
        probateFormsRW03Page = new ProbateFormsRW03Page();
        probateFormsRW01Page =new ProbateFormsRW01Page();
        probateFormsRW02Page =new ProbateFormsRW02Page();
        probateFormsRW04Page =new ProbateFormsRW04Page();
        probateFormsRW05Page =new ProbateFormsRW05Page();
        probateFormsRW06Page =new ProbateFormsRW06Page();
        probateFormsRWxxPage =new ProbateFormsRWxxPage();
        probateFormsRW07Page =new ProbateFormsRW07Page();
        probateFormsRW08Page =new ProbateFormsRW08Page();
        probateFormsRW10Page =new ProbateFormsRW10Page();
        probateFormsOC01Page =new ProbateFormsOC01Page();
        probateFormsOC02Page =new ProbateFormsOC02Page();
    }

    public static void init() throws IOException, ParseException {
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

    public static ProbateFormsRW01Page probateFormsRW01Page() {
        return factory.get().probateFormsRW01Page;
    }

    public static ProbateFormsRW02Page probateFormsRW02Page() {
        return factory.get().probateFormsRW02Page;
    }

    public static ProbateFormsRW04Page probateFormsRW04Page() {
        return factory.get().probateFormsRW04Page;
    }

    public static ProbateFormsRW05Page probateFormsRW05Page() {
        return factory.get().probateFormsRW05Page;
    }

    public static ProbateFormsRW06Page probateFormsRW06Page() {
        return factory.get().probateFormsRW06Page;
    }

    public static ProbateFormsRWxxPage probateFormsRWxxPage() {
        return factory.get().probateFormsRWxxPage;
    }

    public static ProbateFormsRW07Page probateFormsRW07Page() { return factory.get().probateFormsRW07Page; }

    public static ProbateFormsRW08Page probateFormsRW08Page() { return factory.get().probateFormsRW08Page; }

    public static ProbateFormsRW10Page probateFormsRW10Page() { return factory.get().probateFormsRW10Page; }

    public static ProbateFormsOC01Page probateFormsOC01Page() { return factory.get().probateFormsOC01Page; }

    public static ProbateFormsOC02Page probateFormsOC02Page() { return factory.get().probateFormsOC02Page; }

}


