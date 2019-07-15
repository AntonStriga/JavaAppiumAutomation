package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;
import lib.Platform;

abstract public class NavigationUI extends MainPageObject {

    protected static String
        MY_LIST_BUTTON,
        OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openNavigation()
    {
        if (Platform.getInstance().isMobWeb()) {
            this.waitForElementAndClick(
                    OPEN_NAVIGATION,
                    "Cannot find and click open navigation button",
                    10
                    );
        } else {
            System.out.println("Method openNavigation() does nothing for platform " + Platform.getInstance().getPlatformVariable());
        }
    }

    public void clickMyList()
    {
        if (Platform.getInstance().isMobWeb()) {
            this.tryClickElementWithFewAttempts(
                    MY_LIST_BUTTON,
                    "Cannot fine My lists button",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    MY_LIST_BUTTON,
                    "Cannot fine My lists button",
                    10);
        }

    }
}
