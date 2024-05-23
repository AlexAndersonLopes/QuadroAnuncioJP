package com.jardimperuibe.quadro.controller;

import com.jardimperuibe.quadro.model.Semana;
import com.jardimperuibe.quadro.service.SemanaService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SemanaController {

    @Autowired
    SemanaService semanaService;

    //IR PAGINA CADASTRAR SEMANA
    @PostMapping("/irCadastrarSemana")
    public String isCadastrarSemana(Model model, @RequestParam("permissao") String permissao) {
        if (permissao.equals("SE2MAhhtN97A") || permissao.equals("AD25MI87NIST347RADO4lkR")) {
            Semana semana = new Semana();
            model.addAttribute("semana", semana);
            return "cadastrarSemana";
        } else {
            return "negado";
        }
    }

    //METODO POST SALVAR SEMANA
    @PostMapping("/cadastrarSemana")
    public String cadastrarSemana(@ModelAttribute Semana semana, @RequestParam("image") MultipartFile fotos, @RequestParam("image1") MultipartFile fotos1, @RequestParam("image2") MultipartFile fotos2) {
        try {
            if (fotos != null && !fotos.isEmpty()) {
                byte[] bytes = fotos.getBytes();
                Path path = Paths.get("src/main/resources/static/imagens/" + fotos.getOriginalFilename());
                Files.write(path, bytes);
                semana.setFoto(fotos.getOriginalFilename());
            }

            if (fotos1 != null && !fotos1.isEmpty()) {
                byte[] bytes1 = fotos1.getBytes();
                //Path path1 = Paths.get("src/main/resources/static/imagens/" + fotos1.getOriginalFilename());
                Path path1 = Paths.get("src/main/resources/static/imagens/" + fotos1.getOriginalFilename());
                Files.write(path1, bytes1);
                semana.setFotomeiosemana(fotos1.getOriginalFilename());
            }

            if (fotos2 != null && !fotos2.isEmpty()) {
                byte[] bytes2 = fotos2.getBytes();
                Path path2 = Paths.get("src/main/resources/static/imagens/" + fotos2.getOriginalFilename());
                Files.write(path2, bytes2);
                semana.setFotofimsemana(fotos2.getOriginalFilename());
            }

            semanaService.cadastrarSemana(semana);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/restrito";
    }

    //MOSTRAR TODAS ASSEMANAS
    @PostMapping("/irMostrarSemana")
    public String irMostrarSemana(Model model, @RequestParam("permissao") String permissao) {
        if (permissao.equals("SE2MAhhtN97A") || permissao.equals("AD25MI87NIST347RADO4lkR")) {
            List<Semana> listaSemanas = semanaService.mostrarTodasSemanas();
            model.addAttribute("listaSemanas", listaSemanas);
            return "mostrarSemana";
        } else {
            return "negado";
        }
    }

    //IR PAGINA ALTERAR SEMANA
    @GetMapping("/alterarSemana")
    public String irAlterarSemana(Model model, @RequestParam String id) {
        Integer idCategoria = Integer.parseInt(id);
        Semana semana = semanaService.mostrarSemanaPorId(idCategoria);

        model.addAttribute("semana", semana);
        return "alterarSemana";
    }

    //METODO PARA ALTERAR SEMANA
    @PutMapping("/alterarSemanaId/{id}")
    public ResponseEntity<Semana> alterarSemana(@PathVariable String id, @RequestParam(value = "imagem", required = false) MultipartFile imagem, @RequestParam(value = "imagem1", required = false) MultipartFile imagem1, @RequestParam(value = "imagem2", required = false) MultipartFile imagem2, @ModelAttribute Semana s) {
        try {
            Integer idcat = Integer.parseInt(id);
            Semana produtoExistente = semanaService.mostrarSemanaPorId(idcat);

            if (imagem != null && !imagem.isEmpty()) {
                byte[] bytes = imagem.getBytes();
                Path path = Paths.get("src/main/resources/static/imagens/" + imagem.getOriginalFilename());
                Files.write(path, bytes);
                s.setFoto(imagem.getOriginalFilename());
            } else {
                s.setFoto(produtoExistente.getFoto());
            }
            if (imagem1 != null && !imagem1.isEmpty()) {
                byte[] bytes1 = imagem1.getBytes();
                Path path1 = Paths.get("src/main/resources/static/imagens/" + imagem1.getOriginalFilename());
                Files.write(path1, bytes1);
                s.setFotomeiosemana(imagem1.getOriginalFilename());
            } else {
                s.setFotomeiosemana(produtoExistente.getFotomeiosemana());
            }
            if (imagem2 != null && !imagem2.isEmpty()) {
                byte[] bytes2 = imagem2.getBytes();
                Path path2 = Paths.get("src/main/resources/static/imagens/" + imagem2.getOriginalFilename());
                Files.write(path2, bytes2);
                s.setFotofimsemana(imagem2.getOriginalFilename());
            } else {
                s.setFotofimsemana(produtoExistente.getFotofimsemana());
            }
            Semana att = semanaService.atualizarProduto(idcat, s);
            return new ResponseEntity<>(att, HttpStatus.OK);
        } catch (IOException | NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/excluirSemana")
    public String irExcluirSemana(Model model, @RequestParam String id) {
        Integer idCategoria = Integer.parseInt(id);
        Semana semana = semanaService.mostrarSemanaPorId(idCategoria);

        model.addAttribute("semana", semana);
        return "excluirSemana";
    }

    @DeleteMapping("/excluirSemanaId")
    public ResponseEntity<String> excluirSemana(@RequestParam String id) {
        try {
            Integer idcat = Integer.parseInt(id);
            Semana semana = semanaService.mostrarSemanaPorId(idcat);
            semanaService.excluirSemana(semana.getId());

            return ResponseEntity.ok("Semana Excluída com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao excluir uma semana: " + e.getMessage());
        }
    }

    @GetMapping("/semana")
    public String irPaginaSemana(Model model, @RequestParam String id) {
        Integer idSemana = Integer.parseInt(id);
        Semana semana = new Semana();
        semana = semanaService.mostrarSemanaPorId(idSemana);

        model.addAttribute("semana", semana);
        return "semana";
    }
}
