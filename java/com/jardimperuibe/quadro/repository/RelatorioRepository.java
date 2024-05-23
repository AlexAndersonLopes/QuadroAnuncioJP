package com.jardimperuibe.quadro.repository;

import com.jardimperuibe.quadro.model.Relatorio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RelatorioRepository extends JpaRepository<Relatorio, Integer> {

    List<Relatorio> findByNomeContaining(String nome);

    List<Relatorio> findByMesContaining(String mes);

    List<Relatorio> findByGrupoContaining(String grupo);

    List<Relatorio> findByGrupoContainingAndMes(String grupo, String mes);

    @Query("SELECT DISTINCT r.mes FROM Relatorio r ORDER BY r.mes DESC")
    List<String> encontrarMesesUnicosOrdenadosPorData();

}
