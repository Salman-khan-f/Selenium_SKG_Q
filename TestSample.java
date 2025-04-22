// Test Steps:
//     Open the browser and navigate to the URL: https://www.angi.com/
//     Scroll down to the footer section.
//     Click on the Twitter icon and switch to the new window.
//     Close the new window and switch back to the home window.
//     Scroll down to the footer section again.
//     Click on the Facebook icon and switch to the new window.
//     Close the new window and switch back to the home window.
//     Scroll down to the footer section again.
//     Click on the Pinterest icon and switch to the new window.
//     Close the new window and switch back to the home window.
//     Wait for 5 seconds to observe the results.
//     Quit the browser session.


// ------------------------------------------------------

package runner;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.EventHandler;

public class TestSample {

    public static WebDriver driver;

    @Test
    public void testMain() throws InterruptedException {
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444"), new ChromeOptions());
            
            // Maximize the browser window
            driver.manage().window().maximize();
                       
            // Add the implicit wait
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
                     
            // Add the page load timeout
            driver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(10));
                      
            // Initialize the event listener
            WebDriverListener listener = new EventHandler();
            driver = new EventFiringDecorator<>(listener).decorate(driver);

            // Start your script from here
            driver.get("https://www.angi.com");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

            WebElement tw = driver.findElement(By.xpath("//img[@alt='Twitter']"));
            
            tw.click();
            
            // Add the page load timeout
            Thread.sleep(2000);
            
            driver.navigate().to("https://www.angi.com");
            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

            WebElement fb = driver.findElement(By.xpath("//img[@alt='Facebook']"));
            fb.click();
            
            
            driver.navigate().to("https://www.angi.com");
            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

            WebElement pr = driver.findElement(By.xpath("//img[@alt='Pinterest']"));
            pr.click();

            

            // Perform any additional actions (e.g., interacting with elements)
        
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
