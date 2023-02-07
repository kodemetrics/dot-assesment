package com.dot.utils;

import com.dot.model.UserAccessLog;
import com.dot.repo.UserAccessLogRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadFromText extends  Thread{
    private String[] args;
    private UserAccessLogRepository userAccessLogRepository;
    private int limit;
    private String start,duration,accessFile;

    public ReadFromText(String[] args, UserAccessLogRepository userAccessLogRepository) {
        this.args = args;
        this.userAccessLogRepository = userAccessLogRepository;
    }

    @Override
    public void run() {
        super.run();

//        limit = 100;
//        start = "2022-01-01 13:00:00";
//        duration = "hourly";
//        accessFile="C:\\Users\\kingsley.okafor\\Desktop\\user_access.txt";

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--limit=")) {
                limit = Integer.parseInt(args[i].substring(8));
            }
            if (args[i].startsWith("--start=")) {
                start = args[i].substring(8);
            }
            if (args[i].startsWith("--duration=")) {
                duration = args[i].substring(11);
            }

            if (args[i].startsWith("--accessFile=")) {
                accessFile = args[i].substring(13);
            }
        }
        if (accessFile == null || start == null || accessFile == null || limit == 0 ) {
            System.err.println("Error: one of the flags is empty");
            System.exit(1);
        }

        File file = new File(accessFile);
        if (file.exists()) {
            System.out.println("File exists");
            readTextFromFile(accessFile); // read text file and insert records to database
        } else {
            System.out.println("File does not exist");
        }
    }

    public  void readTextFromFile(String filename){
        StringBuilder sb = new StringBuilder();
        final List<UserAccessLog> userAccessLogList = new ArrayList<>();

        BufferedReader br = null;
        String line;
        try{
            br = new BufferedReader(new java.io.FileReader(filename));

            while ((line = br.readLine()) != null){
                String[] data = line.split("\\|");
                UserAccessLog userAccessLog = new UserAccessLog(data[0],data[1],data[2],Integer.valueOf(data[3]),data[4]);
                userAccessLogList.add(userAccessLog);
            }


            System.out.println("Inserting records ..");
            userAccessLogList.parallelStream().forEach(i -> {
                userAccessLogRepository.save(i);
            } );
            System.out.println("Insert operation is complete");
            br.close();

        }catch (IOException ex){
            System.out.println("Error Reading & Inserting to Database: "+ex.getMessage());
        }
    }
}
