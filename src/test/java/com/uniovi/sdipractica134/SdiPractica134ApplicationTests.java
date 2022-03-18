package com.uniovi.sdipractica134;

import com.uniovi.sdipractica134.entities.User;
import com.uniovi.sdipractica134.pageobjects.PO_PostFormView;
import com.uniovi.sdipractica134.pageobjects.PO_Properties;
import com.uniovi.sdipractica134.pageobjects.PO_SignUpView;
import com.uniovi.sdipractica134.pageobjects.PO_View;
import com.uniovi.sdipractica134.repositories.PostsRepository;
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


    @Autowired
    private PostsRepository postsRepository;


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

    //[Prueba24] Ir al formulario de crear publicaciones , rellenarlo con datos VÁLIDOS y pulsar el botón de enviar.
    @Test
    @Order(5)
    public void PR012A() {
        //El usuario debe estar registrado para hacer un post , por tanto

        //Una vez autenticado el usuario,rellena el formulario
        PO_PostFormView.goToPostFormView(driver);
        PO_PostFormView.fillForm(driver,"Días de vacaciones", "Me lo he pasado genial en málaga! :)");

        //Vamos a la última página
       List<WebElement> elements= PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");
        //Nos vamos a la última página
        elements.get(3).click();
        elements=PO_View.checkElementBy(driver, "text", "Días de vacaciones");
        //Comprobamos que aparece la nueva publicación.
        Assertions.assertEquals("Días de vacaciones",elements.get(0).getText());

    }
    @Test
    @Order(6)
    public void PR012B() {
        //El usuario debe estar registrado para hacer un post , por tanto

        //Una vez autenticado el usuario,rellena el formulario
        PO_PostFormView.goToPostFormView(driver);
        PO_PostFormView.fillForm(driver,"", "Me lo he pasado genial en málaga! :)");

        List<WebElement> emptyMessage= PO_View.checkElementBy(driver, "text", PO_View.getP().getString("Error.posts.add.empty.title",PO_Properties.getSPANISH()));
        Assertions.assertEquals("El título de la publicación no puede estar vacío.",emptyMessage.get(0).getText());

    }
    //Comprobar que no se puede realizar una publicación sin cuerpo.
    @Test
    @Order(7)
    public void PR012B2() {
        //El usuario debe estar registrado para hacer un post , por tanto

        //Una vez autenticado el usuario,rellena el formulario
        PO_PostFormView.goToPostFormView(driver);
        PO_PostFormView.fillForm(driver,"Vacaciones!", "");
        List<WebElement> emptyMessage= PO_View.checkElementBy(driver, "text", PO_View.getP().getString("Error.posts.add.empty.description",PO_Properties.getSPANISH()));
        Assertions.assertEquals("La descripción de la publicación no puede estar vacía.",emptyMessage.get(0).getText());

    }
    //Comprobar que no se puede realizar una publicación con un título demasiado corto (menor a 10 caracteres)
    @Test
    @Order(8)
    public void PR012C() {
        //El usuario debe estar registrado para hacer un post , por tanto

        //Una vez autenticado el usuario,rellena el formulario
        PO_PostFormView.goToPostFormView(driver);
        PO_PostFormView.fillForm(driver,"corto", "Descripción de más de 15 caracteres");
        List<WebElement> emptyMessage= PO_View.checkElementBy(driver, "text", PO_View.getP().getString("Error.posts.add.title.tooShort",PO_Properties.getSPANISH()));
        Assertions.assertEquals("El título debe tener al menos 10 caracteres.",emptyMessage.get(0).getText());

    }
    //Comprobar que no se puede realizar una publicación con una descripción demasiado corta (menor a 15 caracteres)
    @Test
    @Order(8)
    public void PR012D() {
        //El usuario debe estar registrado para hacer un post , por tanto

        //Una vez autenticado el usuario,rellena el formulario
        PO_PostFormView.goToPostFormView(driver);
        PO_PostFormView.fillForm(driver,"Vacaciones!", "hola");
        List<WebElement> emptyMessage= PO_View.checkElementBy(driver, "text", PO_View.getP().getString("Error.posts.add.description.tooShort",PO_Properties.getSPANISH()));
        Assertions.assertEquals("La descripción debe tener al menos 15 caracteres.",emptyMessage.get(0).getText());

    }

}
