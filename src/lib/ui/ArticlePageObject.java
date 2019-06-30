package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
        TITLE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        OPTIONS_ADD_TO_MYLIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON;

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE,"Cannot find article title", 20);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }

    }

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT,"Cannot reach footer element", 40);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,"Cannot reach footer element",40);
        }
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(OPTIONS_BUTTON,"Cannot find 'More options' button",5);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MYLIST_BUTTON,"Cannot find 'Add to reading list' button",5);
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY,"Cannot find GOT IT button",5);
        this.waitForElementAndClear(MY_LIST_NAME_INPUT,"Cannot find input element to clear it",5);
        this.waitForElementAndSenKeys(MY_LIST_NAME_INPUT,name_of_folder,"Cannot find text input element",5);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON,"Cannot find OK button",5);
    }

    public void addArticleToMySaved()
    {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MYLIST_BUTTON,"Cannot find option to add article to reading list",5);
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,"Cannot find Navigate up button",5);
    }
}
