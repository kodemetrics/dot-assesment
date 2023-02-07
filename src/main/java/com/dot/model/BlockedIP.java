package com.dot.model;


import javax.persistence.*;

@Entity
@Table(name = "Blocked_IP")
public class BlockedIP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    private String ipAddress;
    private String requestNumber;
    private String comment;

    public BlockedIP() {}

    public BlockedIP(String ipAddress, String requestNumber, String comment) {
        this.ipAddress = ipAddress;
        this.requestNumber = requestNumber;
        this.comment = comment;
    }
}
