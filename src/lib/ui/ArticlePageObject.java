package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    public static final String
        TITLE = "org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MYLIST_BUTTON = "//*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "//*[@text='Got it']",
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
        MY_LIST_EXIST_FOLDER= "//*[@resource-id='org.wikipedia:id/item_title'][@text='{FOLDER_NAME}']",
        MY_LIST_OK_BUTTON = "//*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getFolderXpathByName(String folder_name)
    {
        return MY_LIST_EXIST_FOLDER.replace("{FOLDER_NAME}", folder_name);
    };
    /* TEMPLATE METHODS */

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE),"Cannot find article title", 20);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),"Cannot reach footer element", 20);
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(By.xpath(OPTIONS_BUTTON),"Cannot find 'More options' button",10);
        this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MYLIST_BUTTON),"Cannot find 'Add to reading list' button",10);
        this.waitForElementAndClick(By.xpath(ADD_TO_MY_LIST_OVERLAY),"Cannot find GOT IT button",10);
        this.waitForElementAndClear(By.id(MY_LIST_NAME_INPUT),"Cannot find input element to clear it",10);
        this.waitForElementAndSenKeys(By.id(MY_LIST_NAME_INPUT),name_of_folder,"Cannot find text input element",10);
        this.waitForElementAndClick(By.xpath(MY_LIST_OK_BUTTON),"Cannot find OK button",10);
    }

    public void addArticleToExistListByName(String folder_name)
    {
        this.waitForElementAndClick(By.xpath(OPTIONS_BUTTON),"Cannot find 'More options' button",5);
        this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MYLIST_BUTTON),"Cannot find 'Add to reading list' button",5);
        String folder_name_xpath = getFolderXpathByName(folder_name);
        this.waitForElementAndClick(By.xpath(folder_name_xpath),"Cannot find folder '" + folder_name + "'", 15);
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON),"Cannot find Navigate up button",5);
    }

    public void assertArticleTitlePresent()
    {
        int amount_of_elements = this.getElementsAmount(By.id(TITLE));
        if (amount_of_elements == 0){
            throw new AssertionError("Title of article is not present");
        }
    };
}
