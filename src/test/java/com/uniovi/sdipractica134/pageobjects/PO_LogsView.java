package com.uniovi.sdipractica134.pageobjects;

import org.openqa.selenium.WebDriver;

public class PO_LogsView extends PO_NavView {



    public static void goToLogsPage(WebDriver driver){
        driver.get("localhost:8090/logs/list");
    }

}
