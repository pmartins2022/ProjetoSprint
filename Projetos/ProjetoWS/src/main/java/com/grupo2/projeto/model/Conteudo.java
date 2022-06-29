package com.grupo2.projeto.model;

import com.detectlanguage.errors.APIError;
import com.grupo2.projeto.dto.MomentoAvaliacaoDTO;
import com.grupo2.projeto.exception.AtualizacaoInvalidaException;
import com.grupo2.projeto.exception.ValidacaoInvalidaException;
import com.grupo2.projeto.model.annotations.ForeignKey;
import com.grupo2.projeto.model.annotations.IgnoreField;
import com.grupo2.projeto.model.annotations.PrimaryKey;
import com.grupo2.projeto.model.annotations.Table;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Table(tableName = "CONTEUDO")
public class Conteudo extends JDBCTable
{
    @IgnoreField
    @PrimaryKey(generated = true)
    private Long id;
    @ForeignKey(className = Projeto.class, fieldName = "ID")
    private Long idProjeto;
    private String titulo;
    private String caminhoDocumento;
    private String documento;
    private String linguagemDocumento;

    private EstadoConteudo estadoConteudo;

    public Conteudo()
    {
    }

    private void detetarLingua()
    {
        try
        {
            List<String> strings = Arrays.stream(documento.split("\n")).toList();

            if (strings.size() == 0)
            {
                throw new ValidacaoInvalidaException("O FICHEIRO TEM QUE TER TEXTO");
            }

            Optional<String> s = strings.stream().filter(linha -> linha.split(" ").length > 5).findFirst();

            String linha = "";

            if (s.isEmpty())
            {
                Random r = new Random();
                linha = strings.get(r.nextInt(0, strings.size()));
            } else
            {
                linha = s.get();
            }

            this.linguagemDocumento = DetetorLinguagem.detetar(linha).toUpperCase(Locale.ROOT);
        } catch (APIError e)
        {
            throw new ValidacaoInvalidaException("DEU ERRO NA API - " + e.getMessage());
        }
    }

    public Conteudo(Long id, Long idProjeto, String titulo, String caminhoDocumento, String documento, String linguagemDocumento, String estadoConteudo)
    {
        this.id = id;
        this.idProjeto = idProjeto;
        this.titulo = titulo;

        if (documento == null)
        {
            throw new ValidacaoInvalidaException("O DOCUMENTO NAO PODE SER NULO");
        }
        else
        {
            this.documento = documento;
        }

        if (linguagemDocumento == null)
        {
            detetarLingua();
        } else
        {
            this.documento = documento;
            this.linguagemDocumento = linguagemDocumento;
        }

        if (caminhoDocumento == null)
        {
            this.caminhoDocumento = String.valueOf(documento.hashCode() + System.currentTimeMillis());
        }
        else
        {
            this.caminhoDocumento = caminhoDocumento;
        }


        if (estadoConteudo == null)
        {
            this.estadoConteudo = EstadoConteudo.PENDENTE;
        } else
        {
            this.estadoConteudo = EstadoConteudo.valueOf(estadoConteudo);
        }
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getDocumento()
    {
        return documento;
    }

    public void setDocumento(String documento)
    {
        this.documento = documento;
    }

    public String getLinguagemDocumento()
    {
        return linguagemDocumento;
    }

    public String getCaminhoDocumento()
    {
        return caminhoDocumento;
    }

    public void setLinguagemDocumento(String linguagemDocumento)
    {
        this.linguagemDocumento = linguagemDocumento;
    }

    public EstadoConteudo getEstadoConteudo()
    {
        return estadoConteudo;
    }

    public void setEstadoConteudo(EstadoConteudo estadoConteudo)
    {
        this.estadoConteudo = estadoConteudo;
    }

    public Long getIdProjeto()
    {
        return idProjeto;
    }

    public void setIdProjeto(Long idProjeto)
    {
        this.idProjeto = idProjeto;
    }

    public void rejeitarConteudo() throws AtualizacaoInvalidaException
    {
        if (estadoConteudo != EstadoConteudo.APROVADO)
        {
            throw new AtualizacaoInvalidaException("Nao e possivel mudar o estado da conteudo. Ja se encontra " + this.estadoConteudo.name());
        }
        this.estadoConteudo = EstadoConteudo.REJEITADO;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conteudo conteudo = (Conteudo) o;
        return idProjeto.equals(conteudo.idProjeto) && titulo.equals(conteudo.titulo) && caminhoDocumento.equals(conteudo.caminhoDocumento) && documento.equals(conteudo.documento) && linguagemDocumento.equals(conteudo.linguagemDocumento) && estadoConteudo == conteudo.estadoConteudo;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, idProjeto, titulo, caminhoDocumento, documento, linguagemDocumento, estadoConteudo);
    }

    @Override
    public String toString()
    {
        return "Conteudo{" +
                "id=" + id +
                ", idProjeto=" + idProjeto +
                ", titulo='" + titulo + '\'' +
                ", caminhoDocumento='" + caminhoDocumento + '\'' +
                ", documento='" + documento + '\'' +
                ", linguagemDocumento='" + linguagemDocumento + '\'' +
                ", estadoConteudo=" + estadoConteudo +
                '}';
    }
}
