package com.grupo2.anoLetivoWS.repository;

import com.grupo2.anoLetivoWS.dto.AnoLetivoDTO;
import com.grupo2.anoLetivoWS.exception.ErroGeralException;
import com.grupo2.anoLetivoWS.exception.ValidacaoInvalidaException;
import com.grupo2.anoLetivoWS.model.AnoLetivo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AnoLetivoRepositoryIntegrationTests
{
    @Autowired
    AnoLetivoRepository repository;

    @Test
    public void shouldCreateValidAnoLetivo()
    {
        AnoLetivo ano = new AnoLetivo("2000-2001");

        AnoLetivo saved = repository.createAndSaveAnoLetivo(ano);

        assertEquals(ano, saved);
    }

    @Test
    public void shouldNotCreateValidAnoLetivo_InvalidID()
    {
        assertThrows(ValidacaoInvalidaException.class, () -> new AnoLetivo("20ssgdsgd001"));
    }

    @Test
    public void shouldNotCreateValidAnoLetivo_Exists()
    {
        AnoLetivo ano = new AnoLetivo("2000-2001");

        repository.createAndSaveAnoLetivo(ano);

        assertThrows(ErroGeralException.class, () -> repository.createAndSaveAnoLetivo(ano));
    }

    @Test
    public void shouldFindById_Exists()
    {
        AnoLetivo ano = new AnoLetivo("2000-2001");

        repository.createAndSaveAnoLetivo(ano);

        Optional<AnoLetivo> letivo = repository.findById("2000-2001");

        assertTrue(letivo.isPresent());
    }

    @Test
    public void shouldNotFindById_DoesNotExist()
    {
        Optional<AnoLetivo> letivo = repository.findById("2000-2001");

        assertTrue(letivo.isEmpty());
    }

    @Test
    public void shouldFindAll_ExistsAnoLetivo()
    {
        AnoLetivo ano = new AnoLetivo("2000-2001");
        AnoLetivo ano1 = new AnoLetivo("2002-2003");
        AnoLetivo ano2 = new AnoLetivo("2003-2004");

        repository.createAndSaveAnoLetivo(ano);
        repository.createAndSaveAnoLetivo(ano1);
        repository.createAndSaveAnoLetivo(ano2);

        List<AnoLetivo> letivos = repository.findAll();

        assertEquals(3, letivos.size());
    }

    @Test
    public void shouldNotFindAll_Empty()
    {
        List<AnoLetivo> letivos = repository.findAll();

        assertTrue(letivos.isEmpty());
    }

    @Test
    public void shouldFindAnoLetivo_Exists()
    {
        AnoLetivo ano = new AnoLetivo("2000-2001");

        repository.createAndSaveAnoLetivo(ano);

        Optional<AnoLetivo> saved = repository.findById("2000-2001");

        assertEquals(ano, saved.get());
    }

    @Test
    public void shouldNotFindAnoLetivo_NotExists()
    {

        Optional<AnoLetivo> saved = repository.findById("2000-2001");

        assertEquals(Optional.empty(), saved);
    }

}