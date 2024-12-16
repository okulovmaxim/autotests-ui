package wildberries.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private WebDriver driver;
    private By fieldSearch = By.id("searchInput");
    private By buttonBasket = By.xpath("//a[@data-wba-header-name='Cart']");
    private By buttonLogin = By.xpath("//a[@data-wba-header-name='Login']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchItem(String item) {
        driver.findElement(fieldSearch).click();
        driver.findElement(fieldSearch).sendKeys(item);
        driver.findElement(fieldSearch).sendKeys(Keys.ENTER);
    }


}
