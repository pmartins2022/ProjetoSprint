package com.grupo2.projeto.repository;

import com.grupo2.projeto.dataModel.jpa.ConteudoJPA;
import com.grupo2.projeto.dataModel.jpa.mapper.ConteudoJPAMapper;
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.model.EstadoConteudo;
import com.grupo2.projeto.repository.jdbc.GenericJDBCRepository;
import com.grupo2.projeto.repository.jpa.ConteudoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConteudoRepository
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ConteudoJPAMapper mapper;

    public int saveConteudo(Conteudo conteudo) throws IllegalAccessException
    {
        return GenericJDBCRepository.from(jdbcTemplate,conteudo).save();
    }

    public Optional<Conteudo> findById(Long id)
    {
        Conteudo optional = jdbcTemplate.queryForObject("SELECT * FROM CONTEUDO WHERE ID = ?", Conteudo.class,id);

        return Optional.ofNullable(optional);
    }

    public int atualizarConteudo(Long id, EstadoConteudo estado)
    {
        return jdbcTemplate.update("UPDATE CONTEUDO SET estadoConteudo = '?' WHERE ID = ?",estado.name(),id);
    }

    public List<Conteudo> findAllByIdProjeto(Long id)
    {
        List<Conteudo> list = jdbcTemplate.queryForList("SELECT * FROM PROJETO WHERE ID = ?",Conteudo.class, id);

        return list;
    }
}
