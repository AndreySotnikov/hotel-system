
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import project.app.MainConfig;
import project.entity.Inventory;
import project.repository.InventoryRepository;
import project.repository.RoomRepository;

import java.util.concurrent.TimeUnit;


/**
 * Created by andrey on 29.06.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = MainConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SampleTest {
    private static FirefoxDriver driver;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    RoomRepository roomRepository;

    @BeforeClass
    public static void openBrowser(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void blogin(){
        System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.get("http://localhost:4444/loginuser");
        driver.findElement(By.name("username")).sendKeys("22");
        driver.findElement(By.name("password")).sendKeys("22");
        driver.findElement(By.id("sub")).click();
        Assert.assertNotEquals(driver.getCurrentUrl(), "http://localhost:4444/fail");
    }

    @Test
    public void aloginfail(){
        driver.get("http://localhost:4444/loginuser");
        driver.findElement(By.name("username")).sendKeys("2233");
        driver.findElement(By.name("password")).sendKeys("22");
        driver.findElement(By.id("sub")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:4444/fail");
    }

    @Test
    public void cinsertInventory(){
        driver.get("http://localhost:4444/inventory/add");
        driver.findElement(By.name("name")).sendKeys("test");
        driver.findElement(By.id("sub")).click();
        Inventory inv = inventoryRepository.getByName("test");
        inventoryRepository.delete(inv);
        Assert.assertNotNull(inv);
    }

    @Test
    public void dinsertRoom(){
        driver.get("http://localhost:4444/room/add");
        int prevSize = roomRepository.size();
        driver.findElement(By.name("floor")).sendKeys("qqqq");
        driver.findElement(By.name("number")).sendKeys("qqqq");
        driver.findElement(By.id("sub")).click();
        Assert.assertEquals(roomRepository.size(),prevSize);
    }

    @AfterClass
    public static void closeBrowser(){
        driver.quit();
    }
}