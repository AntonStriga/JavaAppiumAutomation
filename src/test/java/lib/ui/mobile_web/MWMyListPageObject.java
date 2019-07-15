package lib.ui.mobile_web;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListPageObject
{
    static {
        ARTICLE_TITLE_TEMPLATE = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{ARTICLE_TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{ARTICLE_TITLE}')]/../../div[contains(@class,'watched')]";
        MYLIST_FOLDER_ELEMENT = "xpath://div[@id='mw-content-text']//li[contains(@class,'page-summary with-watchstar')]//h3";
    }

    public MWMyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
