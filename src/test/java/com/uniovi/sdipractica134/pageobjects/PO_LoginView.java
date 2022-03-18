package com.uniovi.sdipractica134.pageobjects;

import com.uniovi.sdipractica134.util.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PO_LoginView extends PO_NavView{


    static public List<WebElement> getLoginText(WebDriver driver, int language) {
        //Esperamos a que se cargue el saludo de bienvenida en Español
        return SeleniumUtils.waitLoadElementsBy(driver,
                "text",
                p.getString("login.message", language),
                getTimeout());
    }

}
