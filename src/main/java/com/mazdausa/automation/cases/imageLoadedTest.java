package com.mazdausa.automation.cases;

import com.mazdausa.automation.app.ExecState;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by gabriela.rojas on 8/11/16.
 */
public class imageLoadedTest extends TestCase {
    private WebDriver driver;
    private String type;
    private ArrayList<WebElement> collection_items;
    private WebElement single_element;
    private static final Logger logger = Logger.getLogger(imageLoadedTest.class.getName());

    public imageLoadedTest(){
        type = "single";
        driver = ExecState.getDriver();
    }

    public void prepare(String t) {
        type = t;
    }

    public void setSingleData(WebElement s){
        single_element = s;
    }

    public void setCollectionData(WebElement parent, String search_type, String search_value){
        switch (search_type){
            case "tag":
                collection_items = (ArrayList<WebElement>) parent.findElements(By.tagName(search_value));
                break;
            case "class":
                collection_items = (ArrayList<WebElement>) parent.findElements(By.className(search_value));
                break;
            default:
                collection_items = new ArrayList<WebElement>();
                break;
        }
    }

    @Override
    public boolean test() {
        Boolean test_result = false;
        if(type.equals("single")){ //single element
            Boolean imageLoaded = (Boolean)((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0;", single_element);

                test_result = imageLoaded;

        }else{ //collection
            int i = 0;
            for(WebElement element: collection_items){
                Boolean imageLoaded = (Boolean)((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0;", element);
                test_result = imageLoaded;
                if(!test_result){
                    logger.log(Level.WARNING,"Found broken image" + element.getAttribute("src"));
                    break;
                }
                i++;
            }
        }
        return test_result;
    }

}
