package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "id:Java (programming language)";
        WIKIPEDIA_RETURN_BUTTON = "xpath://XCUIElementTypeButton[@name='Wikipedia, return to Explore']";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MYLIST_BUTTON = "id:Save for later";
        CLOSE_SYNC_POPUP_BUTTON = "id:places auth close";
        CLOSE_ARTICLE_BUTTON = "id:Back";
    }

    public iOSArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
