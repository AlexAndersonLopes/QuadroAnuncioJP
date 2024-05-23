package com.jardimperuibe.quadro.service;

import com.jardimperuibe.quadro.model.Pregacao;
import com.jardimperuibe.quadro.repository.PregacaoRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PregacaoService {

    @Autowired
    PregacaoRepository pregacaoRepository;

    //CADASTRAR
    public Pregacao cadastrarPregacao(Pregacao p) {
        if (mostrarPregacaoIdBoolean()) {
            pregacaoRepository.deleteById(1);

            p.setId(1);
            pregacaoRepository.save(p);
            return p;
        } else {
            p.setId(1);
            pregacaoRepository.save(p);
            return p;
        }
    }

    public Pregacao mostrarPregacaoId(Integer id) {
        try {
            return pregacaoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Carrinho não encontrado para o ID: " + id));
        } catch (Exception e) {
            return null;
        }
    }

    public boolean mostrarPregacaoIdBoolean() {
        Pregacao p = mostrarPregacaoId(1);
        return p != null;
    }

    public List<Pregacao> mostrarTodosCarrinhos() {
        return pregacaoRepository.findAll();
    }

    //ATUALIZAR
    public Pregacao atualizarCarrinho(Pregacao p) {
        Pregacao preg = mostrarPregacaoId(1);
        preg.setFoto1(p.getFoto1());
        preg.setFoto2(p.getFoto2());
        preg.setFoto3(p.getFoto3());
        preg.setFoto4(p.getFoto4());
        preg.setDescricao(p.getDescricao());
        pregacaoRepository.save(preg);
        return preg;
    }

    //EXCLUIR CARRINHO
    public void excluirCarrinho(String x) {
        Pregacao p = mostrarPregacaoId(1);
        switch (x) {
            case "foto1" -> {
                p.setFoto1(null);
                pregacaoRepository.save(p);
            }
            case "foto2" -> {
                p.setFoto2(null);
                pregacaoRepository.save(p);
            }
            case "foto3" -> {
                p.setFoto3(null);
                pregacaoRepository.save(p);
            }
            case "foto4" -> {
                p.setFoto4(null);
                pregacaoRepository.save(p);
            }
            case "descricao" -> {
                p.setDescricao(null);
                pregacaoRepository.save(p);
            }
            default -> {
            }
        }
    }

}
