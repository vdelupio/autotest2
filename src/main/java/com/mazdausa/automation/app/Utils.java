package com.mazdausa.automation.app;
import org.openqa.selenium.NoSuchFrameException;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by gabriela.rojas on 7/4/16.
 */
public class Utils {

    public WebDriver driver;

    public Properties getConfigProperties(String configName) {
        Properties configProps = new Properties();
        try {

            File file = new File(configName);
            FileInputStream fileInput = new FileInputStream(file);
            configProps.load(fileInput);
            fileInput.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return configProps;
    }

    public InputStream getConfigStream(String configName) {
        InputStream in = null;
        try {
            File file = new File(configName);
            in = new FileInputStream(file);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return in;
    }

    public ArrayList<String> getStringList(String data) {
        return new  ArrayList<String>(Arrays.asList(data.split(",")));
    }


    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAndWait(String xpath, int wait){
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void clickwait(String id, int wait){
        WebElement element = driver.findElement(By.id(id));
        element.click();
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void wait( int wait){
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void switchToFrame(String frame) {
        try {
            driver.switchTo().frame(frame);
            System.out.println("Navigated to frame with name " + frame);
        } catch (NoSuchFrameException e) {
            System.out.println("Unable to locate frame with id " + frame
                    + e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Unable to navigate to frame with id " + frame
                    + e.getStackTrace());
        }
    }
    public void switchtoDefaultFrame() {
        try {
            driver.switchTo().defaultContent();
            System.out.println("Navigated back to webpage from frame");
        } catch (Exception e) {
            System.out
                    .println("unable to navigate back to main webpage from frame"
                            + e.getStackTrace());
        }
    }


}
