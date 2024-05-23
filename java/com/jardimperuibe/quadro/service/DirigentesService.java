package com.jardimperuibe.quadro.service;

import com.jardimperuibe.quadro.model.Dirigentes;
import com.jardimperuibe.quadro.repository.DirigentesRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirigentesService {

    @Autowired
    DirigentesRepository dirigentesRepository;

    //CADASTRAR
    public Dirigentes cadastrarDirigentes(Dirigentes d) {
        if (mostrarDirigentesIdBoolean()) {
            dirigentesRepository.deleteById(1);

            d.setId(1);
            dirigentesRepository.save(d);
            return d;
        } else {
            d.setId(1);
            dirigentesRepository.save(d);
            return d;
        }
    }

    public Dirigentes mostrarDirigentesId(Integer id) {
        try {
            return dirigentesRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Carrinho não encontrado para o ID: " + id));
        } catch (Exception e) {
            return null;
        }
    }

    public boolean mostrarDirigentesIdBoolean() {
        Dirigentes d = mostrarDirigentesId(1);
        return d != null;
    }

    public List<Dirigentes> mostrarTodosDirigentes() {
        return dirigentesRepository.findAll();
    }

    //ATUALIZAR
    public Dirigentes atualizarDirigentes(Dirigentes d) {
        Dirigentes diri = mostrarDirigentesId(1);
        diri.setFoto1(d.getFoto1());
        diri.setFoto2(d.getFoto2());
        diri.setFoto3(d.getFoto3());
        diri.setFoto4(d.getFoto4());
        diri.setDescricao(d.getDescricao());
        dirigentesRepository.save(diri);
        return diri;
    }

    //EXCLUIR CARRINHO
    public void excluirDirigentes(String x) {
        Dirigentes d = mostrarDirigentesId(1);
        switch (x) {
            case "foto1" -> {
                d.setFoto1(null);
                dirigentesRepository.save(d);
            }
            case "foto2" -> {
                d.setFoto2(null);
                dirigentesRepository.save(d);
            }
            case "foto3" -> {
                d.setFoto3(null);
                dirigentesRepository.save(d);
            }
            case "foto4" -> {
                d.setFoto4(null);
                dirigentesRepository.save(d);
            }
            case "descricao" -> {
                d.setDescricao(null);
                dirigentesRepository.save(d);
            }
            default -> {
            }
        }
    }

}
