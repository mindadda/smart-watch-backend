package com.htlabs.smartwatch.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "client_location_mapping")
public class ClientLocation extends AuditEntity{

    @Id
    @Column(name = "client_location_id")
    private String clientLocationId;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "location_id")
    private String locationId;

    public ClientLocation(String clientLocationId , String clientId , String locationId){
        super();
        this.clientLocationId = clientLocationId;
        this.clientId = clientId;
        this.locationId = locationId;
    }

    public ClientLocation(){

    }
}
