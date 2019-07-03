package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class AndroidMyListPageObject extends MyListPageObject
{
    static {
        FOLDER_BY_NAME_TEMPLATE = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_TITLE_TEMPLATE = "xpath://*[@text='{ARTICLE_TITLE}']";
        MYLIST_FOLDER_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
    }

    public AndroidMyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
