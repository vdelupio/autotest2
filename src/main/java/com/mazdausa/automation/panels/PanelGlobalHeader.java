package com.mazdausa.automation.panels;

import com.mazdausa.automation.app.ExecState;
import com.mazdausa.automation.app.Utils;
import com.mazdausa.automation.cases.*;
import com.mazdausa.automation.panels.PanelGlobalHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.logging.Level;
import java.util.logging.Logger;


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
    private imageLoadedTest image_loaded_test;
    private static final Logger logger = Logger.getLogger(PanelGlobalHeader.class.getName());


    public PanelGlobalHeader(){
        driver = ExecState.getDriver();
        props = ExecState.getProps();
        hover_test = new HoverVerificationTest();
        link_test = new LinkVerificationTest();
        displayed_test = new IsDisplayedTest();
        has_class_test = new HasClassTest();
        image_loaded_test = new imageLoadedTest();
        utils = new Utils();
        utils.setDriver(driver);
        this.execute();
    }

    public void execute(){

        //LOGGER
        logger.log(Level.INFO,"Starting PanelGlobalHeader");



        //Mazda_Logo Link verification
        WebElement global_header_logo = driver.findElement(By.xpath(props.getProperty("mazda_logo")));
        link_test.prepare("single");
        link_test.setSingleData(global_header_logo, props.getProperty("musa_homepage_url_prod"));
        Boolean global_logo_result = link_test.test();
        logger.log(Level.INFO,"Global Mazda Logo link: " + ((global_logo_result) ? "PASS" : "FAIL"));

        //hover verification globalHeader parent
        WebElement global_header_link = driver.findElement(By.xpath(props.getProperty("globalheader_parent")));
        hover_test.prepare("collection");
        hover_test.setCollectionData(global_header_link, "tag", "a", "color");
        Boolean global_hover_result = hover_test.test();
        logger.log(Level.INFO,"Global Navigation Hover: " + ((global_hover_result) ? "PASS" : "FAIL"));

        //Vehicles expand verification test
        utils.clickAndWait(props.getProperty("globalHeader_vehiclelink"),1000);
        has_class_test.prepare(props.getProperty("vehicles_container"),"show");
        Boolean vehicles_displayed = has_class_test.test();
        logger.log(Level.INFO,"Vehicles dropdown expand: " + ((vehicles_displayed) ? "PASS" : "FAIL"));

        //Vehicles sub menu Hover Verification
        WebElement vehicles_dropdown_column_4 = driver.findElement(By.xpath(props.getProperty("vehicles_dropdown_column_4")));
        hover_test.prepare("collection");
        hover_test.setCollectionData(vehicles_dropdown_column_4, "css", "cta", "color");
        Boolean vehicles_hover_result = hover_test.test();
        logger.log(Level.INFO,"Vehicles Navigation Hover: " + ((vehicles_hover_result) ? "PASS" : "FAIL"));

        //Vehicles menu links verification
        WebElement vehicles_dropdown = driver.findElement(By.xpath(props.getProperty("vehicles_dropdown_options")));
        link_test.prepare("collection");
        ArrayList<String> vehicles_links = new ArrayList<String>();
        vehicles_links.add(props.getProperty("mazda3_4_door_link"));
        vehicles_links.add(props.getProperty("mazda3_5_door_link"));
        vehicles_links.add(props.getProperty("mazda_6_link"));
        vehicles_links.add(props.getProperty("mazda_cx3_link"));
        vehicles_links.add(props.getProperty("mazda_cx5_link"));
        vehicles_links.add(props.getProperty("mazda_cx9_link"));
        vehicles_links.add(props.getProperty("mazda_mx5_link"));
        vehicles_links.add(props.getProperty("mazda_mx5_rf_link"));
        vehicles_links.add(props.getProperty("explore_vehicle_link"));
        vehicles_links.add(props.getProperty("learn_more_link"));
        link_test.setCollectionData(vehicles_dropdown, "css", ".vehicle-nav__image-wrapper a, .cta", vehicles_links);
        Boolean vehicles_linksResult = link_test.test();
        logger.log(Level.INFO,"Vehicles  Links: " + ((vehicles_linksResult) ? "PASS" : "FAIL"));

        //Vehicles images loaded test
        image_loaded_test.prepare("collection");
        image_loaded_test.setCollectionData(vehicles_dropdown, "tag", "img");
        Boolean vehicles_images_result = image_loaded_test.test();
        logger.log(Level.INFO,"Vehicles images: " + ((vehicles_images_result) ? "PASS" : "FAIL"));

        //Vehicles retract verification test
        utils.clickAndWait(props.getProperty("globalHeader_vehiclelink"),1000);
        has_class_test.prepare(props.getProperty("vehicles_container"),"show");
        Boolean vehicles_retract = has_class_test.test();
        logger.log(Level.INFO,"Vehicles dropdown retract: " + ((!vehicles_retract) ? "PASS" : "FAIL"));

        //Shopping tools expand verification test
        utils.clickAndWait(props.getProperty("globalHeader_shoppingtoollink"),1000);
        has_class_test.prepare(props.getProperty("shopping_tools_container"),"show");
        Boolean shopping_tools_displayed = has_class_test.test();
        logger.log(Level.INFO,"Shopping Tools dropdown: " + ((shopping_tools_displayed) ? "PASS" : "FAIL"));

        //Shopping tools sub menu Hover Verification
        WebElement shopping_tools_dropdown = driver.findElement(By.xpath(props.getProperty("shopping_tools_dropdown_options")));
        hover_test.prepare("collection");
        hover_test.setCollectionData(shopping_tools_dropdown, "tag", "a", "color");
        Boolean shopping_tools_hover_result = hover_test.test();
        logger.log(Level.INFO,"Shopping Tools Navigation Hover: " + ((shopping_tools_hover_result) ? "PASS" : "FAIL"));

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
        logger.log(Level.INFO,"Shopping Tools Links: " + ((shopping_linksResult) ? "PASS" : "FAIL"));

        //Shopping tools retract verification test
        utils.clickAndWait(props.getProperty("globalHeader_shoppingtoollink"),1000);
        has_class_test.prepare(props.getProperty("shopping_tools_container"),"show");
        Boolean shopping_tools_retract = has_class_test.test();
        logger.log(Level.INFO,"Shopping Tools dropdown: " + ((!shopping_tools_retract) ? "PASS" : "FAIL"));

        //Why Mazda Expand verification test
        utils.clickAndWait(props.getProperty("globalHeader_whymazdalink"),1000);
        has_class_test.prepare(props.getProperty("why_mazda_container"),"show");
        Boolean why_mazda_displayed = has_class_test.test();
        logger.log(Level.INFO,"Why Mazda dropdown expand: " + ((why_mazda_displayed) ? "PASS" : "FAIL"));

        //Why Mazda sub menu Hover Verification
        WebElement why_mazda_dropdown = driver.findElement(By.xpath(props.getProperty("why_mazda_dropdown_options")));
        hover_test.prepare("collection");
        hover_test.setCollectionData(why_mazda_dropdown, "tag", "a", "color");
        Boolean why_mazda_menu_hover_result = hover_test.test();
        logger.log(Level.INFO,"Why Mazda Navigation Hover: " + ((why_mazda_menu_hover_result) ? "PASS" : "FAIL"));

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
        logger.log(Level.INFO,"Why Mazda Navigation Links: " + ((why_mazda_links_result) ? "PASS" : "FAIL"));

        //Why Mazda Retract verification test
        utils.clickAndWait(props.getProperty("globalHeader_whymazdalink"),2000);
        has_class_test.prepare(props.getProperty("why_mazda_container"),"show");
        Boolean why_mazda_retract = has_class_test.test();
        logger.log(Level.INFO,"Why Mazda dropdown retract: " + ((!why_mazda_retract) ? "PASS" : "FAIL"));

        //Owners Expand verification test
        utils.clickAndWait(props.getProperty("globalHeader_ownerslink"),1000);
        has_class_test.prepare(props.getProperty("owners_container"),"show");
        Boolean owners_displayed = has_class_test.test();
        logger.log(Level.INFO,"Owners dropdown expand: " + ((owners_displayed) ? "PASS" : "FAIL"));

        //Owners sub menu Hover Verification
        WebElement owners_dropdown = driver.findElement(By.xpath(props.getProperty("owners_dropdown_options")));
        hover_test.prepare("collection");
        hover_test.setCollectionData(owners_dropdown, "tag", "a", "color");
        Boolean owners_menu_hover_result = hover_test.test();
        logger.log(Level.INFO,"Owners Navigation Hover: " + ((owners_menu_hover_result) ? "PASS" : "FAIL"));

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
        logger.log(Level.INFO,"Owners Navigation Links: " + ((owners_links_result) ? "PASS" : "FAIL"));

        //Owners Retract verification test
        utils.clickAndWait(props.getProperty("globalHeader_ownerslink"),2000);
        has_class_test.prepare(props.getProperty("owners_container"),"show");
        Boolean owners_retract = has_class_test.test();
        logger.log(Level.INFO,"Owners dropdown retract: " + ((!owners_retract) ? "PASS" : "FAIL"));

        //Find a dealer link verification
        link_test.prepare("single");
        WebElement find_a_dealer_option = driver.findElement(By.xpath(props.getProperty("globalHeader_findadealerlink")));
        link_test.setSingleData(find_a_dealer_option,props.getProperty("find_a_dealer_link"));
        Boolean find_a_dealer_link_result = link_test.test();
        logger.log(Level.INFO,"Find a dealer link: " + ((find_a_dealer_link_result) ? "PASS" : "FAIL"));

        //Location icon
        displayed_test.prepare(props.getProperty("local_icon"));
        Boolean is_displayed_icon = displayed_test.test();
        logger.log(Level.INFO,"Location icon is displayed: " + ((is_displayed_icon) ? "PASS" : "FAIL"));

        //Location icon hover color test
        WebElement location_icon = driver.findElement(By.xpath(props.getProperty("local_icon")));
        //Test hover color on :before pseudo element
        String script = "return window.getComputedStyle(document.querySelector('a.map-icon.map-icon--has-zip'),':before').getPropertyValue('color')";
        JavascriptExecutor js = (JavascriptExecutor)driver;
        String initial_color = (String) js.executeScript(script);
        Actions action = new Actions(ExecState.getDriver());
        action.moveToElement(location_icon).build().perform();
        String hovered_color = (String) js.executeScript(script);
        logger.log(Level.INFO,"Location icon color hover: " + ((!initial_color.equals(hovered_color)) ? "PASS" : "FAIL"));

        //Location icon hover expand test
        action.moveToElement(location_icon).build().perform();
        has_class_test.prepare(props.getProperty("navigation_global_wrapper"),"map-icon--hover");
        Boolean location_icon_hover_expand = has_class_test.test();
        logger.log(Level.INFO,"Location icon hover expand: " + ((location_icon_hover_expand) ? "PASS" : "FAIL"));

        //Location icon click test
        utils.clickAndWait(props.getProperty("local_icon"),2000);
        displayed_test.prepare(props.getProperty("your_location_panel"));
        Boolean is_displayed_your_location = displayed_test.test();
        logger.log(Level.INFO,"You Location popup is displayed: " + ((is_displayed_your_location) ? "PASS" : "FAIL"));

    }

    @Override
    public void prepare() {

    }
}