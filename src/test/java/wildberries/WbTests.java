package wildberries;

import org.testng.annotations.Test;
import wildberries.pages.MainPage;
import wildberries.pages.SearchResultPage;

public class WbTests extends BaseTest {

    @Test
    public void searchResultTest() {
        String expectedItem = "iPhone";
        Integer expectedMinPrice = 30_000;
        Integer expectedMaxPrice = 100_000;

        MainPage mainPage = new MainPage(driver);
        mainPage.searchItem(expectedItem);

        SearchResultPage resultPage = new SearchResultPage(driver);
        resultPage.openFilters();
        resultPage.setMinPrice(expectedMinPrice);
        resultPage.setMaxPrice(expectedMaxPrice);
        resultPage.applyFilters();
    }
}
