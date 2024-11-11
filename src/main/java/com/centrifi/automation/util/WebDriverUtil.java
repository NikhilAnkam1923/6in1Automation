package com.centrifi.automation.util;

import com.centrifi.automation.constants.Constants;
import com.centrifi.automation.drivers.DriverFactory;
import com.centrifi.automation.exception.AutomationException;
import com.centrifi.automation.pages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverUtil {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverUtil.class);

    public static final int NO_WAIT = 0;
    public static final int WAIT_1_SEC = 1;
    public static final int WAIT_2_SEC = 2;
    public static final int WAIT_3_SEC = 3;
    public static final int WAIT_5_SEC = 5;
    public static final int DEFAULT_ELEMENT_WAIT = 60;
    public static final int MAX_ELEMENT_WAIT = 90;
    public static final int MAX_WAIT_120 = 120;
    public static final int MAX_PAGE_LOADING_WAIT = 180;
    public static final String DEFAULT_PAGE_LOAD_TIMEOUT = "180";
    public static final String ADMIN_PORTAL_LOADING_INVISIBLE = "//div[@class='MuiBackdrop-root' and contains(@style,'hidden')]";



    public WebElement getWebElement(String locator) throws AutomationException {
        System.out.println("Locator : " + locator);
        return getWebElement(locator, DEFAULT_ELEMENT_WAIT);
    }

    public List<WebElement> getWebElements(String locator) throws AutomationException {
        try {
            return DriverFactory.drivers.get().findElements(By.xpath(locator));
        } catch (Exception e) {
            return null;
        }
    }

    public WebElement getWebElementAndScroll(String locator) throws AutomationException {
        return getWebElementAndScroll(locator, DEFAULT_ELEMENT_WAIT);
    }

    public void getWebElementAndScrollUp() throws AutomationException {
        if (DriverFactory.drivers.get() == null) {
            throw new AutomationException("Driver is not initialized!");
        } else {
            JavascriptExecutor jse = (JavascriptExecutor) DriverFactory.drivers.get();
            jse.executeScript("scroll(0, -250);");
            ;
        }


    }

    public WebElement getWebElementWithoutWait(String locator) throws AutomationException {
        return getWebElement(locator, NO_WAIT);
    }

    public WebElement getWebElementAndScrollWithoutWait(String locator) throws AutomationException {
        return getWebElementAndScroll(locator, NO_WAIT);
    }

    public WebElement getWebElement(String locator, int waitTime) throws AutomationException {
        return getWebElement(locator, waitTime, null);
    }

    public WebElement getWebElementAndScroll(String locator, int waitTime) throws AutomationException {
        return getWebElementAndScroll(locator, waitTime, null);
    }

    public WebElement getWebElement(String locator, int waitTime, String message) throws AutomationException {
        WebElement element = null;
        if (DriverFactory.drivers.get() == null)
            throw new AutomationException("Driver is not initialized!");
        if (applyWait(locator, waitTime))
            element = findElement(locator);
        if (element == null && message != null)
            throw new AutomationException(message);
        return element;
    }

    public WebElement getWebElementAndScroll(String locator, int waitTime, String message) throws AutomationException {
        if (DriverFactory.drivers.get() == null)
            throw new AutomationException("Driver is not initialized!");
        if (applyWait(locator, waitTime))
            return findElementAndScroll(locator);
        if (message != null)
            throw new AutomationException(message);
        return null;
    }

    public WebElement findElement(String locator) {
        try {
            WebElement element = DriverFactory.drivers.get().findElement(By.xpath(locator));
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    public WebElement findElementAndScroll(String locator) {
        try {
            WebElement element = DriverFactory.drivers.get().findElement(By.xpath(locator));
            JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();
            js.executeScript("arguments[0].scrollIntoView();", element);
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    public WebElement findElementAndScroll(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();
            js.executeScript("arguments[0].scrollIntoView();", element);
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    public void scrollTo(String element) {
        try {
            System.out.println("inside scroll to method " + element);
            JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();
            js.executeScript("window.scrollBy(0," + element + ")", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean applyWait(String locator, int waitTime) {
        try {
            if (waitTime == NO_WAIT)
                return true;
            WebDriverWait wait = new WebDriverWait(DriverFactory.drivers.get(), Duration.ofSeconds(waitTime));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public static void waitForAWhile() {
        waitForAWhile(1, TimeUnit.SECONDS);
    }

    public static void waitForAWhile(int waitTime) {
        waitForAWhile(waitTime, TimeUnit.SECONDS);
    }

    public static void waitForAWhile(int waitTime, TimeUnit unit) {
        try {
            if (unit.equals(TimeUnit.SECONDS))
                Thread.sleep(waitTime * 1000);
            if (unit.equals(TimeUnit.MINUTES))
                Thread.sleep(waitTime * 1000 * 60);
        } catch (Exception ex) {
            //DO nothing..
        }
    }

    public void waitForElement(By by) {
        waitForElement(by, NO_WAIT);
    }

    public void waitForElement(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverFactory.drivers.get(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public void moveToElementAndClick(WebElement element, int xOffset, int yOffset) {
        Actions action = new Actions(DriverFactory.drivers.get());
        action.moveToElement(element, xOffset, yOffset).click().build().perform();
    }

    public void moveToElementAndClick(WebElement element) {
        Actions action = new Actions(DriverFactory.drivers.get());
        action.moveToElement(element).perform();
        waitForAWhile(WAIT_1_SEC);
        element.click();
    }

    public static void waitForVisibleElement(By by) {
        waitForAWhile(1, TimeUnit.SECONDS);
        waitForVisibleElement(by, MAX_ELEMENT_WAIT);
    }

    public static void waitForVisibleElement(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverFactory.drivers.get(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public static void waitForElementClickable(By by) {
        waitForElementClickable(by, MAX_ELEMENT_WAIT);
    }

    public static void waitForElementClickable(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverFactory.drivers.get(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public static void waitForInvisibleElement(By by) {
        waitForInvisibleElement(by, DEFAULT_ELEMENT_WAIT);
    }

    public static void waitForInvisibleElement(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverFactory.drivers.get(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public static byte[] pngBytesToJpgBytes(byte[] pngBytes) {
        try {
            //create InputStream for ImageIO using png byte[]
            ByteArrayInputStream bais = new ByteArrayInputStream(pngBytes);
            //read png bytes as an image
            BufferedImage bufferedImage = ImageIO.read(bais);

            BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
                    bufferedImage.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
            //create OutputStream to write prepaired jpg bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //write image as jpg bytes
            ImageIO.write(newBufferedImage, "JPG", baos);
            //convert OutputStream to a byte[]
            return baos.toByteArray();
        } catch (Exception ex) {
            logger.error("Unable to resize screenshot..");
        }
        return pngBytes;
    }


    public void waitForLoadingPage() {
        WebDriverUtil.waitForInvisibleElement(By.xpath(BasePage.LOADING), MAX_PAGE_LOADING_WAIT);
        WebDriverUtil.waitForInvisibleElement(By.xpath(BasePage.LOADING_DATA), MAX_PAGE_LOADING_WAIT);
    }

    public String ignoreCase(String value) {
        return "translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='"
                + value.toLowerCase() + "'";
    }

    public String containsIgnoreCase(String value) {
        return "contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'"
                + value.toLowerCase() + "')";
    }

    public WebElement findElementByText(String text) throws AutomationException {
        WebElement element = getWebElement("//*[" + ignoreCase(text) + "]");
        if (element == null) {
            element = getWebElement("//*[starts-with(text(),'" + text + "')]");
        }
        return element;
    }

    public void findElementByTextAndClick(String text) throws AutomationException {
        WebElement element = getWebElement("//*[" + ignoreCase(text) + "]");
        if (element == null) {
            element = getWebElement("//*[starts-with(text(),'" + text + "')]");
        }
        element.click();
    }

    public boolean clickUsingJavaScript(String elementXpath) {
        boolean status = false;
        try {
            String javascript = String.format("document.evaluate(\"%s\",document,null,XPathResult.FIRST_ORDERED_NODE_TYPE,null).singleNodeValue.click();", elementXpath);
            ((JavascriptExecutor) DriverFactory.drivers.get()).executeScript(javascript);
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public WebElement getElementUsingJavascript(String elementXpath) {
        try {
            return (WebElement) ((JavascriptExecutor) DriverFactory.drivers.get()).executeScript(String.format("return document.evaluate( \"%s\" ,document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null ).singleNodeValue;", elementXpath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void waitForElementToBeClickable(String xpath) {
        if (System.getProperty(Constants.PAGE_LOAD_TIMEOUT) == null)
            System.setProperty(Constants.PAGE_LOAD_TIMEOUT, DEFAULT_PAGE_LOAD_TIMEOUT);
        try {
            WebDriverWait wait = new WebDriverWait(DriverFactory.drivers.get(), Duration.ofSeconds(Integer.parseInt(System.getProperty(Constants.PAGE_LOAD_TIMEOUT))));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            //waitForAWhile(DEFAULT_ELEMENT_WAIT);
        } catch (TimeoutException ex) {
        }
    }

    public void waitForLoaderToDisappear() throws AutomationException {
        waitForAWhile(1);
        String elementVisible = waitForElementNotVisible(MAX_WAIT_120, "//div[@role='progressbar']");
        if (elementVisible != null)
            throw new AutomationException("Loader is displayed, even after waiting for 120 seconds");
    }

    public static String waitForElementNotVisible(int timeOutInSeconds, String elementXPath) {
        if ((elementXPath == null) || elementXPath.isEmpty())
            return "Please provide valid element xpath!";
        try {
            (new WebDriverWait(DriverFactory.drivers.get(), Duration.ofSeconds(timeOutInSeconds)))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(elementXPath)));
            return null;
        } catch (TimeoutException e) {
            return "Element still visible";
        }
    }

    public static void scrollPageDown() {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();
        js.executeScript("window.scrollBy(0,250);");
    }

    public static void scrollPageUp() {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.drivers.get();
        js.executeScript("window.scrollBy(0,-250);");
    }

    public void switchToFrame(String xpath) throws AutomationException {
        try {
            DriverFactory.drivers.get().switchTo().frame(findElement(xpath));
        } catch (Exception ex) {
            throw new AutomationException("Unable to find iFrame with the given xpath: " + xpath);
        }
    }

    public void switchToDefault() {
        DriverFactory.drivers.get().switchTo().defaultContent();
    }

    public WebElement getElementByJavascript(String xpath) {
        String javascript = String.format("document.evaluate('%s',document,null,XPathResult.FIRST_ORDERED_NODE_TYPE,null).singleNodeValue;", xpath);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverFactory.drivers.get();
        WebElement element = (WebElement) jsExecutor.executeScript(javascript);
        return element;
    }

    public static void dragAndDrop(WebElement srcweelement, WebElement destelement) {
        Actions actions = new Actions(DriverFactory.drivers.get());
        actions.dragAndDrop(srcweelement, destelement).perform();
    }

    public static void dragAndDropUsingJavaScript(WebElement srcweelement, WebElement destelement) {
        final String java_script =
                "var src=arguments[0],tgt=arguments[1];var dataTransfer={dropEffe" +
                        "ct:'',effectAllowed:'all',files:[],items:{},types:[],setData:fun" +
                        "ction(format,data){this.items[format]=data;this.types.append(for" +
                        "mat);},getData:function(format){return this.items[format];},clea" +
                        "rData:function(format){}};var emit=function(event,target){var ev" +
                        "t=document.createEvent('Event');evt.initEvent(event,true,false);" +
                        "evt.dataTransfer=dataTransfer;target.dispatchEvent(evt);};emit('" +
                        "dragstart',src);emit('dragenter',tgt);emit('dragover',tgt);emit(" +
                        "'drop',tgt);emit('dragend',src);";

        ((JavascriptExecutor) DriverFactory.drivers.get()).executeScript(java_script, srcweelement, destelement);
    }
}
