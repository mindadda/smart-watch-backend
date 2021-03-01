package com.htlabs.smartwatch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ClientLocationDTO {

    private String clientLocationId;

    private String clientId;

    private String locationId;

    private Date createdAt;

    private Date updatedAt;

}
