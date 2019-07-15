package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.inputSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.inputSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        //SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Diskography";
        SearchPageObject.inputSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "dfyghareheshersfgdf";
        SearchPageObject.inputSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLable();
        SearchPageObject.assertThereIsNoResultsOfSearch();
    }

    @Test
    public void testCheckPresenceOfThreeResults()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "goal";
        SearchPageObject.inputSearchLine(search_line);

        boolean all_search_result_present = true;

        try
        {
            SearchPageObject.waitForElementByTitleAndDescription("Goal","esired result or outcome");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            all_search_result_present = false;
        }

        try
        {
            SearchPageObject.waitForElementByTitleAndDescription("Goalkeeper (association football)","osition in association football");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            all_search_result_present = false;
        }

        try
        {
            SearchPageObject.waitForElementByTitleAndDescription("Goaltender","erson who blocks the goal in ice hockey");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            all_search_result_present = false;
        }

        assertTrue(
                "Cannot find all expected articles in search results by query: '" + search_line + "'",
                all_search_result_present
        );
    }
}
