package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListPageObject extends MainPageObject {

    protected static String
        FOLDER_BY_NAME_TEMPLATE,
        ARTICLE_TITLE_TEMPLATE,
        MYLIST_FOLDER_ELEMENT;

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
        this.waitForElementAndClick(folder_name_xpath,"Cannot find folder by name '" + folder_name + "'",20);
    }

    public void waitForArticleToAppearsByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath,"Cannot find saved article '" + article_title + "'",15);
    }

    public void waitForArticleToDisappearsByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath,"Saved article '" + article_title + "' still present",15);
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearsByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(article_xpath,"Cannot find saved article '" + article_title + "' to swipe");

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(article_xpath,"Cannot find saved article");
        }
        this.waitForArticleToDisappearsByTitle(article_title);
    }

    public int getAmountOfSavedArticles()
    {
        this.waitForElementPresent(MYLIST_FOLDER_ELEMENT,"Cannot find any element in folder",20);
        return this.getElementsAmount(MYLIST_FOLDER_ELEMENT);
    }

    public void openArticleFromMyListByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(article_xpath,"Cannot find article in My list by title '" + article_title + "'", 5);
    }
}
