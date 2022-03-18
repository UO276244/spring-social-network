package com.uniovi.sdipractica134.repositories;

import com.uniovi.sdipractica134.entities.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;


public interface LogRepository extends CrudRepository<Log, Long> {

    Page<Log> findAll(Pageable pageable);

    @Query("SELECT l FROM Log l WHERE l.logType LIKE (?1)")
    Page<Log> findByLogType(Pageable pageable, String logType);


}
