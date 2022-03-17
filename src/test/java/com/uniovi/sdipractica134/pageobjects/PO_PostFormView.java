package com.uniovi.sdipractica134.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_PostFormView {
    static public void fillForm(WebDriver driver, String title, String description) {
        //Write in the title
        WebElement titleElemnt = driver.findElement(By.name("title"));
        titleElemnt.click();
        titleElemnt.clear();
        titleElemnt.sendKeys(title);

        //Write in the description
        WebElement descriptionElement = driver.findElement(By.name("description"));
        descriptionElement .click();
        descriptionElement .clear();
        descriptionElement .sendKeys(description);

        //Send the form to publish the post.
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }

    public static void goToPostFormView(WebDriver driver) {
    }
}
