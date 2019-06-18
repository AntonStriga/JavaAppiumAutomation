package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{

    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT = "//*[contains(@text,'Search…')]",
        SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
        SEARCH_RESULT = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
        SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_result_list']/*[@resource-id='org.wikipedia:id/page_list_item_container]",
        SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']",
        SEARCH_RESULT_TITLE_DESCRIPTION = "//*[@text='{SUBSTRING_TITLE}']/following-sibling::*[@text='{SUBSTRING_DESCRIPTION}']/..";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT.replace("{SUBSTRING}", substring);
    };

    private static String getResultSearchElementByTitleAndDescription(String substring_title, String substring_description)
    {
        return SEARCH_RESULT_TITLE_DESCRIPTION.replace("{SUBSTRING_TITLE}", substring_title).replace("{SUBSTRING_DESCRIPTION}",substring_description);
    };
    /* TEMPLATE METHODS */

    public void initSearchInput()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),"Cannot find and click search input element",5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT),"Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON),"Cannot find search cancel button",5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON),"Search cancel button is still on the screen",5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button",5);
    }

    public void inputSearchLine(String search_line)
    {
        this.waitForElementAndSenKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring,10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT),"Cannot find any search result",20);
        return this.getElementsAmount(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLable()
    {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),"Cannot find empty result element", 20);
    }

    public void assertThereIsNoResultsOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT),"We supposed not to find any results");
    }

    public void waitForElementByTitleAndDescription(String substring_title, String substring_description)
    {
        String search_result_xpath = getResultSearchElementByTitleAndDescription(substring_title, substring_description);
        this.waitForElementPresent(
                By.xpath(search_result_xpath),
                "Cannot find search result with title: '" + substring_title + "' and description: '" + substring_description + "' in search results",
                15);
    }
}
