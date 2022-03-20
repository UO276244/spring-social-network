package com.uniovi.sdipractica134.pageobjects;

import com.uniovi.sdipractica134.util.SeleniumUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PO_FriendsView extends PO_View{


    public static void goToListFriendsInvitations(WebDriver driver){
        driver.get("localhost:8090/friends/invites");
    }
    public static void goToListFriends(WebDriver driver){

        driver.get("localhost:8090/friends/list");
    }


}
