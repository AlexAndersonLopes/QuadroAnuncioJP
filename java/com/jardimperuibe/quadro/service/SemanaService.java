package com.jardimperuibe.quadro.service;

import com.jardimperuibe.quadro.model.Semana;
import com.jardimperuibe.quadro.repository.SemanaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SemanaService {

    @Autowired
    SemanaRepository semanaRepository;

    //Cadastrar uma semana
    public Semana cadastrarSemana(Semana s) {
        s.setId(null);
        semanaRepository.save(s);
        return s;
    }

    //Mostrar Todas semanas
    public List<Semana> mostrarTodasSemanas() {
        return semanaRepository.findAll();
    }

    //Mostrar Semana Pesquisando pelo ID
    public Semana mostrarSemanaPorId(Integer id) {
        return semanaRepository.findById(id).orElseThrow();
    }

    //Deletar semana
    public void excluirSemana(Integer id) {
        Semana s = mostrarSemanaPorId(id);
        semanaRepository.deleteById(s.getId());
    }

    //Atualizar dados do Produto
    public Semana atualizarProduto(Integer id, Semana s) {
        Semana semana = mostrarSemanaPorId(id);

        semana.setFoto(s.getFoto());
        semana.setDia(s.getDia());
        semana.setFotomeiosemana(s.getFotomeiosemana());
        semana.setFotofimsemana(s.getFotofimsemana());
        semana.setIndicador(s.getIndicador());
        semana.setPalco(s.getPalco());
        semana.setVolante1(s.getVolante1());
        semana.setVolante2(s.getVolante2());
        semana.setPortao(s.getPortao());
        semana.setMidias(s.getMidias());
        semana.setAudio(s.getAudio());
        semana.setLimpeza(s.getLimpeza());
        semana.setLink1(s.getLink1());
        semana.setLink2(s.getLink2());

        semanaRepository.save(semana);
        return semana;
    }

}
