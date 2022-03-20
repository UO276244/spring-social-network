package com.uniovi.sdipractica134;

import com.uniovi.sdipractica134.entities.Log;
import com.uniovi.sdipractica134.entities.User;
import com.uniovi.sdipractica134.pageobjects.*;
import com.uniovi.sdipractica134.repositories.PostsRepository;
import com.uniovi.sdipractica134.pageobjects.*;
import com.uniovi.sdipractica134.repositories.LogRepository;
import com.uniovi.sdipractica134.repositories.UsersRepository;
import com.uniovi.sdipractica134.services.UsersService;
import com.uniovi.sdipractica134.util.SeleniumUtils;
import org.apache.logging.log4j.spi.LoggerRegistry;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SdiPractica134ApplicationTests {

    /* MARTIN
    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    //static String Geckodriver = "C:\\Path\\geckodriver-v0.30.0-win64.exe";
    static String Geckodriver = "C:\\Users\\usuario\\Desktop\\Eii\\AÑO 3 GRADO INGENIERIA INFORMATICA\\Sistemas Distribuidos e Internet\\Lab\\sesion05\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";
*/


    //SARA
    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    //static String Geckodriver = "C:\\Path\\geckodriver-v0.30.0-win64.exe";
    static String Geckodriver = "C:\\Users\\Sara\\Desktop\\Universidad\\3-tercer curso\\segundo cuatri\\(SDI)-Sistemas Distribuidos e Internet\\Sesión5-material\\geckodriver-v0.30.0-win64.exe";

    //static String PathFirefox = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
    // static String Ge ckodriver = "/Users/USUARIO/selenium/geckodriver-v0.30.0-macos";


    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://localhost:8090";

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private LogRepository logRepository;


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
    void PR01_1() {
        int userBefore = usersRepository.countUsers();
        PO_SignUpView.goToSignUpPage(driver);
        PO_SignUpView.fillForm(driver,"martin@email.com","Martin","Beltran",
                "password","password");

        Assertions.assertTrue(usersRepository.findByUsername("martin@email.com") != null);
        Assertions.assertTrue(usersRepository.countUsers() > userBefore);

        usersRepository.deleteByUsername("martin@email.com");
    }


    //[Prueba1-2] Registro de Usuario con datos inválidos (username vacío, nombre vacío, apellidos vacíos).
    @Test
    @Order(2)
    void PR01_2() {

        int userBefore = usersRepository.countUsers();
        PO_SignUpView.goToSignUpPage(driver);
        PO_SignUpView.fillForm(driver,"","","",
                "password","password");

        Assertions.assertTrue(usersRepository.countUsers() ==userBefore);

    }


    //[Prueba1-3] Registro de Usuario con datos inválidos (repetición de contraseña inválida).
    @Test
    @Order(3)
    public void PR01_3() {
        int userBefore = usersRepository.countUsers();
        PO_SignUpView.goToSignUpPage(driver);
        PO_SignUpView.fillForm(driver,"martin@email.com","Martin","Beltran",
                "pass","pass");

        Assertions.assertTrue(usersRepository.countUsers() ==userBefore);



    }

    //[Prueba1-4] Registro de Usuario con datos inválidos (email existente).
    @Test
    @Order(4)
    public void PR01_4() {

        PO_SignUpView.goToSignUpPage(driver);
        PO_SignUpView.fillForm(driver,"martin@email.com","Martin","Beltran",
                "password","password");

        int userBefore = usersRepository.countUsers();
        Assertions.assertTrue(usersRepository.findByUsername("martin@email.com") != null);

        PO_SignUpView.goToSignUpPage(driver);
        PO_SignUpView.fillForm(driver,"martin@email.com","Martin","Beltran",
                "password","password");

        Assertions.assertTrue(usersRepository.countUsers() ==userBefore);
    }

    //[Prueba2-1] Inicio de sesión con datos válidos (administrador).
    @Test
    @Order(5)
    public void PR02_1() {

        PO_LoginView.goToLoginPage(driver);
        PO_LoginView.fillForm(driver,"admin@email.com","admin");


        //Si se ha logeado bien, podrá encontrar el boton de logout
        WebElement myDynamicElement = (new WebDriverWait(driver,
                10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("logout")));


        WebElement logoutButton = driver.findElement(By.id("logout"));
        Assertions.assertTrue(logoutButton != null);

        //Para comprobar, checkeamos que el menú de listar logs sea visible (solo admins)
        WebElement logsButton = driver.findElement(By.id("listLogs"));
        Assertions.assertTrue(logsButton != null);
    }


    //[Prueba2-2] Inicio de sesión con datos válidos (usuario estándar).
    @Test
    @Order(6)
    public void PR02_2() {


        PO_LoginView.goToLoginPage(driver);
        PO_LoginView.fillForm(driver,"user01@email.com","user01");


        //Si se ha logeado bien, podrá encontrar el boton de logout
        WebElement myDynamicElement = (new WebDriverWait(driver,
                10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("logout")));
        WebElement logoutButton = driver.findElement(By.id("logout"));
        Assertions.assertTrue(logoutButton != null);

        //Si se ha logeado bien, podra ver el menu de usuario "Mi Cuenta"
        WebElement myAccountDropdown = driver.findElement(By.id("accountDropdown"));
        Assertions.assertTrue(logoutButton != null);



    }

    //[Prueba2-3] Inicio de sesión con datos inválidos (usuario estándar, campo email y contraseña vacíos).
    @Test
    @Order(7)
    public void PR02_3() {


        //Te logueas con credenciales vacias
        PO_LoginView.goToLoginPage(driver);
        PO_LoginView.fillForm(driver,"","");

        //Sigues en la página de login
        List<WebElement> welcomeMessageElement = PO_LoginView.getLoginText(driver,PO_Properties.getSPANISH());

        Assertions.assertEquals(welcomeMessageElement.get(0).getText(),
                PO_View.getP().getString("login.message",
                        PO_Properties.getSPANISH()));



    }

    //[Prueba2-4] Inicio de sesión con datos válidos (usuario estándar, email existente, pero contraseña incorrecta).
    @Test
    @Order(8)
    public void PR02_4() {


        //Te logueas con email existente pero contraseña invalida
        PO_LoginView.goToLoginPage(driver);
        PO_LoginView.fillForm(driver,"user01@email.com","nopassword");

        //Sigues en la página de login
        List<WebElement> welcomeMessageElement = PO_LoginView.getLoginText(driver,PO_Properties.getSPANISH());

        Assertions.assertEquals(welcomeMessageElement.get(0).getText(),
                PO_View.getP().getString("login.message",
                        PO_Properties.getSPANISH()));



    }


    //[Prueba3-1] Hacer clic en la opción de salir de sesión
    // y comprobar que se redirige a la página de inicio de sesión (Login).
    @Test
    @Order(9)
    public void PR03_1() {


        //Te logueas con email existente
        PO_LoginView.goToLoginPage(driver);
        PO_LoginView.fillForm(driver,"user01@email.com","user01");

        //Puedes hacer logout
        PO_NavView.clickLogout(driver);

        //Te vas a la página de login
        List<WebElement> welcomeMessageElement = PO_LoginView.getLoginText(driver,PO_Properties.getSPANISH());

        Assertions.assertEquals(welcomeMessageElement.get(0).getText(),
                PO_View.getP().getString("login.message",
                        PO_Properties.getSPANISH()));

    }


    //[Prueba3-2] Comprobar que el botón cerrar sesión no está visible si el usuario no está autenticado
    @Test
    @Order(10)
    public void PR03_2() {


        //Si el usuario no esta autenticado y tratamos de buscar el boton,
        //se lanzará una NoSuchElementException
        try{

            driver.findElement(By.id("logout"));
            //El test fallará si encuentra el botón sin logearse
            Assertions.assertTrue(false);
        }catch(NoSuchElementException e){
            //El test pasa si no encuentra el botón sin logearse
            Assertions.assertTrue(true);
        }



    }



    //[Prueba24] Ir al formulario de crear publicaciones , rellenarlo con datos VÁLIDOS y pulsar el botón de enviar.
    @Test
    @Order(24)
    public void PR012A() {
        //El usuario debe estar registrado para hacer un post , por tanto
        PO_LoginView.fillForm(driver,"user17@email.com","user17");
        //Una vez autenticado el usuario,rellena el formulario
        PO_PostFormView.fillForm(driver,"Días de vacaciones", "Me lo he pasado genial en málaga! :)");

        //Vamos a la última página
        List<WebElement> elements= PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");
        //Nos vamos a la última página
        elements.get(1).click();
        elements=PO_View.checkElementBy(driver, "text", "Días de vacaciones");
        //Comprobamos que aparece la nueva publicación.
        Assertions.assertEquals("Días de vacaciones",elements.get(0).getText());

    }
    //[Prueba25] Ir al formulario de crear publicaciones , rellenarlo con datos INVÁLIDOS (título vacío) y pulsar el botón de enviar.
    @Test
    @Order(25)
    public void PR012B() {
        //El usuario debe estar registrado para hacer un post , por tanto
        PO_LoginView.fillForm(driver,"user01@email.com","user01");
        //Una vez autenticado el usuario,rellena el formulario
         PO_PostFormView.fillForm(driver,"", "Me lo he pasado genial en málaga! :)");

        List<WebElement> emptyMessage= PO_View.checkElementBy(driver, "text", PO_View.getP().getString("Error.posts.add.empty.title",PO_Properties.getSPANISH()));

        Assertions.assertTrue(emptyMessage.get(0).getText().contains("El título de la publicación no puede estar vacío."));
        //Como aparece también el mensaje de longitud, uso assert equals y contains para centrarme en el mensaje de vacío.
    }
    //[Prueba26]Mostrar el listado de publicaciones de un usuario y comprobar que se muestran todas las que existen para dicho usuario.
    @Test
    @Order(26)
    public void PR013A() {
        //El usuario debe estar registrado para hacer un post , por tanto
        PO_LoginView.fillForm(driver,"user17@email.com","user17");
        //Una vez inciada la sesión , el usuario podrá ver sus publicaciones.
        PO_NavView.clickListPosts(driver);
        //Una vez el usuario seleccione la opción de ver sus publicaciones, comprobamos que realmente se muestran.
         PO_ListPostsView.checkPosts(driver,5);

    }
    //[Prueba26-EXTRA]Mostrar el listado de publicaciones de un usuario que no tiene ninguna-> mensaje "No hay publicaciones"
    @Test
    @Order(26)
    public void PR013B() {
        //El usuario debe estar registrado para hacer un post , por tanto
        PO_LoginView.fillForm(driver,"user01@email.com","user01");
        //Una vez inciada la sesión , el usuario podrá ver sus publicaciones.(No tiene)
        PO_NavView.clickListPosts(driver);
        //Una vez el usuario seleccione la opción de ver sus publicaciones, comprobamos que realmente se muestran.
        List<WebElement>  noPostsMsg=PO_View.checkElementBy(driver, "text", PO_View.getP().getString("posts.list.noPosts",PO_Properties.getSPANISH()));
        Assertions.assertEquals("No hay posts disponibles.",noPostsMsg.get(0).getText());

    }

    //[PRUEBA EXTRA APARTADO 12]Comprobar que no se puede realizar una publicación sin cuerpo.
    @Test
    @Order(26)
    public void PR012B2() {
        //El usuario debe estar registrado para hacer un post , por tanto
        PO_LoginView.fillForm(driver,"user01@email.com","user01");
        //Una vez autenticado el usuario,rellena el formulario
        PO_PostFormView.fillForm(driver,"Vacaciones!", "");
        List<WebElement> emptyMessage= PO_View.checkElementBy(driver, "text", PO_View.getP().getString("Error.posts.add.empty.description",PO_Properties.getSPANISH()));
        Assertions.assertTrue(emptyMessage.get(0).getText().contains("La descripción de la publicación no puede estar vacía."));//Como aparece también el mensaje de longitud, uso assert equals y contains para centrarme en el mensaje de vacío.

    }
    //[PRUEBA EXTRA APARTADO 12]Comprobar que no se puede realizar una publicación con un título demasiado corto (menor a 10 caracteres)
    @Test
    @Order(27)
    public void PR012C() {
        //El usuario debe estar registrado para hacer un post , por tanto
        PO_LoginView.fillForm(driver,"user01@email.com","user01");
        //Una vez autenticado el usuario,rellena el formulario
        PO_PostFormView.fillForm(driver,"corto", "Descripción de más de 15 caracteres");
        List<WebElement> emptyMessage= PO_View.checkElementBy(driver, "text", PO_View.getP().getString("Error.posts.add.title.tooShort",PO_Properties.getSPANISH()));
        Assertions.assertEquals("El título debe tener al menos 10 caracteres.",emptyMessage.get(0).getText());

    }
    //[PRUEBA EXTRA APARTADO 12]Comprobar que no se puede realizar una publicación con una descripción demasiado corta (menor a 15 caracteres)
    @Test
    @Order(28)
    public void PR012D() {
        //El usuario debe estar registrado para hacer un post , por tanto
        PO_LoginView.fillForm(driver,"user01@email.com","user01");
        //Una vez autenticado el usuario,rellena el formulario
        PO_PostFormView.fillForm(driver,"Vacaciones!", "hola");
        List<WebElement> emptyMessage= PO_View.checkElementBy(driver, "text", PO_View.getP().getString("Error.posts.add.description.tooShort",PO_Properties.getSPANISH()));
        Assertions.assertEquals("La descripción debe tener al menos 15 caracteres.",emptyMessage.get(0).getText());

    }








    //[Prueba16-1] Intentar acceder sin estar autenticado a la opción de
    //listado de usuarios. Se deberá volver al formulario de login.
    @Test
    @Order(30)
    void PR016_1() {

        PO_LogsView.goToLogsPage(driver);
        List<WebElement> welcomeMessageElement = PO_LoginView.getLoginText(driver,PO_Properties.getSPANISH());

        Assertions.assertEquals(welcomeMessageElement.get(0).getText(),
                PO_View.getP().getString("login.message",
                        PO_Properties.getSPANISH()));


    }

    //[Prueba16-2] Intentar acceder sin estar autenticado a la opción de listado de invitaciones de amistad
    // recibida de un usuario estándar. Se deberá volver al formulario de login
    @Test
    @Order(31)
    void PR016_2() {

        PO_FriendsView.goToListFriendsInvitations(driver);
        List<WebElement> welcomeMessageElement = PO_LoginView.getLoginText(driver,PO_Properties.getSPANISH());

        Assertions.assertEquals(welcomeMessageElement.get(0).getText(),
                PO_View.getP().getString("login.message",
                        PO_Properties.getSPANISH()));


    }


    //[Prueba6-3] Estando autenticado como usuario estándar intentar acceder a una opción disponible
    // solo para usuarios administradores (Añadir menú de auditoria (visualizar logs)).
    // Se deberá indicar un mensaje de acción prohibida.
    @Test
    @Order(32)
    void PR016_3() {

        PO_SignUpView.goToSignUpPage(driver);
        PO_SignUpView.fillForm(driver,"martin@email.com","Martin","Beltran",
                "password","password");

        PO_LoginView.goToLoginPage(driver);
        PO_LoginView.fillForm(driver,"martin@email.com","password");

        PO_LogsView.goToLogsPage(driver);

        List<WebElement> ohohMessage = PO_ErrorView.getErrorText(driver,PO_Properties.getSPANISH());

        Assertions.assertEquals(ohohMessage.get(0).getText(), "OhOh");




    }


    //[Prueba16-4] Estando autenticado como usuario administrador visualizar
    // todos los logs generados en una serie de interacciones.
    // Esta prueba deberá generar al menos dos interacciones de cada tipo y
    // comprobar que el listado incluye los logs correspondientes.
    @Test
    @Order(33)
    void PR016_4() {

        //Generate several logs of different types
        PO_LogsView.generateBatchLogs(driver);

        String[] types = {"PET","LOGOUT","LOGIN_EX","LOGIN_ERR","ALTA"};

        List<WebElement> logsListed;
        for(String type : types){

            //Primer log de los PET
            logsListed = PO_LogsView.getLogListedInPosition(driver, type, 1);
            Assertions.assertEquals(logsListed.get(0).getText(), type);

            //Primer log de los PET
            logsListed = PO_LogsView.getLogListedInPosition(driver, type, 2);
            Assertions.assertEquals(logsListed.get(0).getText(), type);
        }


    }


    //[Prueba16-5] Estando autenticado como usuario administrador,
    // ir a visualización de logs, pulsar el botón/enlace borrar logs y
    // comprobar que se eliminan los logs de la base de datos
    @Test
    @Order(34)
    void PR016_5() {
        PO_LoginView.goToLoginPage(driver);
        PO_LoginView.fillForm(driver,"admin@email.com","admin");
        PO_LogsView.goToLogsPage(driver);

        List<Log> prevDelete = logRepository.findAll();
        int sizeBeforeDeletion = prevDelete.size();
        Assertions.assertTrue( sizeBeforeDeletion >= 0);


        PO_LogsView.deleteFirstLog(driver);

        List<Log> afterDelete = logRepository.findAll();
        int sizeAfterDeletion = afterDelete.size();



        Assertions.assertTrue(sizeAfterDeletion + 1 == sizeBeforeDeletion);

    }



}
