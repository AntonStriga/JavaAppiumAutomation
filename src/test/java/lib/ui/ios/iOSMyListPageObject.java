package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListPageObject extends MyListPageObject
{
    static {
        ARTICLE_TITLE_TEMPLATE = "xpath://XCUIElementTypeLink[contains(@name,'{ARTICLE_TITLE}')]";
        MYLIST_FOLDER_ELEMENT = "xpath://XCUIElementTypeLink";
    }

    public iOSMyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
