import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;


public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","d:/JavaAppiumAutomation/apks/old.org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void testAddTwoArticlesAndDeleteOne()
    {
        // Search articles by first query and select first from the list

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' container",
                5
        );

        String search_query = "Appium";

        waitForElementAndSenKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_query,
                "Cannot find 'Search' text input",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Cannot find search results by query: " + search_query,
                20
        );

        String first_article_name = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find article title",
                15
        );

        // Add article to reading list

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find 'More options' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find 'Add to reading list' option",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='GOT IT']"),
                "Cannot find 'GOT IT' button",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find Name of the list text input to clear it ",
                10
        );

        String list_name = "List for testing only";

        waitForElementAndSenKeys(
                By.id("org.wikipedia:id/text_input"),
                list_name,
                "Cannot find Name of the list text input to enter name ",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find 'OK' button",
                10
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Navigate up' button",
                10
        );

        // Search articles by second query and select first from the list

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' container",
                5
        );

        search_query = "Java";

        waitForElementAndSenKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_query,
                "Cannot find 'Search' text input",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Cannot find search results by query: " + search_query,
                20
        );

        String second_article_name = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find article title",
                15
        );

        // Add second article to list

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find 'More options' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find 'Add to reading list' option",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='" + list_name + "']"),
                "Cannot find list '" + list_name + "'",
                10
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Navigate up' button",
                10
        );

        // View saved articles list and delete first saved article

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/fragment_main_nav_tab_layout']//*[@content-desc='My lists']"),
                "Cannot find 'my lists' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='" + list_name + "']"),
                "Cannot find saved list '" + list_name + "'",
                15
        );

        Assert.assertTrue(
                "There are no two articles in the list '" + list_name +"'",
                getElementsAmount(By.id("org.wikipedia:id/page_list_item_container")) == 2
        );

        swipeElementToLeft(
                By.xpath("//*[@text='" + first_article_name + "']"),
                "Cannot find article '" + first_article_name + "' to delete"
        );

        Assert.assertTrue(
                "Expected one article in the list '" + list_name +"'",
                getElementsAmount(By.id("org.wikipedia:id/page_list_item_container")) == 1
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Cannot find article",
                5
        );

        String real_article_name = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find article title",
                15
        );

        Assert.assertEquals(
                "Real article title doesn't equal article title in the list",
                second_article_name,
                real_article_name
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSenKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.clear();
        return element;
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                15
        );

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x,middle_y)
                .waitAction(250)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private int getElementsAmount (By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}
