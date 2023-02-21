package com.dot.model;


import javax.persistence.*;

@Entity
@Table(name = "USER_ACCESS_LOG")
public class UserAccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date;
    private String ip_address;
    private String request_type;
    private int status_code;
    private String user_agent;

    public UserAccessLog() {}
    public UserAccessLog(String date, String ip_address, String request_type, int status_code, String user_agent) {
        this.date = date;
        this.ip_address = ip_address;
        this.request_type = request_type;
        this.status_code = status_code;
        this.user_agent = user_agent;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }
}
