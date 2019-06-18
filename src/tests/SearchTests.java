package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.inputSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.inputSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        //SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "dfyghareheshersfgdf";
        SearchPageObject.inputSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLable();
        SearchPageObject.assertThereIsNoResultsOfSearch();
    }

    @Test
    public void testCheckPresenceOfThreeResults()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "sport";
        SearchPageObject.inputSearchLine(search_line);

        boolean search_result_missed = false;

        try
        {
            SearchPageObject.waitForElementByTitleAndDescription("Sportz","Forms of competitive activity, usually physical");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            search_result_missed = true;
        }

        try
        {
            SearchPageObject.waitForElementByTitleAndDescription("Sportz utility vehicle","Type of automobile");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            search_result_missed = true;
        }

        try
        {
            SearchPageObject.waitForElementByTitleAndDescription("Sport of athleticsz","Collection of sports which involve running, jumping, throwing, and walking");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            search_result_missed = true;
        }

        if (search_result_missed)
        {
            throw new AssertionError("Cannot find all expected articles in search results by query: '" + search_line + "'");
        }
    }
}
