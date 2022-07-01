package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.dto.filter.ProjetoFilterBody;
import com.grupo2.projeto.dto.filter.ProjetoFilterElement;
import com.grupo2.projeto.dto.mapper.ProjetoDTOMapper;
import com.grupo2.projeto.exception.ErroGeralException;
import com.grupo2.projeto.model.filter.*;
import com.grupo2.projeto.repository.ProjetoJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoFilterService
{
    @Autowired
    private ProjetoJDBCRepository projetoJDBCRepository;

    @Autowired
    private ProjetoDTOMapper mapper;

    private String lastQuery;

    public List<ProjetoDTO> filtrarProjetos(ProjetoFilterBody body)
    {
        return listFiltered(body);
    }

    private List<ProjetoDTO> listFiltered(ProjetoFilterBody body)
    {
        String query = toQuery(body);
        Object[] params = body.getParams();

        System.out.println(query);

        int paramCount = query.split("\\?").length-1;

        if(paramCount > params.length)
        {
            throw new ErroGeralException("Filtros requerem mais parametros: "+paramCount+" - recebido: "+params.length);
        }

        return projetoJDBCRepository.findAllFilter(query, params).stream().map(mapper::toDTO).toList();
    }

    private String toQuery(ProjetoFilterBody body)
    {
        ProjetoQueryBuilder builder = new ProjetoQueryBuilder();

        Filter f = readBody(body);

        builder.addFilter(f);

        return builder.build();
    }

    private Filter readBody(ProjetoFilterBody body)
    {
        return readElement(body.getBase());
    }

    private Filter readElement(ProjetoFilterElement e)
    {
        Filter f = null;

        switch (e.getName().toUpperCase())
        {
            case "AND" -> {
                f = new AndFilter(readElement(e.getElements()[0]), readElement(e.getElements()[1]));
                return f;
            }
            case "OR" -> {
                f = new OrFilter(readElement(e.getElements()[0]), readElement(e.getElements()[1]));
                return f;
            }
            case "NOT" -> {
                f = new NotFilter(readElement(e.getElements()[0]));
                return f;
            }
        }

        QueryPresets qp = QueryPresets.valueOf(e.getName());

        f = new StringFilter(qp.getValue());

        return f;
    }

    public String getLastQuery()
    {
        return lastQuery;
    }
}
