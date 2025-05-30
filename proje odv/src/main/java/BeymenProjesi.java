import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.*; // org.apache.poi.ss.usermodel altındakileri import et
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;

public class  BeymenProjesi {

public static void  main (String[] args) throws InterruptedException, IOException {
    ChromeOptions options = new ChromeOptions();

    // Disable notifications
    options.addArguments("--disable-notifications");




    WebDriver driver = new ChromeDriver(options);
    WebDriverManager.chromedriver().setup();
   // WebDriver driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    driver.get("https://www.beymen.com");



    driver.manage().window().maximize(); //ekranı büyüttüm
    Thread.sleep(1000);
    driver.findElement(By.xpath("//*[@id='onetrust-accept-btn-handler']")).click(); //cıkan pop up ı disable ettim
    Thread.sleep(1000);
    driver.findElement(By.xpath("//*[@class='a-primaryButton genderPopup__button']")).click(); // herhangi bir cinsiyete tıkladım buradaki erkek

    WebElement searchButton = driver.findElement(By.xpath("(//*[@class='o-header__search--input']) [1]")); // search butonunun xpathi

    try {
        String searchTerm = getValue(0, 0);
        searchButton.sendKeys(searchTerm);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

    Thread.sleep(1000);


    driver.findElement(By.xpath("//*[@class='o-header__search--close -hasButton']")).click();
    searchButton.click();


    //WebElement searchButton2 = driver.findElement(By.xpath("(//*[@class='o-header__search--input']) [1]"));

    try {
        String searchTerm = getValue(1, 0);
        searchButton.sendKeys(searchTerm);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }


}
//birden çok kez kullanacağım için fonksiyon haline getirdim
public static String getValue(int row, int col) throws Exception {
    String excel = "src/resources-sort/testdata.xlsx"; // pathe stringi atadım
    FileInputStream fis = new FileInputStream(excel);  // bu dosyaları okuyabilmek için gerekli kütüphanem
    Workbook workbook = WorkbookFactory.create(fis);
    Sheet sheet = workbook.getSheetAt(0);
    return sheet.getRow(row).getCell(col).getStringCellValue();


}

}
