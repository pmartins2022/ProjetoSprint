package com.grupo2.projeto.repository;

import com.grupo2.projeto.model.Projeto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProjetoRepositoryUnitTest
{
    @Autowired
    ProjetoRepository repository;

}