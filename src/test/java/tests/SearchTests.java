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
        String search_line = "sport";
        SearchPageObject.inputSearchLine(search_line);

        boolean all_search_result_present = true;

        try
        {
            SearchPageObject.waitForElementByTitleAndDescription("Sport","Forms of competitive activity, usually physical");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            all_search_result_present = false;
        }

        try
        {
            SearchPageObject.waitForElementByTitleAndDescription("Sport utility vehicle","Type of automobile");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            all_search_result_present = false;        }

        try
        {
            SearchPageObject.waitForElementByTitleAndDescription("Sport of athletics","Collection of sports which involve running, jumping, throwing, and walking");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            all_search_result_present = false;        }

        assertTrue(
                "Cannot find all expected articles in search results by query: '" + search_line + "'",
                all_search_result_present
        );
    }
}
