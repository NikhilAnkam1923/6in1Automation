package com.centrifi.automation.pages;

public class PageFactory {
    private static ThreadLocal<PageFactory> factory = new ThreadLocal<>();
    public LoginPage loginPage;
    private AddClientPage addClientPage;
    private CampaignPage campaignPage;
    private CampaignNewPage campaignNewPage;

    private PageFactory() {

        loginPage = new LoginPage();
        addClientPage = new AddClientPage();
        campaignPage= new CampaignPage();
        campaignNewPage = new CampaignNewPage();
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

    public static CampaignPage campaignPage() {
        return factory.get().campaignPage;
    }

    public static CampaignNewPage campaignNewPage(){
        return factory.get().campaignNewPage;
    }
}
