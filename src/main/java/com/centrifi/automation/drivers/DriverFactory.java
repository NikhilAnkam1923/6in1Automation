package com.centrifi.automation.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

public class DriverFactory {
    private static final Logger LOGGER = Logger.getLogger(DriverFactory.class.getName());
    public String downloadPath = ((System.getProperty(OS)==null || System.getProperty(OS)==WINDOWS)?System.getProperty("user.dir"):System.getProperty("user.dir").replace("\\", "/"))+"/downloads";
    public static final String DEFAULT_BROWSER = "chrome";
    public static final String WINDOWS = "windows";
    public static final String LINUX = "linux";
    public static final String MACOS = "ios";
    public static final String OS = "OS";
    public static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    private DriverFactory() {
        if (System.getProperty(OS)== null)
            System.setProperty(OS,WINDOWS);
    }

    private static DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance() {
        return instance;
    }

    public WebDriver initDriver(String browser) {
        System.setProperty("java.awt.headless", "false");
        WebDriver driver = drivers.get();
        if(driver==null) {
            if (browser == null || browser.isEmpty())
                browser = DEFAULT_BROWSER;

            if(System.getProperty(OS).equalsIgnoreCase("linux")) {
                File file = executableDriver(System.getProperty(OS), browser);
                System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                driver = new ChromeDriver(chromeOptions());
            } else {
                switch (browser) {
                    default:
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver(chromeOptions());
                        break;
                }
            }
        }
        drivers.set(driver);
        return driver;
    }

    public EdgeOptions edgeOptions() {
        EdgeOptions edgeOptions =new EdgeOptions();
        edgeOptions.setCapability("UseChromium", true);
        if(System.getProperty("debug")==null || System.getProperty("debug").equalsIgnoreCase("false"))
            edgeOptions.addArguments("--headless");
        return edgeOptions;
    }

    public ChromeOptions chromeOptions() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put( "profile.default_content_setting_values.automatic_downloads", 1 );
        chromePrefs.put("download.default_directory", downloadPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.setExperimentalOption("prefs", chromePrefs);
        if(System.getProperty("debug")==null || System.getProperty("debug").equalsIgnoreCase("false")) {
            if(LINUX.equalsIgnoreCase(System.getProperty(OS)))
                options.setBinary("/usr/bin/google-chrome");
            options.addArguments("--no-sandbox");
            options.addArguments("--headless");
            options.addArguments("--disable-gup");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("force-device-scale-factor=0.75");
            options.addArguments("high-dpi-support=0.75");
        }
        return options;
    }

    public File executableDriver(String os, String browser) {
        File file=null;
        switch (browser) {
            case "firefox":
                if(os.equalsIgnoreCase(LINUX))
                    file = new File("src/test/resources/web-drivers/linux/geckodriver");
                else if(os.equalsIgnoreCase(MACOS))
                    file = new File("src/test/resources/web-drivers/iOS/geckodriver");
            case "edge":
                if(os.equalsIgnoreCase(LINUX))
                    file = new File("src/test/resources/web-drivers/linux/edgedriver");
                else if(os.equalsIgnoreCase(MACOS))
                    file = new File("src/test/resources/web-drivers/iOS/edgedriver");
            case "opera":
                if(os.equalsIgnoreCase(LINUX))
                    file = new File("src/test/resources/web-drivers/linux/operadriver");
                else if(os.equalsIgnoreCase(MACOS))
                    file = new File("src/test/resources/web-drivers/iOS/operadriver");
            default:
                if(os.equalsIgnoreCase(LINUX))
                    file = new File("src/test/resources/web-drivers/linux/chromedriver");
                else if(os.equalsIgnoreCase(MACOS))
                    file = new File("src/test/resources/web-drivers/iOS/chromedriver");
        }
        return file;
    }

    public static void clearDriverSession() {
        drivers.set(null);
    }
}