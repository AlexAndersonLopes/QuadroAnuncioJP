package com.jardimperuibe.quadro.service;

import com.jardimperuibe.quadro.model.Carrinho;
import com.jardimperuibe.quadro.repository.CarrinhoRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoService {

    @Autowired
    CarrinhoRepository carrinhoRepository;

    //CADASTRAR
    public Carrinho cadastrarCarrinho(Carrinho c) {
        if (mostrarCarrinhoIdBoolean()) {
            carrinhoRepository.deleteById(1);
            
            c.setId(1);
            carrinhoRepository.save(c);
            return c;
        } else {
            c.setId(1);
            carrinhoRepository.save(c);
            return c;
        }
    }

    public Carrinho mostrarCarrinhoId(Integer id) {
        try {
            return carrinhoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Carrinho não encontrado para o ID: " + id));
        } catch (Exception e) {
            return null;
        }
    }

    public boolean mostrarCarrinhoIdBoolean() {
        Carrinho c = mostrarCarrinhoId(1);
        return c != null;
    }

    public List<Carrinho> mostrarTodosCarrinhos() {
        return carrinhoRepository.findAll();
    }

    //ATUALIZAR
    public Carrinho atualizarCarrinho(Carrinho c) {
        Carrinho carrinho = mostrarCarrinhoId(1);
        carrinho.setFoto1(c.getFoto1());
        carrinho.setFoto2(c.getFoto2());
        carrinho.setFoto3(c.getFoto3());
        carrinho.setFoto4(c.getFoto4());
        carrinho.setDescricao(c.getDescricao());
        carrinhoRepository.save(carrinho);
        return carrinho;
    }

    //EXCLUIR CARRINHO
    public void excluirCarrinho(String x) {
        Carrinho c = mostrarCarrinhoId(1);
        switch (x) {
            case "foto1" -> {
                c.setFoto1(null);
                carrinhoRepository.save(c);
            }
            case "foto2" -> {
                c.setFoto2(null);
                carrinhoRepository.save(c);
            }
            case "foto3" -> {
                c.setFoto3(null);
                carrinhoRepository.save(c);
            }
            case "foto4" -> {
                c.setFoto4(null);
                carrinhoRepository.save(c);
            }
            case "descricao" -> {
                c.setDescricao(null);
                carrinhoRepository.save(c);
            }
            default -> {
            }
        }
    }

}
