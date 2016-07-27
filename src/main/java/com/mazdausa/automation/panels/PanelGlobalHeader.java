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

        System.out.println("Starting PanelGlobalHeader");

        //Mazda_Logo Link verification
        WebElement globalHeaderlogo = driver.findElement(By.xpath(props.getProperty("mazda_logo")));
        Boolean globalLogoResult = link_test.testLink(globalHeaderlogo, "class", "mazda-logo__desktop",props.getProperty("musa_homepage_url_prod"),false);
        System.out.println("GlobalLogolink: " + ((globalLogoResult) ? "PASS" : "FAIL"));

        //hover verification globalHeader parent
        WebElement globalHeaderlink = driver.findElement(By.xpath(props.getProperty("globalheader_parent")));
        Boolean globaHoverResult = hover_test.testCollection(globalHeaderlink, "tag", "a", "color");
        System.out.println("Global Navigation Hover: " + ((globaHoverResult) ? "PASS" : "FAIL"));

        //Displayed Why Mazda verification test
        utils.clickAndWait(props.getProperty("globalHeader_whymazdalink"),1000);
        displayed_test.prepare(props.getProperty("why_mazda_dropdown"));
        Boolean whyMazdaDisplayed = displayed_test.test();
        System.out.println("Why Mazda dropdown: " + ((whyMazdaDisplayed) ? "PASS" : "FAIL"));

        //Why Mazda sub menu Hover Verification
        WebElement whyMazdaDropdown = driver.findElement(By.xpath(props.getProperty("why_mazda_dropdown_options")));
        Boolean whyMazdaMenuHoverResult = hover_test.testCollection(whyMazdaDropdown, "tag", "a", "color");
        System.out.println("Why Mazda Navigation Hover: " + ((whyMazdaMenuHoverResult) ? "PASS" : "FAIL"));

        //Displayed Shopping tool verification test
        utils.clickAndWait(props.getProperty("globalHeader_shoppingtoollink"),1000);
        displayed_test.prepare(props.getProperty("shopping_tools_dropdown"));
        Boolean shoppingToolDisplayed = displayed_test.test();
        System.out.println("Shopping Tools dropdown: " + ((shoppingToolDisplayed) ? "PASS" : "FAIL"));

        //

    }

    @Override
    public void prepare() {

    }
}