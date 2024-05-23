package com.jardimperuibe.quadro.controller;

import com.jardimperuibe.quadro.model.Pregacao;
import com.jardimperuibe.quadro.service.PregacaoService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PregacaoController {

    @Autowired
    PregacaoService pregacaoService;

    @GetMapping("/pregacao")
    public String irPregacao(Model model) {
        List<Pregacao> pregacao = pregacaoService.mostrarTodosCarrinhos();
        model.addAttribute("pregacao", pregacao);
        return "pregacao";
    }

    @PostMapping("/irCadastrarPregacao")
    public String irCadastrarCarrinho(Model model, @RequestParam("permissao") String permissao) {
        if (permissao.equals("P00R54EGAlkiuCA0O") || permissao.equals("AD25MI87NIST347RADO4lkR") || permissao.equals("SE2MAhhtN97A")) {
            Pregacao pregacao = new Pregacao();
            model.addAttribute("pregacao", pregacao);
            return "cadastrarPregacao";
        } else {
            return "negado";
        }
    }

    @PostMapping("/cadastrarPregacao")
    public String cadastrarCarrinho(@ModelAttribute Pregacao pregacao, @RequestParam("image1") MultipartFile fotos1, @RequestParam("image2") MultipartFile fotos2, @RequestParam("image3") MultipartFile fotos3, @RequestParam("image4") MultipartFile fotos4) {
        try {
            if (!fotos1.getOriginalFilename().isEmpty()) {
                byte[] bytes1 = fotos1.getBytes();
                Path path1 = Paths.get("src/main/resources/static/imagens/" + fotos1.getOriginalFilename());
                Files.write(path1, bytes1);
                pregacao.setFoto1(fotos1.getOriginalFilename());
            } else {
                pregacao.setFoto1(null);
            }

            if (!fotos2.getOriginalFilename().isEmpty()) {
                byte[] bytes2 = fotos2.getBytes();
                Path path2 = Paths.get("src/main/resources/static/imagens/" + fotos2.getOriginalFilename());
                Files.write(path2, bytes2);
                pregacao.setFoto2(fotos2.getOriginalFilename());
            } else {
                pregacao.setFoto2(null);
            }

            if (!fotos3.getOriginalFilename().isEmpty()) {
                byte[] bytes3 = fotos3.getBytes();
                Path path3 = Paths.get("src/main/resources/static/imagens/" + fotos3.getOriginalFilename());
                Files.write(path3, bytes3);
                pregacao.setFoto3(fotos3.getOriginalFilename());
            } else {
                pregacao.setFoto3(null);
            }

            if (!fotos4.getOriginalFilename().isEmpty()) {
                byte[] bytes4 = fotos4.getBytes();
                Path path4 = Paths.get("src/main/resources/static/imagens/" + fotos4.getOriginalFilename());
                Files.write(path4, bytes4);
                pregacao.setFoto4(fotos4.getOriginalFilename());
            } else {
                pregacao.setFoto4(null);
            }

            pregacaoService.cadastrarPregacao(pregacao);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/restrito";
    }

    @PostMapping("/irAlterarPregacao")
    public String irAlterarPregacao(Model model, @RequestParam("permissao") String permissao) {
        if (permissao.equals("P00R54EGAlkiuCA0O") || permissao.equals("AD25MI87NIST347RADO4lkR") || permissao.equals("SE2MAhhtN97A")) {
            Pregacao pregacao = pregacaoService.mostrarPregacaoId(1);
            model.addAttribute("pregacao", pregacao);
            return "alterarPregacao";
        } else {
            return "negado";
        }
    }

    @DeleteMapping("/excluirPregacao")
    public ResponseEntity<String> excluirPregacao(@RequestParam String campo) {
        try {
            pregacaoService.excluirCarrinho(campo);
            return ResponseEntity.ok(campo + "Excluído com Sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao excluir: " + e.getMessage());
        }
    }

    @PutMapping("/alterarPregacaoId")
    public ResponseEntity<Pregacao> alterarPregacao(@ModelAttribute Pregacao p, @RequestParam(value = "desc", required = false) String descricao, @RequestParam(value = "imagem", required = false) MultipartFile imagem, @RequestParam(value = "imagem1", required = false) MultipartFile imagem1, @RequestParam(value = "imagem2", required = false) MultipartFile imagem2, @RequestParam(value = "imagem3", required = false) MultipartFile imagem3) {
        try {
            Pregacao antigo = pregacaoService.mostrarPregacaoId(1);
            if (imagem != null && !imagem.getOriginalFilename().isEmpty()) {
                byte[] bytes = imagem.getBytes();
                Path path = Paths.get("src/main/resources/static/imagens/" + imagem.getOriginalFilename());
                Files.write(path, bytes);
                p.setFoto1(imagem.getOriginalFilename());
            } else {
                p.setFoto1(antigo.getFoto1());
            }

            if (imagem1 != null && !imagem1.getOriginalFilename().isEmpty()) {
                byte[] bytes = imagem1.getBytes();
                Path path = Paths.get("src/main/resources/static/imagens/" + imagem1.getOriginalFilename());
                Files.write(path, bytes);
                p.setFoto2(imagem1.getOriginalFilename());
            } else {
                p.setFoto2(antigo.getFoto2());
            }

            if (imagem2 != null && !imagem2.getOriginalFilename().isEmpty()) {
                byte[] bytes = imagem2.getBytes();
                Path path = Paths.get("src/main/resources/static/imagens/" + imagem2.getOriginalFilename());
                Files.write(path, bytes);
                p.setFoto3(imagem2.getOriginalFilename());
            } else {
                p.setFoto3(antigo.getFoto3());
            }

            if (imagem3 != null && !imagem3.getOriginalFilename().isEmpty()) {
                byte[] bytes = imagem.getBytes();
                Path path = Paths.get("src/main/resources/static/imagens/" + imagem3.getOriginalFilename());
                Files.write(path, bytes);
                p.setFoto4(imagem3.getOriginalFilename());
            } else {
                p.setFoto4(antigo.getFoto4());
            }

            if (descricao != null && !descricao.isEmpty()) {
                p.setDescricao(descricao);
            } else {
                p.setDescricao(antigo.getDescricao());
            }

            pregacaoService.atualizarCarrinho(p);
            return new ResponseEntity<>(p, HttpStatus.OK);
        } catch (IOException | NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
