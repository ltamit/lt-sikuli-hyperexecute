package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.SeleniumHelper;
import utils.SikuliHelper;

public class ExampleTest extends LambdaTestFileUploader {

    WebDriver driver;
    Screen screen;

    @BeforeClass
    public void setup() {
        driver = SeleniumHelper.getDriver();
        screen = SikuliHelper.getScreen();
    }

    @Test
    public void testExample() throws FindFailed, InterruptedException {




        String pathValue = System.getenv("HYPEREXECUTE_WORKING_DIR");

        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/login");


       //setSize(new Dimension(1200, 753));
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");


        double similarity = 0.1;
        Pattern loginBtn = new Pattern( (pathValue) + "\\resources\\sikuliImages\\LoginButton.png").targetOffset(0, -20).similar(similarity);
        screen.click(loginBtn);



        Thread.sleep(10000);




    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
