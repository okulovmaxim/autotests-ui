package wildberries;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import wildberries.pages.MainPage;

public class WbTests extends BaseTest {

    @Test
    public void searchResultTest() {
        String expectedItem = "iPhone";
        MainPage mainPage = new MainPage(driver);
        mainPage.searchItem(expectedItem);
    }
}
