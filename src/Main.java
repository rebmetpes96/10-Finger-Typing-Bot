
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.event.KeyEvent;


public class Main {

    static void typer(WebDriver webDriver) {
        int i = 1;

        try {
            Robot robot = new Robot();

            while (true) {
                String key = webDriver.findElement(By.xpath("//*[@id=\"writer_text\"]/span[" + i + "]")).getText();
                System.out.print(key);

                if (key.length() > 0) {

                    for (int j = 0; j < key.toCharArray().length; j++) {
                        robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(key.charAt(j)));
                    }

                } else {
                    robot.keyPress(KeyEvent.VK_SPACE);
                    System.out.println("\n");
                }
                i++;
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        //SETUP
        System.setProperty("webdriver.chrome.driver", "[YOUR-PATH-TO-THE-DRIVER(PREFEREBLY CHROME)]\\chromedriver.exe\\");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.tippenakademie.de/schreibtrainer/tipp-test");

        //FIRST STEPS
        webDriver.findElement(By.xpath("//*[@id=\"dialog\"]/div/div/div[2]/a[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"dialog_x\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"writer_slideshow\"]/p[2]/a")).click();
        webDriver.findElement(By.xpath("//*[@id=\"writer_message\"]/div/p/a[1]")).click();

        //WAIT TO LOAD...
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //CLOSE LAST ELEMENT BEFORE INTERFERING
        webDriver.findElement(By.xpath("//*[@id=\"dialog_x\"]")).click();

        //WAIT FOR THE LETTERS TO LOAD
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //START TYPING
        typer(webDriver);



    }
}
