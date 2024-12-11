package wildberries.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage {

    private WebDriver driver;
    private By buttonFilter = By.xpath("//button[@data-link='{on filtresModel.showDesktopFiltres}']");
    private By fieldStartFilter = By.xpath("//input[@data-link='{:value convert=~setText}'][@name='startN']");
    private By fieldEndFilter = By.xpath("//input[@data-link='{:value convert=~setText}'][@name='endN']");



    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }


}
