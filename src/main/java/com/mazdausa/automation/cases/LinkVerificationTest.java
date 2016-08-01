package com.mazdausa.automation.cases;

import com.mazdausa.automation.app.ExecState;
import com.mazdausa.automation.app.Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;

/**
 * Created by gabriela.rojas on 6/29/16.
 */
public class LinkVerificationTest extends TestCase {

    private WebDriver driver;
    private String type;
    private ArrayList<WebElement> collection_items;
    private ArrayList<String> links;
    private WebElement single_element;
    private String single_link;

    public LinkVerificationTest(){
        type = "single";
        driver = ExecState.getDriver();
    }

    public void prepare(String t) {
        type = t;
    }

    public void setSingleData(WebElement s, String l){
        single_element = s;
        single_link = l;
    }

    public void setCollectionData(WebElement parent, String search_type, String search_value, ArrayList<String> l){
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
        links = l;
    }

    @Override
    public boolean test() {
        Boolean test_result = false;
        if(type.equals("single")){ //single element
            if(single_element.getAttribute("href").equals(single_link)){
                test_result = true;
            }else{
                test_result = false;
            }
        }else{ //collection
            int i = 0;
            for(WebElement element: collection_items){
                if(element.getAttribute("href").equals(links.get(i))){
                    test_result = true;
                }else{
                    test_result = false;
                    System.out.println("Link failed: " + links.get(i) + ", got: " + element.getAttribute("href"));
                    break;
                }
                i++;
            }
        }
        return test_result;
    }

//    public boolean testLink(WebElement element, String search_type, String search_value, String targetURL, boolean alertBoolean) {
//        Boolean test_result = false;
//        switch (search_type){
//            case "tag":
//                element = driver.findElement(By.tagName(search_value));
//                break;
//            case "class":
//                element = driver.findElement(By.className(search_value));
//                break;
//            default:
//              //  elements = new ArrayList<WebElement>();
//                break;
//        }
//            try {
//                element.click();
//                if (targetURL.compareTo(driver.getCurrentUrl()) == 0) {
//                    test_result = true;
//            } else {
//                    test_result = false;
//                System.out.println("Target URL = " + targetURL);
//                System.out.println("Current URL = " + driver.getCurrentUrl());
//            }
//
//            } catch(Exception ex) {
//                System.out.println(ex.getMessage());
//
//            }
//        return test_result;
//    }
}

