package com.mazdausa.automation.cases;

import com.mazdausa.automation.app.ExecState;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by gabriela.rojas on 8/1/16.
 */
public class HasClassTest extends TestCase {

    private WebDriver driver;
    private WebElement element;
    private String test_class;

    public HasClassTest(){

    }
    public void prepare(String element_xpath, String t) {
        driver = ExecState.getDriver();
        element = driver.findElement(By.xpath(element_xpath));
        test_class = t;
    }
    @Override
    public boolean test() {

        if(element.getAttribute("class").indexOf(test_class) >= 0){
            return true;
        }else {

            return false;
        }
    }
}
