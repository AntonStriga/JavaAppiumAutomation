package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class AndroidMyListPageObject extends MyListPageObject
{
    static {
        FOLDER_BY_NAME_TEMPLATE = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_TITLE_TEMPLATE = "xpath://*[@text='{ARTICLE_TITLE}']";
    }

    public AndroidMyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
