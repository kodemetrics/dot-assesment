package com.dot.utils;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class FTT {

    public void start()  {
        //System.out.println("you they learn");
    }

    @EventListener(ApplicationReadyEvent.class)
    void doSomething(){
        //System.out.println("i got here me the event");
    }


    public static void main(String[] args) {
        String date = "2022-01-01 13:00:00";
        String duration = "daily";
        System.out.println(formatDate(date,duration));
    }


    /*
    private static String formatDate(String date){
        LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).plusDays(1);
        String newDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS"));
        return  newDate;
    }
    */

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
