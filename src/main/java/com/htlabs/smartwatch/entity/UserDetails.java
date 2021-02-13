package com.htlabs.smartwatch.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "user_details")
public class UserDetails extends AuditEntity{

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_no")
    private String phoneNo;

    public UserDetails(String userId, String name, String role, String email, String phoneNo){
        super();
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.email = email;
        this.phoneNo = phoneNo;
    }


    public UserDetails() {

    }
}
