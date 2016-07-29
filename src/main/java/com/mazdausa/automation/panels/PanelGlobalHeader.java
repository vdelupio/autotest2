package com.mazdausa.automation.panels;

import com.mazdausa.automation.app.ExecState;
import com.mazdausa.automation.app.Utils;
import com.mazdausa.automation.cases.HoverVerificationTest;
import com.mazdausa.automation.cases.IsDisplayedTest;
import com.mazdausa.automation.cases.LinkVerificationTest;
import com.mazdausa.automation.panels.PanelGlobalHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by gabriela.rojas on 6/29/16.
 */
public class PanelGlobalHeader extends Panel {

    private WebDriver driver;
    private HoverVerificationTest hover_test;
    private LinkVerificationTest link_test;
    private IsDisplayedTest displayed_test;
    private Utils utils;

    public PanelGlobalHeader(){

        driver = ExecState.getDriver();
        props = ExecState.getProps();
        hover_test = new HoverVerificationTest();
        link_test = new LinkVerificationTest();
        displayed_test = new IsDisplayedTest();
        utils = new Utils();
        utils.setDriver(driver);
        this.execute();

    }


    public void execute(){

        System.out.println("Starting PanelGlobalHeader\n\n");

        //Mazda_Logo Link verification
        WebElement globalHeaderlogo = driver.findElement(By.xpath(props.getProperty("mazda_logo")));
        link_test.prepare("single");
        link_test.setSingleData(globalHeaderlogo, props.getProperty("musa_homepage_url_prod"));
        Boolean globalLogoResult = link_test.test();
        System.out.println("GlobalLogolink: " + ((globalLogoResult) ? "PASS" : "FAIL"));

        //hover verification globalHeader parent
        WebElement globalHeaderlink = driver.findElement(By.xpath(props.getProperty("globalheader_parent")));
        Boolean globaHoverResult = hover_test.testCollection(globalHeaderlink, "tag", "a", "color");
        System.out.println("Global Navigation Hover: " + ((globaHoverResult) ? "PASS" : "FAIL"));

        //Displayed verification test
        utils.clickAndWait(props.getProperty("globalHeader_whymazdalink"),1000);
        displayed_test.prepare(props.getProperty("why_mazda_dropdown"));
        Boolean whyMazdaDisplayed = displayed_test.test();
        System.out.println("Why Mazda dropdown: " + ((whyMazdaDisplayed) ? "PASS" : "FAIL"));

        //Why Mazda sub menu Hover Verification
        WebElement why_mazda_dropdown = driver.findElement(By.xpath(props.getProperty("why_mazda_dropdown_options")));
        Boolean why_mazda_menu_hover_result = hover_test.testCollection(why_mazda_dropdown, "tag", "a", "color");
        System.out.println("Why Mazda Navigation Hover: " + ((why_mazda_menu_hover_result) ? "PASS" : "FAIL"));

        //Why Mazda sub menu links verification
        link_test.prepare("collection");
        ArrayList<String> why_mazda_links = new ArrayList<String>();
        why_mazda_links.add(props.getProperty("driving_matters_link"));
        why_mazda_links.add(props.getProperty("design_link"));
        why_mazda_links.add(props.getProperty("safety_link"));
        why_mazda_links.add(props.getProperty("inside_mazda_link"));
        why_mazda_links.add(props.getProperty("discover_skyactiv_link"));
        link_test.setCollectionData(why_mazda_dropdown, "tag", "a", why_mazda_links);
        Boolean whyMazdaLinksResult = link_test.test();
        System.out.println("Why Mazda Navigation Links: " + ((whyMazdaLinksResult) ? "PASS" : "FAIL"));

        //Displayed verification test
        utils.clickAndWait(props.getProperty("globalHeader_shoppingtoollink"),1000);
        displayed_test.prepare(props.getProperty("shopping_tools_dropdown"));
        Boolean shoppingToolDisplayed = displayed_test.test();
        System.out.println("Shopping Tools dropdown: " + ((shoppingToolDisplayed) ? "PASS" : "FAIL"));

        //Shopping tools sub menu Hover Verification
        WebElement shopping_tools_dropdown = driver.findElement(By.xpath(props.getProperty("shopping_tools_dropdown_options")));
        Boolean shopping_tools_hover_result = hover_test.testCollection(shopping_tools_dropdown, "tag", "a", "color");
        System.out.println("Shopping Tools Navigation Hover: " + ((shopping_tools_hover_result) ? "PASS" : "FAIL"));

        //Why Shopping Tools menu links verification
        link_test.prepare("collection");
        ArrayList<String> shopping_links = new ArrayList<String>();
        shopping_links.add(props.getProperty("build_and_price_link"));
        shopping_links.add(props.getProperty("compare_vehicles_link"));
        shopping_links.add(props.getProperty("inventory_search_link"));
        shopping_links.add(props.getProperty("cpo_inventory_search_link"));
        shopping_links.add(props.getProperty("trade_in_estimator_link"));
        shopping_links.add(props.getProperty("payment_estimator_link"));
        shopping_links.add(props.getProperty("special_offers_link"));
        shopping_links.add(props.getProperty("apply_for_financing_link"));
        shopping_links.add(props.getProperty("request_quote_link"));
        link_test.setCollectionData(shopping_tools_dropdown, "tag", "a", shopping_links);
        Boolean shopping_linksResult = link_test.test();
        System.out.println("Shopping Tools Links: " + ((shopping_linksResult) ? "PASS" : "FAIL"));

    }

    @Override
    public void prepare() {

    }
}