package com.jardimperuibe.quadro.controller;

import com.jardimperuibe.quadro.model.Aviso;
import com.jardimperuibe.quadro.model.Semana;
import com.jardimperuibe.quadro.model.Usuario;
import com.jardimperuibe.quadro.service.AvisoService;
import com.jardimperuibe.quadro.service.SemanaService;
import com.jardimperuibe.quadro.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Telas {

    @Autowired
    SemanaService semanaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    AvisoService avisoService;

    //IR PARA PAGINA INICIAL
    @GetMapping("/inicio")
    public String paginaInicial(Model model) {
        Aviso aviso = avisoService.mostrarAvisoId(1);
        List<Semana> todasSemanas = semanaService.mostrarTodasSemanas();
        model.addAttribute("aviso", aviso);
        model.addAttribute("todasSemanas", todasSemanas);
        return "index";
    }

    //IR PARA PAGINA INICIAL
    @GetMapping("/")
    public String paginaInicial3(Model model) {
        if (!usuarioService.haUsuariosCadastrados()) {
            Usuario u = new Usuario();
            u.setNome("Administrador");
            u.setSenha("jardimperuibe");
            u.setPermissao("AD25MI87NIST347RADO4lkR");
            usuarioService.cadastrarUsuario(u);
        }
        Aviso aviso = avisoService.mostrarAvisoId(1);
        List<Semana> todasSemanas = semanaService.mostrarTodasSemanas();
        model.addAttribute("todasSemanas", todasSemanas);
        model.addAttribute("aviso", aviso);
        return "quadro";
    }

    @PostMapping("/loginQuadro")
    public String validarInicio(Model model, @RequestParam("senha") String senha) {
        if (senha.equals("001914")) {
            Aviso aviso = avisoService.mostrarAvisoId(1);
            List<Semana> todasSemanas = semanaService.mostrarTodasSemanas();
            model.addAttribute("todasSemanas", todasSemanas);
            model.addAttribute("aviso", aviso);
            return "index";
        } else {
            model.addAttribute("erro", "Senha incorreta.");
            return "quadro";
        }
    }

    @GetMapping("/negado")
    public String acessoNegado() {
        return "negado";
    }

    //IR PARA PAGINA RESTRITO
    @GetMapping("/restrito")
    public String paginaRestrito(Model model) {
        return "restrito";
    }

    //IR PARA PAGINA ENTRAR
    @PostMapping("/entrar")
    public String paginaEntrar(Model model, @RequestParam(value = "permissao", required = false) String permissao) {
        return switch (permissao) {
            case "AD25MI87NIST347RADO4lkR", "SE2MAhhtN97A", "P00R54EGAlkiuCA0O", "R7EaswL55ATschOR901IOs" ->
                "restrito";
            case "SU123PE321RINaaTENxDExTEx" ->
                "restritoGrupos";
            default ->
                "entrar";
        };
    }
    /*
    //SALVAR A IMAGEM CORRETAMENTE
    @GetMapping("/imagens/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = new FileSystemResource("src/main/resources/static/imagens/" + filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
     */
}
