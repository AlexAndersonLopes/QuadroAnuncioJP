package com.jardimperuibe.quadro.service;

import com.jardimperuibe.quadro.model.Aviso;
import com.jardimperuibe.quadro.repository.AvisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvisoService {

    @Autowired
    AvisoRepository avisoRepository;

    public Aviso cadastrarAviso(Aviso a) {
        if (existe()) {
            avisoRepository.deleteById(1);
            a.setId(1);
            avisoRepository.save(a);
            return a;
        } else {
            a.setId(1);
            avisoRepository.save(a);
            return a;
        }
    }

    public boolean existe() {
        Aviso a = mostrarAvisoId(1);
        return a != null;
    }

    public Aviso mostrarAvisoId(Integer id) {
        try {
            return avisoRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            return null;
        }
    }

    public void excluirAviso() {
        try {
            avisoRepository.deleteById(1);
        } catch (Exception e) {
        }
    }

}
