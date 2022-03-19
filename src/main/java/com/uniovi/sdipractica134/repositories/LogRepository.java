package com.uniovi.sdipractica134.repositories;

import com.uniovi.sdipractica134.entities.Log;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface LogRepository extends CrudRepository<Log, Long> {

    List<Log> findAll();

    @Query("SELECT l FROM Log l WHERE l.logType = (?1)")
    List<Log> findAllByLogtype(String logType);


}
