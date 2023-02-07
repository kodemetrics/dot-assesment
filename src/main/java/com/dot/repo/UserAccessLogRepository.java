package com.dot.repo;

import com.dot.model.UserAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;



public interface UserAccessLogRepository extends JpaRepository<UserAccessLog, Long> {
    @Query(value = "SELECT * from USER_ACCESS_LOG s ORDER BY s.id LIMIT 3",nativeQuery = true)
    List<UserAccessLog> getFirstThreeRecords();

    @Query(value = " SELECT * from USER_ACCESS_LOG WHERE date BETWEEN :start_date AND :end_date GROUP BY ip_address HAVING COUNT(ip_address) > :limit",nativeQuery = true)
    List<UserAccessLog> checkHourlyRequest(String start_date,String end_date,int limit);

    @Query(value = " SELECT * from USER_ACCESS_LOG WHERE date BETWEEN :start_date AND :end_date GROUP BY ip_address HAVING COUNT(ip_address) > :limit",nativeQuery = true)
    List<UserAccessLog> checkDailyRequest(String start_date,String end_date,int limit);


    // check if IP address exceed request limit
    @Query(value = " SELECT COUNT(ip_address) from USER_ACCESS_LOG WHERE date BETWEEN :start_date AND :end_date AND ip_address = :ip_address",nativeQuery = true)
    int hasExceedHourlyRequest(String ip_address,String start_date,String end_date);

}
