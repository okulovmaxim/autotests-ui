import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SeleniumTests {

    private WebDriver driver;
    private final String downloadFolder = System.getProperty("user.dir") + File.separator + "build" + File.separator + "downloadFiles";

    @BeforeSuite
    public static void downloadDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        Map<String, String> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadFolder);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("http://85.192.34.140:8081/");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();

    }

    @Test
    public void simpleForTest() {
        String expectedName = "Okulov Maxim";
        String expectedEmail = "okulov.m.i@gmail.com";
        String expectedCurrentAddress = "Ekaterinburg";
        String expectedPermanentAddress = "Kargi";

        WebElement elementCard = driver.findElement(By.xpath("//div[@class='card-body']//h5[text()='Elements']"));
        elementCard.click();

        WebElement elementTextBox = driver.findElement(By.xpath("//span[text()='Text Box']"));
        elementTextBox.click();

        WebElement elementFullName = driver.findElement(By.id("userName"));
        WebElement elementEmail = driver.findElement(By.id("userEmail"));
        WebElement elementCurrentAddress = driver.findElement(By.id("currentAddress"));
        WebElement elementPermanentAddress = driver.findElement(By.id("permanentAddress"));
        WebElement submit = driver.findElement(By.id("submit"));

        elementFullName.sendKeys(expectedName);
        elementEmail.sendKeys(expectedEmail);
        elementCurrentAddress.sendKeys(expectedCurrentAddress);
        elementPermanentAddress.sendKeys(expectedPermanentAddress);
        submit.click();

        WebElement newFullName = driver.findElement(By.id("name"));
        WebElement newEmail = driver.findElement(By.id("email"));
        WebElement newCurrentAddress = driver.findElement(By.xpath("//p[@id='currentAddress']"));
        WebElement newPermanentAddress = driver.findElement(By.xpath("//p[@id='permanentAddress']"));

        String actualFullName = newFullName.getText();
        String actualEmail = newEmail.getText();
        String actualCurrentAddress = newCurrentAddress.getText();
        String actualPermanentAddress = newPermanentAddress.getText();

        Assert.assertTrue(actualFullName.contains(expectedName));
        Assert.assertTrue(actualEmail.contains(expectedEmail));
        Assert.assertTrue(actualCurrentAddress.contains(expectedCurrentAddress));
        Assert.assertTrue(actualPermanentAddress.contains(expectedPermanentAddress));
    }

    @Test
    public void uploadTest() {
        WebElement elementCard = driver.findElement(By.xpath("//div[@class='card-body']//h5[text()='Elements']"));
        elementCard.click();

        WebElement elementTextBox = driver.findElement(By.xpath("//span[text()='Upload and Download']"));
        elementTextBox.click();

        WebElement uploadButton = driver.findElement(By.id("uploadFile"));
        uploadButton.sendKeys(System.getProperty("user.dir") + "/src/test/resources/FileForTest.txt");

        WebElement uploadedFile = driver.findElement(By.id("uploadedFilePath"));
        String expectedUploadedFile = uploadedFile.getText();

        Assert.assertTrue(expectedUploadedFile.contains("FileForTest.txt"));
    }

    @Test
    public void downloadTest() {
        WebElement elementCard = driver.findElement(By.xpath("//div[@class='card-body']//h5[text()='Elements']"));
        elementCard.click();

        WebElement elementTextBox = driver.findElement(By.xpath("//span[text()='Upload and Download']"));
        elementTextBox.click();

        WebElement downloadButton = driver.findElement(By.id("downloadButton"));
        downloadButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(x-> Paths.get(downloadFolder, "sticker.png").toFile().exists());

        File file = new File("build/downloadFiles/sticker.png");
        Assert.assertTrue(file.length() != 0);
        Assert.assertNotNull(file);
    }
}
