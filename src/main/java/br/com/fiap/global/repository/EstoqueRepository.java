package br.com.fiap.global.repository;

import br.com.fiap.global.model.painel.Estoque;
import br.com.fiap.global.model.painel.PainelSolar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    void deleteByPainel(PainelSolar painel);

    List<Estoque> findByPainel(PainelSolar painel);
}
