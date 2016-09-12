package com.mazdausa.automation.cases;

import com.mazdausa.automation.app.ExecState;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mazdausa.automation.app.Utils;


/**
 * Created by gabriela.rojas on 7/8/16.
 */
public class HoverVerificationTest extends TestCase {


    private WebDriver driver;
    private String type;
    private ArrayList<WebElement> collection_items;
    private WebElement single_element;
    private String prop;
    private static final Logger logger = Logger.getLogger(HoverVerificationTest.class.getName());

    public HoverVerificationTest(){
        type = "single";
        driver = ExecState.getDriver();
    }

    public void prepare(String t) {
        type = t;
    }

    public void setSingleData(WebElement s, String p){
        single_element = s;
        prop = p;
    }

    public void setCollectionData(WebElement parent, String search_type, String search_value, String p){
        prop = p;
        switch (search_type){
            case "tag":
                collection_items = (ArrayList<WebElement>) parent.findElements(By.tagName(search_value));
                break;
            case "class":
                collection_items = (ArrayList<WebElement>) parent.findElements(By.className(search_value));
                break;
            case "css":
                collection_items = (ArrayList<WebElement>) parent.findElements(By.cssSelector(search_value));
                break;
            default:
                collection_items = new ArrayList<WebElement>();
                break;
        }
    }



    @Override
    public boolean test() {
        Boolean test_result = true;
        if(type.equals("single")){ //single element
            try {

                String initialPropertyValue = single_element.getCssValue(prop);
                Actions action = new Actions(ExecState.getDriver());
                action.moveToElement(single_element).build().perform();
                Thread.sleep(2000);
                String hoveredPropertyValue = single_element.getCssValue(prop);

                logger.log(Level.FINER,"initial " + initialPropertyValue + "hovered" + hoveredPropertyValue);
                if(initialPropertyValue.equals(hoveredPropertyValue)){
                    test_result = false;
                }
            } catch(Exception ex) {
                logger.log(Level.WARNING,ex.getMessage(),ex);

            }
        }else { //collection
            for(WebElement element: collection_items){
                try {

                    String initialPropertyValue = element.getCssValue(prop);
                    Actions action = new Actions(ExecState.getDriver());
                    action.moveToElement(element).build().perform();
                    Thread.sleep(2000);
                    String hoveredPropertyValue = element.getCssValue(prop);

                    if(initialPropertyValue.equals(hoveredPropertyValue)){
                        test_result = false;
                    }
                } catch(Exception ex) {
                    logger.log(Level.WARNING,ex.getMessage(),ex);

                }
            }
        }
        return test_result;
    }
}
