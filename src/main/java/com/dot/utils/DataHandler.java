package com.dot.utils;

import com.dot.model.UserAccessLog;
import com.dot.repo.UserAccessLogRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private static UserAccessLogRepository userAccessLogRepository;

    public DataHandler(UserAccessLogRepository userAccessLogRepository) {
        this.userAccessLogRepository = userAccessLogRepository;
    }

   public static void readTextFromFile(){
        List<UserAccessLog> userAccessLogList = new ArrayList<>();
        BufferedReader br = null;
        String line;
        try{
            br = new BufferedReader(new java.io.FileReader("user_access.txt"));
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null){
                String[] data = line.split("\\|");

                UserAccessLog userAccessLog = new UserAccessLog(data[0],data[1],data[2],Integer.valueOf(data[3]),data[4]);
                //UserAccessLog userAccessLog = new UserAccessLog(data[0],data[1],data[2],200,data[4]);
                userAccessLogList.add(userAccessLog);
                //userAccessLogRepository.save(userAccessLog);
                //userAccessLogRepository.saveAll(userAccessLogList);
                System.out.println(data);
            }
            userAccessLogList.parallelStream().forEach(i -> {
                userAccessLogRepository.save(i);
            } );
            //userAccessLogRepository.saveAll(userAccessLogList);
            br.close();
        }catch (IOException ex){
            System.out.println("Error Reading & Inserting: "+ex.getMessage());
        }
    }
}
