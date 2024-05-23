package com.jardimperuibe.quadro.service;

import com.jardimperuibe.quadro.model.Usuario;
import com.jardimperuibe.quadro.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario cadastrarUsuario(Usuario usuario) {
        usuario.setId(null);
        usuarioRepository.save(usuario);
        return usuario;
    }

    public List<Usuario> mostrarUsuarios() {
        return usuarioRepository.findAll();
    }
    
    public List<Usuario> obterUsuariosComGrupo() {
        return usuarioRepository.findByGrupoNotNull();
    }
    
    public List<Usuario> mostrarUsuarioPorNome(String nome) {
        List<Usuario> usuarios = usuarioRepository.findByNome(nome);
        return usuarios;
    }

    public Usuario mostrarUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id).orElseThrow();
    }

    public void excluirUsuario(Integer id) {
        Usuario u = mostrarUsuarioPorId(id);
        usuarioRepository.deleteById(u.getId());
    }
    
    public boolean haUsuariosCadastrados() {
        List<Usuario> usuarios = mostrarUsuarios();
        return !usuarios.isEmpty();
    }

}
