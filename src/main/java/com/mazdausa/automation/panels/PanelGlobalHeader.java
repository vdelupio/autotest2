package com.mazdausa.automation.panels;

import com.mazdausa.automation.app.ExecState;
import com.mazdausa.automation.app.Utils;
import com.mazdausa.automation.cases.HasClassTest;
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
    private HasClassTest has_class_test;

    public PanelGlobalHeader(){
        driver = ExecState.getDriver();
        props = ExecState.getProps();
        hover_test = new HoverVerificationTest();
        link_test = new LinkVerificationTest();
        displayed_test = new IsDisplayedTest();
        has_class_test = new HasClassTest();
        utils = new Utils();
        utils.setDriver(driver);
        this.execute();
    }


    public void execute(){

        System.out.println("Starting PanelGlobalHeader\n\n");

        //Mazda_Logo Link verification
        WebElement global_header_logo = driver.findElement(By.xpath(props.getProperty("mazda_logo")));
        link_test.prepare("single");
        link_test.setSingleData(global_header_logo, props.getProperty("musa_homepage_url_prod"));
        Boolean global_logo_result = link_test.test();
        System.out.println("Global Mazda Logo link: " + ((global_logo_result) ? "PASS" : "FAIL"));

        //hover verification globalHeader parent
        WebElement global_header_link = driver.findElement(By.xpath(props.getProperty("globalheader_parent")));
        Boolean global_hover_result = hover_test.testCollection(global_header_link, "tag", "a", "color");
        System.out.println("Global Navigation Hover: " + ((global_hover_result) ? "PASS" : "FAIL"));

        //Shopping tools expand verification test
        utils.clickAndWait(props.getProperty("globalHeader_shoppingtoollink"),1000);
        has_class_test.prepare(props.getProperty("shopping_tools_container"),"show");
        Boolean shopping_tools_displayed = has_class_test.test();
        System.out.println("Shopping Tools dropdown: " + ((shopping_tools_displayed) ? "PASS" : "FAIL"));

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

        //Shopping tools retract verification test
        utils.clickAndWait(props.getProperty("globalHeader_shoppingtoollink"),1000);
        has_class_test.prepare(props.getProperty("shopping_tools_container"),"show");
        Boolean shopping_tools_retract = has_class_test.test();
        System.out.println("Shopping Tools dropdown: " + ((!shopping_tools_retract) ? "PASS" : "FAIL"));

        //Why Mazda Expand verification test
        utils.clickAndWait(props.getProperty("globalHeader_whymazdalink"),1000);
        has_class_test.prepare(props.getProperty("why_mazda_container"),"show");
        Boolean why_mazda_displayed = has_class_test.test();
        System.out.println("Why Mazda dropdown expand: " + ((why_mazda_displayed) ? "PASS" : "FAIL"));

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
        Boolean why_mazda_links_result = link_test.test();
        System.out.println("Why Mazda Navigation Links: " + ((why_mazda_links_result) ? "PASS" : "FAIL"));

        //Why Mazda Retract verification test
        utils.clickAndWait(props.getProperty("globalHeader_whymazdalink"),2000);
        has_class_test.prepare(props.getProperty("why_mazda_container"),"show");
        Boolean why_mazda_retract = has_class_test.test();
        System.out.println("Why Mazda dropdown retract: " + ((!why_mazda_retract) ? "PASS" : "FAIL"));

        //Owners Expand verification test
        utils.clickAndWait(props.getProperty("globalHeader_ownerslink"),1000);
        has_class_test.prepare(props.getProperty("owners_container"),"show");
        Boolean owners_displayed = has_class_test.test();
        System.out.println("Owners dropdown expand: " + ((owners_displayed) ? "PASS" : "FAIL"));

        //Owners sub menu Hover Verification
        WebElement owners_dropdown = driver.findElement(By.xpath(props.getProperty("owners_dropdown_options")));
        Boolean owners_menu_hover_result = hover_test.testCollection(owners_dropdown, "tag", "a", "color");
        System.out.println("Owners Navigation Hover: " + ((owners_menu_hover_result) ? "PASS" : "FAIL"));

        //Owners sub menu links verification
        link_test.prepare("collection");
        ArrayList<String> owners_links = new ArrayList<String>();
        owners_links.add(props.getProperty("overview_link"));
        owners_links.add(props.getProperty("how_to_use_link"));
        owners_links.add(props.getProperty("recalls_link"));
        owners_links.add(props.getProperty("faq_link"));
        owners_links.add(props.getProperty("service_link"));
        owners_links.add(props.getProperty("parts_link"));
        owners_links.add(props.getProperty("accessories_link"));
        owners_links.add(props.getProperty("login_register_link"));
        link_test.setCollectionData(owners_dropdown, "tag", "a", owners_links);
        Boolean owners_links_result= link_test.test();
        System.out.println("Owners Navigation Links: " + ((owners_links_result) ? "PASS" : "FAIL"));

        //Owners Retract verification test
        utils.clickAndWait(props.getProperty("globalHeader_ownerslink"),2000);
        has_class_test.prepare(props.getProperty("owners_container"),"show");
        Boolean owners_retract = has_class_test.test();
        System.out.println("Owners dropdown retract: " + ((!owners_retract) ? "PASS" : "FAIL"));



    }

    @Override
    public void prepare() {

    }
}