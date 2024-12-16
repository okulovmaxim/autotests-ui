package wildberries.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage {

    private WebDriver driver;
    private By buttonAllFilters = By.xpath("//button[@class='dropdown-filter__btn dropdown-filter__btn--all']");
    private By fieldStartFilter = By.xpath("//input[@name='startN']");
    private By fieldEndFilter = By.xpath("//input[@name='endN']");
    private By buttonResult = By.xpath("//button[@class='filters-desktop__btn-main btn-main']");
    private By items = By.xpath("//div[@class='product-card-list']//article");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openItem() {
        driver.findElements(items).get(0).click();
        
    }

    public void openFilters() {
        driver.findElement(buttonAllFilters).click();
    }

    public void applyFilters() {
        driver.findElement(buttonResult).click();
    }

    public void setMinPrice(Integer minPrice) {
        driver.findElement(fieldStartFilter).clear();
        driver.findElement(fieldStartFilter).sendKeys(String.valueOf(minPrice));
    }

    public void setMaxPrice(Integer maxPrice) {
        driver.findElement(fieldEndFilter).clear();
        driver.findElement(fieldEndFilter).sendKeys(String.valueOf(maxPrice));
    }

}
