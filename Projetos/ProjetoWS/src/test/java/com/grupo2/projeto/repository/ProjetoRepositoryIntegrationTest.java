package com.grupo2.projeto.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class ProjetoRepositoryIntegrationTest
{
    @Autowired
    ProjetoRepository repository;

}