package com.htlabs.smartwatch.service;

import com.htlabs.smartwatch.dto.ClientDTO;

import java.util.List;

public interface ClientService {

    public String createClient(ClientDTO dto);

    public String updateClient(String clientId, String clientName, String ClientAddress );

    public String deleteClient(String clientId);

    public ClientDTO getClientById(String clientId);

    public ClientDTO getClientByName(String clientName);

    public List<ClientDTO> getAllClients();
}
