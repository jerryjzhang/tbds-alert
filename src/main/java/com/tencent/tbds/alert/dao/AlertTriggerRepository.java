package com.tencent.tbds.alert.dao;

import com.tencent.tbds.alert.domain.AlertTrigger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jerryjzhang on 2016/3/21.
 */
@Repository
public interface AlertTriggerRepository extends JpaRepository<AlertTrigger, String>, JpaSpecificationExecutor {
    @Query("SELECT at FROM AlertTrigger at WHERE at.time >= :startTime")
    List<AlertTrigger> findAll(@Param("startTime") Timestamp startTime);

    @Modifying
    @Query("update AlertTrigger at set at.read = :read where at.id = :id")
    int setRead(@Param("id")String id, @Param("read")boolean read);
}
