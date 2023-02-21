package com.dot;

import com.dot.model.BlockedIP;
import com.dot.model.UserAccessLog;
import com.dot.repo.BlockedIPRepository;
import com.dot.repo.UserAccessLogRepository;
import com.dot.utils.DataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class FileReader{
        //implements CommandLineRunner {
    public static String start;
    private static String duration;
    private static int limit;
    List<UserAccessLog> result;

    @Autowired
    private UserAccessLogRepository userAccessLogRepository ;

    @Autowired
    private BlockedIPRepository blockedIPRepository;

    public FileReader() {}

    //@Override
    //public void run(String... args) throws Exception {System.out.println("Run Error(i git here first): "+Arrays.toString(args));}

    public void run(String...args) {


        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--limit=")) {
                limit = Integer.parseInt(args[i].substring(8));
            }
            if (args[i].startsWith("--start=")) {
                char oldWord ='.', newWord = ' ';
                start = args[i].substring(8).replace(oldWord,newWord);
            }
            if (args[i].startsWith("--duration=")) {
                duration = args[i].substring(11);
            }
        }

        /*
        limit = 250;
        start = "2022-01-01 13:00:00";
        duration = "daily";
        //System.out.println("Entries: "+this.start+" - "+this.duration+" - "+this.limit);
       */

        try {
            // Code to execute the program with the given limit
            //System.out.println("Entries: "+this.start+" - "+this.duration+" - "+this.limit);
            //System.out.println(formatDate(this.start,this.duration));
            String end_date ;
            System.out.println("Printing IPs that exceeds "+limit+" \n\n");

            // check if duration is hourly or daily
            if(this.duration.equals("hourly")){
                end_date = formatDate(this.start,this.duration);

                // list records that exceed hourly limit
                result = userAccessLogRepository.checkHourlyRequest(this.start,end_date,this.limit);
            }else {
                end_date = formatDate(this.start,this.duration);

                // list records that exceed daily limit
                result = userAccessLogRepository.checkDailyRequest(this.start,end_date,this.limit);
            }


            // insert records to blocked ip table
            result.parallelStream().forEach( i->{
                System.out.println("Entries: "+i.getDate()+"___"+i.getIp_address()+"___"+i.getRequest_type()+"___"+i.getStatus_code()+"___"+i.getUser_agent());
                BlockedIP blockedIP = new BlockedIP(i.getIp_address(),i.getRequest_type(),"DOS Attack");
                blockedIPRepository.save(blockedIP);
            });

        }catch (Exception ex){
            System.out.println("FileReader class Error: "+ex.getMessage());
            ex.printStackTrace();
            ex.fillInStackTrace();
        }
    }


    private static String formatDate(String date,String duration){
        LocalDateTime localDateTime = null;
        if(duration.equals("hourly")){
            localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).plusHours(1);
        }else {
            localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).plusDays(1);
        }
        String newDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS"));
        return  newDate;
    }

}
