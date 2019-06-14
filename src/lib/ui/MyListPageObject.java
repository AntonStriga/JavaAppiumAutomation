package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {

    public static final String
        FOLDER_BY_NAME_TEMPLATE = "//*[@text='{FOLDER_NAME}']",
        ARTICLE_TITLE_TEMPLATE = "//*[@text='{ARTICLE_TITLE}']",
        MYLIST_FOLDER_ELEMENT = "//*[@resource-id='org.wikipedia:id/page_list_item_container']";

    public MyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getFolderXpathByName(String folder_name)
    {
        return FOLDER_BY_NAME_TEMPLATE.replace("{FOLDER_NAME}", folder_name);
    };

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_TITLE_TEMPLATE.replace("{ARTICLE_TITLE}", article_title);
    };
    /* TEMPLATE METHODS */

    public void openFolderByName(String folder_name)
    {
        String folder_name_xpath = getFolderXpathByName(folder_name);
        this.waitForElementAndClick(By.xpath(folder_name_xpath),"Cannot find folder by name '" + folder_name + "'",20);
    }

    public void waitForArticleToAppearsByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(By.xpath(article_xpath),"Cannot find saved article '" + article_title + "'",15);
    }

    public void waitForArticleToDisappearsByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(By.xpath(article_xpath),"Saved article '" + article_title + "' still present",15);
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearsByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(By.xpath(article_xpath),"Cannot find saved article '" + article_title + "' to swipe");
        this.waitForArticleToDisappearsByTitle(article_title);
    }

    public int getAmountOfSavedArticles()
    {
        this.waitForElementPresent(By.xpath(MYLIST_FOLDER_ELEMENT),"Cannot find any element in folder",20);
        return this.getElementsAmount(By.xpath(MYLIST_FOLDER_ELEMENT));
    }

    public void openArticleFromMyListByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(By.xpath(article_xpath),"Cannot find article in My list by title '" + article_title + "'", 5);
    }
}
