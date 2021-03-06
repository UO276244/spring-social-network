package com.uniovi.sdipractica134.services;

import com.uniovi.sdipractica134.entities.Log;
import com.uniovi.sdipractica134.entities.LogType;
import com.uniovi.sdipractica134.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LoggerService {

    @Autowired
    private LogRepository logRepo;


    public void deleteLog(Long id){
        logRepo.deleteById(id);
    }


    public List<Log> getAllByType(String logType){

        try{
            //Si no existe el tipo de log, lanza excepcion
            LogType.valueOf(logType);
            return logRepo.findAllByLogtype(logType);
        }catch(IllegalArgumentException | NullPointerException e){
            return logRepo.findAll();
        }

    }

    /**
     * Crea e inserta un log de tipo PET
     * @param controller controlador desde el que se crea
     * @param httpMethod método http
     * @param params parámetros que forman parte de la descripción
     * @return String descripción
     */
    public String createPETLog(String controller, String httpMethod, String... params){

        String description = "Controller: " + controller + " - Method " + httpMethod + " - Params: ";

        for(String st : params){
            description += " " + st + " ";
        }

        insertLog(LogType.PET,description);

        return description;
    }

    /**
     * Crea e inserta un log de tipo ALTA
     * @param controller controlador desde el que se crea
     * @param httpMethod método http
     * @param params parámetros que forman parte de la descripción
     * @return String descripción
     */
    public String createALTALog(String controller, String httpMethod, String... params){

        String description = "Controller: " + controller + " - Method " + httpMethod + " - Params: ";

        for(String st : params){
            description += " " + st + " ";
        }

        insertLog(LogType.ALTA,description);

        return description;
    }

    /**
     * Crea e inserta un log de tipo LOGIN_EX (login éxito)
     * @param username nombre de usuario del usuario que ha iniciado sesión correctamente
     * @return String descripción
     */
    public String createLOGIN_EXLog(String username){

        String description = "LogIn success by user: " + username;


        insertLog(LogType.LOGIN_EX,description);

        return description;
    }

    /**
     * Crea e inserta un log de tipo LOGIN_ERR (login error)
     * @param username nombre de usuario del usuario que ha iniciado sesión erróneamente
     * @return String descripción
     */
    public String createLOGIN_ERRLog(String username){

        String description = "LogIn error by user: " + username;


        insertLog(LogType.LOGIN_ERR,description);

        return description;
    }

    /**
     * Crea e inserta un log de tipo LOGOUT
     * @param username nombre de usuario del usuario que ha cerrado sesión
     * @return String descripción
     */
    public String createLOGOUTLog(String username){

        String description = "LogOut by user: " + username;


        insertLog(LogType.LOGOUT,description);

        return description;
    }

    /**
     * Método para insertar un log
     * @param type tipo de log
     * @param description mensaje descriptivo del log
     */
    private void insertLog(LogType type, String description){

        Log l = new Log(type.toString()
                ,new Timestamp(System.currentTimeMillis())
                ,description);
        logRepo.save(l);

    }

}
