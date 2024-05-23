package com.jardimperuibe.quadro.repository;

import com.jardimperuibe.quadro.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
    List<Usuario> findByNome(String nome);
    
    @Query("SELECT u FROM Usuario u WHERE u.grupo IS NOT NULL")
    List<Usuario> findByGrupoNotNull();
}
