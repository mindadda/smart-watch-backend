package com.htlabs.smartwatch.repository;

import com.htlabs.smartwatch.entity.ClientDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClientDetailRepository extends JpaRepository<ClientDetails,String> {

    //public UserDetails findByEmail(String email);

    public ClientDetails findByClientPhone(String clientPhone);

    @Modifying
    @Transactional
    @Query(value = "UPDATE client set client_name = :#{#clientName} where client_id = :#{#clientId}", nativeQuery = true)
    public void updateName(String clientId,String clientName);



    @Modifying
    @Transactional
    @Query(value = "UPDATE client set client_address = :#{#clientAddress} where client_id = :#{#clientId}", nativeQuery = true)
    public void updateAddress(String clientId,String clientAddress);



    @Modifying
    @Transactional
    @Query(value = "DELETE FROM client where client_id = :#{#clientId}", nativeQuery = true)
    public void deleteClient(String clientId);
}
