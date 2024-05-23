package com.jardimperuibe.quadro.controller;

import com.jardimperuibe.quadro.model.Aviso;
import com.jardimperuibe.quadro.service.AvisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AvisoController {

    @Autowired
    AvisoService avisoService;

    @PostMapping("/irCadastrarAviso")
    public String irCadastrarAviso(Model model, @RequestParam("permissao") String permissao) {
        if (permissao.equals("P00R54EGAlkiuCA0O") || permissao.equals("AD25MI87NIST347RADO4lkR") || permissao.equals("SE2MAhhtN97A") || permissao.equals("R7EaswL55ATschOR901IOs")) {
            Aviso aviso = avisoService.mostrarAvisoId(1);
            model.addAttribute("aviso", aviso);
            return "cadastrarAviso";
        } else {
            return "negado";
        }
    }

    @DeleteMapping("/excluirAviso")
    public ResponseEntity<String> excluirAviso() {
        try {
            avisoService.excluirAviso();
            return ResponseEntity.ok("Aviso excluído com Sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao excluir o aviso: " + e.getMessage());
        }
    }

    @PostMapping("/cadastrarAvisos")
    public String cadastrarAviso(@ModelAttribute Aviso a, @RequestParam("descProduto") String desc, @RequestParam("descProduto2") String desc2) {
        try {
            a.setTema(desc);
            a.setMensagem(desc2);
            avisoService.cadastrarAviso(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/restrito";
    }

}
