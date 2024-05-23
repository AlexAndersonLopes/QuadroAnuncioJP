package com.jardimperuibe.quadro.service;

import com.jardimperuibe.quadro.model.Relatorio;
import com.jardimperuibe.quadro.repository.RelatorioRepository;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatorioService {

    @Autowired
    RelatorioRepository relatorioRepository;

    //Cadastrar um Relatorio
    public Relatorio cadastrarRelatorio(Relatorio r) {
        r.setId(null);
        relatorioRepository.save(r);
        return r;
    }

    //Mostrar Todos Relatorios
    public List<Relatorio> mostrarTodosRelatorios() {
        return relatorioRepository.findAll();
    }

    //Mostrar Relatorio Pesquisando pelo ID
    public Relatorio mostrarRelatorioPorId(Integer id) {
        return relatorioRepository.findById(id).orElseThrow();
    }

    //Deletar Relatorio
    public void excluirRelatorio(Integer id) {
        Relatorio r = mostrarRelatorioPorId(id);
        relatorioRepository.deleteById(r.getId());
    }

    public void excluirRelatoriosPorMes(String mes) {
        List<Relatorio> relatoriosDoMes = relatorioRepository.findByMesContaining(mes);
        for (Relatorio relatorio : relatoriosDoMes) {
            relatorioRepository.deleteById(relatorio.getId());
        }
    }

    //procurar pelo nome
    public List<Relatorio> encontrarRelatorioPorNome(String nome) {
        return relatorioRepository.findByNomeContaining(nome);
    }

    //procurar pelo mes
    public List<Relatorio> encontrarRelatorioPorMes(String nome) {
        return relatorioRepository.findByMesContaining(nome);
    }
    
     //procurar pelo grupo
    public List<Relatorio> encontrarRelatorioPorGrupo(String grupo) {
        return relatorioRepository.findByGrupoContaining(grupo);
    }
    
    //procurar pelo grupo
    public List<Relatorio> encontrarRelatorioPorGrupoMes(String grupo, String mes) {
        return relatorioRepository.findByGrupoContainingAndMes(grupo, mes);
    }
    
     public List<String> encontrarMesesUnicosOrdenadosPorData() {
        return relatorioRepository.encontrarMesesUnicosOrdenadosPorData();
    }

}
