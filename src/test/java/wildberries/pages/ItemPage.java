package wildberries.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemPage {

    private WebDriver driver;
    private By itemHeaderName = By.xpath("//div[@class='product-page__header']//h1");
    private By itemPriceText = By.xpath("//ins[@class='price-block__final-price']");
    

    public ItemPage(WebDriver driver) {
        this.driver = driver;
    }


}
