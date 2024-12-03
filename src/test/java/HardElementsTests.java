import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HardElementsTests {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @Test
    public void authTest() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        String authText = driver.findElement(By.xpath("//h3[text()='Basic Auth']")).getText();
        Assert.assertTrue(authText.contains("Basic Auth"));
    }

    @Test
    public void alertOkTest() {
        String expectedAlertText= "I am a JS Alert";
        driver.get("https://the-internet.herokuapp.com/");

        WebElement buttonMain = driver.findElement(By.xpath("//a[contains(text(), 'JavaScript Alerts')]"));
        buttonMain.click();

        WebElement buttonPage = driver.findElement(By.xpath("//button[contains(text(), 'Click for JS Alert')]"));
        buttonPage.click();

        String actualAlertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        Assert.assertEquals(expectedAlertText, actualAlertText);
    }

    @Test
    public void iFrameTest() {
        driver.get("https://mail.ru/");
        WebElement button = driver.findElement(By.xpath("//div[@id='mailbox']//button[text()='Войти']"));
        button.click();

        WebElement iframeAuth = driver.findElement(By.xpath("//iframe[@class='ag-popup__frame__layout__iframe']"));
        driver.switchTo().frame(iframeAuth);
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Я ввел текст, епта)");



    }
}
