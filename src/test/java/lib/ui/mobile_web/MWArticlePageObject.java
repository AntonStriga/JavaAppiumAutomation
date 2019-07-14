package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_BUTTON = "css:#page-action li#ca-watch button";
        OPTIONS_ADD_TO_MYLIST_BUTTON = "css:#page-actions li#page-actions-watch a#ca-watch";
        OPTIONS_REMOVE_FROM_MYLIST_BUTTON = "css:#page-actions li#ca-watch.mw-ui-icon-mf-watched watched button";
        ADD_TO_MY_LIST_OVERLAY = "";
        MY_LIST_NAME_INPUT = "";
        MY_LIST_EXIST_FOLDER= "";
        MY_LIST_OK_BUTTON = "";
    }

    public MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
