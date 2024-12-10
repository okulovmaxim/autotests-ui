package wildberries.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class MainPage {

    private By fieldSearch = By.id("searchInput");
    private By buttonBasket = By.xpath("//a[@data-wba-header-name='Cart']");
    private By buttonLogin = By.xpath("//a[@data-wba-header-name='Login']");


}
