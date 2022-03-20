package com.uniovi.sdipractica134.controllers;

import com.uniovi.sdipractica134.entities.Log;
import com.uniovi.sdipractica134.services.LoggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class LogController {

    Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private LoggerService loggerService;

    @RequestMapping("/logs/list")
    public String getList(Model model,
                          @RequestParam(value = "", required = false) String logType){


        logger.info(
                loggerService.createPETLog("LogController --> /logs/list",
                        "GET",
                        new String[] {"logType="+logType})
        );

        findLogsAndReturnFragment(logType,model);

        return "logs/list";
    }

    @RequestMapping("/logs/delete/{id}")
    public String deleteLog(Model model, @PathVariable Long id) {
        loggerService.deleteLog(id);

        List<Log> logs = loggerService.getAllByType("");

        //Se ordena de más antiguo a más reciente
        Collections.sort(logs);

        //Hay que darle la vuelta para mostrar los logs de más reciente a más antiguo
        Collections.reverse(logs);

        model.addAttribute("logsList", logs);

        return "logs/list :: tableLogs";
    }

//th:href="${'/logs/delete/' + log.getId()}"


    @RequestMapping("/logs/list/update")
    public String update(Model model, @RequestParam(value = "", required = false) String logType) {


        /*
        logger.info(
                loggerService.createPETLog("LogController --> /logs/list/update",
                        "GET",
                        new String[] {"logType="+logType})
        );
*/

        return findLogsAndReturnFragment(logType,model);
    }


    private String findLogsAndReturnFragment(String logType, Model model){

        List<Log> logs = loggerService.getAllByType(logType);

        //Se ordena de más antiguo a más reciente
        Collections.sort(logs);

        //Hay que darle la vuelta para mostrar los logs de más reciente a más antiguo
        Collections.reverse(logs);

        model.addAttribute("logsList", logs);

        return "logs/list :: tableLogs";

    }

}
