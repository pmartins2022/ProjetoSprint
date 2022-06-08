package com.pp.app.service;

import com.pp.app.repository.rest.UtilizadorRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService
{
    @Autowired
    private UtilizadorRestRepository restRepository;

    public String getAdminMessage(String encoded)
    {
        return restRepository.getAdminMessage(encoded);
    }
}
