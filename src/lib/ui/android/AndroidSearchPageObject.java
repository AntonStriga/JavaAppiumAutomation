package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject
{
    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_result_list']/*[@resource-id='org.wikipedia:id/page_list_item_container]";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No resulta found']";
        SEARCH_RESULT_TITLE_DESCRIPTION = "xpath://*[@text='{SUBSTRING_TITLE}']/following-sibling::*[@text='{SUBSTRING_DESCRIPTION}']/..";
    }

    public AndroidSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
