package com.uniovi.sdipractica134;

import com.uniovi.sdipractica134.entities.User;
import com.uniovi.sdipractica134.pageobjects.PO_Properties;
import com.uniovi.sdipractica134.pageobjects.PO_SignUpView;
import com.uniovi.sdipractica134.repositories.UsersRepository;
import com.uniovi.sdipractica134.services.UsersService;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SdiPractica134ApplicationTests {

    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    //static String Geckodriver = "C:\\Path\\geckodriver-v0.30.0-win64.exe";
    static String Geckodriver = "C:\\Users\\usuario\\Desktop\\Eii\\AÑO 3 GRADO INGENIERIA INFORMATICA\\Sistemas Distribuidos e Internet\\Lab\\sesion05\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

    //static String PathFirefox = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
    // static String Geckodriver = "/Users/USUARIO/selenium/geckodriver-v0.30.0-macos";


    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://localhost:8090";

    @Autowired
    private UsersRepository usersRepository;

    //Común a Windows y a MACOSX
    public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckodriver);
        driver = new FirefoxDriver();
        return driver;
    }

    @BeforeEach
    public void setUp() {
        driver.navigate().to(URL);
    } //Después de cada prueba se borran las cookies del navegador

    @AfterEach
    public void tearDown() {
        driver.manage().deleteAllCookies();

        usersRepository.deleteAll();
    }

    //Antes de la primera prueba
    @BeforeAll
    static public void begin() {
    }

    //Al finalizar la última prueba
    @AfterAll
    static public void end() {
        //Cerramos el navegador al finalizar las pruebas
        driver.quit();
    }



    //[Prueba1-1] Registro de Usuario con datos válidos.
    @Test
    @Order(1)
    void PR01A() {
        PO_SignUpView.goToSignUpPage(driver);
        PO_SignUpView.fillForm(driver,"martin@gmail.com","Martin","Beltran",
                "password","password");

        Assertions.assertTrue(usersRepository.findByEmail("martin@gmail.com") != null);
        Assertions.assertTrue(usersRepository.countUsers() ==1);

    }


    //[Prueba1-2] Registro de Usuario con datos inválidos (email vacío, nombre vacío, apellidos vacíos).
    @Test
    @Order(2)
    void PR01B() {
        PO_SignUpView.goToSignUpPage(driver);
        PO_SignUpView.fillForm(driver,"","","",
                "password","password");

        Assertions.assertTrue(usersRepository.countUsers() ==0);
    }


    //[Prueba1-3] Registro de Usuario con datos inválidos (repetición de contraseña inválida).
    @Test
    @Order(3)
    public void PR01C() {

        PO_SignUpView.goToSignUpPage(driver);
        PO_SignUpView.fillForm(driver,"martin@gmail.com","Martin","Beltran",
                "pass","pass");

        Assertions.assertTrue(usersRepository.countUsers() ==0);


    }

    //[Prueba1-4] Registro de Usuario con datos inválidos (email existente).
    @Test
    @Order(4)
    public void PR01D() {


        PO_SignUpView.goToSignUpPage(driver);
        PO_SignUpView.fillForm(driver,"martin@gmail.com","Martin","Beltran",
                "password","password");

        Assertions.assertTrue(usersRepository.findByEmail("martin@gmail.com") != null);

        PO_SignUpView.goToSignUpPage(driver);
        PO_SignUpView.fillForm(driver,"martin@gmail.com","Martin","Beltran",
                "password","password");

        Assertions.assertTrue(usersRepository.countUsers() ==1);
    }


}
