package com.dot.utils;

import com.dot.model.UserAccessLog;
import java.io.*;
import java.util.*;


public class UserAccessLogReader {
    public List<UserAccessLog> readUserAccessLogs(String filename) {
        List<UserAccessLog> userAccessLogs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                UserAccessLog userAccessLog = new UserAccessLog(data[0], data[1], data[2], Integer.valueOf(data[3]), data[4]);
                userAccessLogs.add(userAccessLog);
            }
        } catch (IOException ex) {
            System.out.println("Error reading from file: " + ex.getMessage());
        }

        return userAccessLogs;
    }
}