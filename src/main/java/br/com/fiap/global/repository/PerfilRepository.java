package br.com.fiap.global.repository;

import br.com.fiap.global.model.user.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Perfil findByName(String name);

}
