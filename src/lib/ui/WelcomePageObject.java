package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject
{
    private static final String
        STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
        STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
        STEP_ADD_OR_EDIT_PREFFERED_LANGUAGE_LINK = "id:Add or edit preferred languages",
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
        NEXT_BUTTON = "id:Next",
        GET_STARTED_BUTTON = "id:Get started",
        SKIP = "id:Skip";

    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void waitForLearMoreLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,"Cannot find '" + STEP_LEARN_MORE_LINK + "' link",10);
    }

    public void waitForNewWaysToExploreText()
    {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT,"Cannot find '" + STEP_NEW_WAYS_TO_EXPLORE_TEXT + "' text",10);
    }

    public void waitForAddOrEditPrefferedLangLink()
    {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFFERED_LANGUAGE_LINK,"Cannot find '" + STEP_ADD_OR_EDIT_PREFFERED_LANGUAGE_LINK + "' link",10);
    }

    public void waitForLearMoreAboutDataCollectedLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,"Cannot find '" + STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK + "' link",10);
    }

    public void clickNextButton()
    {
        this.waitForElementAndClick(NEXT_BUTTON,"Cannot find and click 'Next' button",5);
    }

    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(GET_STARTED_BUTTON,"Cannot find and click 'Get started' button",5);
    }

    public void clickSkip()
    {
        this.waitForElementAndClick(SKIP,"Cannot find and click SKIP button",10);
    }
}
