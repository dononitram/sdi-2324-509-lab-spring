package com.uniovi.notaneitor.pageobjects;
import com.uniovi.notaneitor.util.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PO_PrivateView extends PO_NavView {
    static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep)
    {
        //Esperamos 5 segundo a que cargue el DOM porque en algunos equipos falla
        SeleniumUtils.waitSeconds(driver, 5);
        //Seleccionamos el alumno userOrder
        new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
        //Rellenemos el campo de descripción
        WebElement description = driver.findElement(By.name("description"));
        description.clear();
        description.sendKeys(descriptionp);
        WebElement score = driver.findElement(By.name("score"));
        score.click();
        score.clear();
        score.sendKeys(scorep);
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }

    public static void login(WebDriver driver, String dni, String password) {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, dni, password);
        PO_View.checkElementBy(driver, "text", dni);
    }

    public static void logout(WebDriver driver) {
        String loginText = PO_HomeView.getP().getString("signup.message", PO_Properties.getSPANISH());
        PO_PrivateView.clickOption(driver, "logout", "text", loginText);
    }

    public static void clickElementByXpath(WebDriver driver, String xpath, int pos) {
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", xpath);
        elements.get(pos).click();
    }

    public static WebElement getElementByText(WebDriver driver, String text, int pos) {
        List<WebElement> elements = PO_View.checkElementBy(driver, "text", text);
        return elements.get(pos);
    }

}