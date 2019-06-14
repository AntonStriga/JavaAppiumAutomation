package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    @Test
    public void testAddArticleToTheMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.inputSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning list";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);

        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = new MyListPageObject(driver);

        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testAddTwoArticlesToMyListAndDeleteOne()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Appium";
        SearchPageObject.inputSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(search_line);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.waitForTitleElement();
        String first_article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning list";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        search_line = "Java";
        SearchPageObject.inputSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(search_line);
        ArticlePageObject.waitForTitleElement();
        String second_article_title = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addArticleToExistListByName(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);

        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = new MyListPageObject(driver);

        MyListPageObject.openFolderByName(name_of_folder);
        int number_of_saved_articles = MyListPageObject.getAmountOfSavedArticles();

        assertTrue(
                "There are no two articles in the list '" + name_of_folder +"'",
                number_of_saved_articles == 2
        );

        MyListPageObject.swipeByArticleToDelete(first_article_title);
        number_of_saved_articles = MyListPageObject.getAmountOfSavedArticles();

        assertTrue(
                "Expected one article in the list '" + name_of_folder +"'",
                number_of_saved_articles == 1
        );

        MyListPageObject.openArticleFromMyListByTitle(second_article_title);
        ArticlePageObject.waitForTitleElement();
        String real_article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Real article title doesn't equal article title in the list",
                second_article_title,
                real_article_title
        );
    }
}
