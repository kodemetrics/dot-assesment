package com.dot.utils;


import com.dot.model.UserAccessLog;
import com.dot.repo.UserAccessLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    UserAccessLogRepository userAccessLogRepository;

    @GetMapping("/")
    private List<UserAccessLog> home(){
        String start_date = "2022-01-01 13:00:00" ,end_date ="2022-01-01 14:00:00";
        int limit = 100;
       //return userAccessLogRepository.getFirstThreeRecords();
        return  userAccessLogRepository.checkHourlyRequest(start_date,end_date,limit);
    }
}
