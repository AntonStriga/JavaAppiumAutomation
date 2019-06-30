package tests;


import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase
{

    @Test
    public void testPassThroughWelome()
    {
        if (Platform.getInstance().isAndroid()) {
            return;
        }

        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForLearMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWaysToExploreText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForAddOrEditPrefferedLangLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForLearMoreAboutDataCollectedLink();
        WelcomePage.clickGetStartedButton();
    }
}
