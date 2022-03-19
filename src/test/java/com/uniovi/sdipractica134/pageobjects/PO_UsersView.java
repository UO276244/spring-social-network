package com.uniovi.sdipractica134.pageobjects;

import com.uniovi.sdipractica134.util.SeleniumUtils;
import org.openqa.selenium.WebDriver;

public class PO_UsersView extends PO_NavView{
    public static void goToUsersList(WebDriver driver){
        driver.get("localhost:8090/user/list");
        SeleniumUtils.waitLoadElementsBy(driver, "id", "tableUsers", 20);
    }
}
