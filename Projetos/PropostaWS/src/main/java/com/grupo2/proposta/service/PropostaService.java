package com.grupo2.proposta.service;

import com.grupo2.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropostaService
{
    @Autowired
    private PropostaRepository repository;
}
