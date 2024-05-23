package com.jardimperuibe.quadro.controller;

import com.jardimperuibe.quadro.model.Usuario;
import com.jardimperuibe.quadro.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioService usuarioServive;

    @PostMapping("/cadastrarUsuario")
    public String irPaginaCadastrarUsuario(@ModelAttribute Usuario u) {
        List<Usuario> usuarios = usuarioServive.mostrarUsuarioPorNome(u.getNome());
        if (usuarios.isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setNome(u.getNome());
            usuario.setSenha(u.getSenha());
            usuario.setPermissao(u.getPermissao());
            usuario.setGrupo(u.getGrupo());
            usuarioServive.cadastrarUsuario(usuario);
            return "redirect:/restrito";
        }
        return null;
    }

    @PostMapping("/irCadastrarUsuario")
    public String irPaginaCadastrarUsuario(Model model, @RequestParam("permissao") String permissao) {
        if (permissao.equals("AD25MI87NIST347RADO4lkR")) {
            Usuario usuario = new Usuario();
            List<Usuario> listaUsuarios = usuarioServive.mostrarUsuarios();

            model.addAttribute("listaUsuarios", listaUsuarios);
            model.addAttribute("usuario", usuario);
            return "cadastrarUsuario";
        } else {
            return "negado";
        }
    }

    @PostMapping("/login")
    public String verificarLogin(Model model, @RequestParam("nome") String nome, @RequestParam("senha") String senha) {
        List<Usuario> usuarios = usuarioServive.mostrarUsuarioPorNome(nome);
        if (!usuarios.isEmpty()) {
            Usuario usuario = usuarios.get(0); 
            if (usuario.getSenha().equals(senha)) {
                String permissao = usuario.getPermissao();
                String grupo = usuario.getGrupo();
                model.addAttribute("grupo", grupo);
                model.addAttribute("permissao", permissao);
                if (permissao.equals("SU123PE321RINaaTENxDExTEx")) {
                    return "restritoGrupos";
                } else {
                    return "restrito";
                }
            } else {
                model.addAttribute("erro", "Senha ou usuário incorreto.");
                return "entrar";
            }
        } else {
            model.addAttribute("erro", "Senha ou usuário incorreto.");
            return "entrar";
        }
    }

    @DeleteMapping("/excluirUsuario")
    public ResponseEntity<String> excluirUsuarioId(@RequestParam String id) {
        try {
            int excluir = Integer.parseInt(id);
            usuarioServive.excluirUsuario(excluir);
            return ResponseEntity.ok("Usuario excluido com sucesso!");
        } catch (NumberFormatException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao excluir o Usuario!");
        }
    }

}
