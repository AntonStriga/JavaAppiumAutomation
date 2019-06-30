package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class iOSMyListPageObject extends MyListPageObject
{
    static {
        ARTICLE_TITLE_TEMPLATE = "xpath://XCUITElementTypeLink[contains(@name='{ARTICLE_TITLE}')]";
    }

    public iOSMyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
