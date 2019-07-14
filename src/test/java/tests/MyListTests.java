package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListTests extends CoreTestCase
{

    private static final String
        name_of_folder = "Learning list",
        login = "as_appium",
        password = "appium_as";

    @Test
    public void testAddArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.inputSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");


        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }

        if (Platform.getInstance().isIOS()) {
            ArticlePageObject.closeSyncPopup();
        }

        if (Platform.getInstance().isMobWeb()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            assertEquals(
                    "We are not at the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );

            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(name_of_folder);
        }
        MyListPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testAddTwoArticlesToMyListAndDeleteOne()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.inputSearchLine("Sandwich");
        SearchPageObject.clickByArticleWithSubstring("Two slices of bread");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.waitForWikipediaReturnElement();
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.closeSyncPopup();
        }
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        if (Platform.getInstance().isIOS()) {
            SearchPageObject.clearSearchLine();
        }

        String expected_search_result = "Java";
        SearchPageObject.inputSearchLine(expected_search_result);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.addArticleToExistListByName(name_of_folder);
        } else {
            ArticlePageObject.waitForWikipediaReturnElement();
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);

        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(name_of_folder);
        }
        int number_of_saved_articles = MyListPageObject.getAmountOfSavedArticles();

        assertTrue(
                "There are no two articles in the list '" + name_of_folder +"'",
                number_of_saved_articles == 2
        );

        MyListPageObject.swipeByArticleToDelete("Sandwich");
        number_of_saved_articles = MyListPageObject.getAmountOfSavedArticles();

        assertTrue(
                "Expected one article in the list '" + name_of_folder +"'",
                number_of_saved_articles == 1
        );

        String saved_article_name = MyListPageObject.getSavedArticleName();

        assertTrue(
                "Expected saved article is not in the list",
                saved_article_name.contains(expected_search_result)
        );
    }
}
