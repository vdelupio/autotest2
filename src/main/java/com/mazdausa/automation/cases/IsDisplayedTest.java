package com.mazdausa.automation.cases;

import com.mazdausa.automation.app.ExecState;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by gabriela.rojas on 7/27/16.
 */
public class IsDisplayedTest{

    private WebDriver driver;
    private WebElement element;

    public IsDisplayedTest(){

    }

    public void prepare(String element_xpath) {
      driver = ExecState.getDriver();
      element = driver.findElement(By.xpath(element_xpath));
    }

    public boolean test() {

        return element.isDisplayed();
    }
}
