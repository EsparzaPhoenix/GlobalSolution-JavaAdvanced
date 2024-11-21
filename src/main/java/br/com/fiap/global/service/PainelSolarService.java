package br.com.fiap.global.service;

import br.com.fiap.global.model.painel.PainelSolar;
import br.com.fiap.global.repository.EstoqueRepository;
import br.com.fiap.global.repository.PainelSolarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PainelSolarService {

    private final PainelSolarRepository painelSolarRepository;
    private final EstoqueRepository estoqueRepository;

    public PainelSolarService(PainelSolarRepository painelSolarRepository, EstoqueRepository estoqueRepository) {
        this.painelSolarRepository = painelSolarRepository;
        this.estoqueRepository = estoqueRepository;
    }

    @Transactional
    public void excluirPainel(Long painelId) {
        // Verificar se o painel existe
        PainelSolar painel = painelSolarRepository.findById(painelId)
                .orElseThrow(() -> new IllegalArgumentException("Painel não encontrado"));

        // Verificar se há registros associados ao painel no estoque
        if (!estoqueRepository.findByPainel(painel).isEmpty()) {
            throw new IllegalStateException("Não é possível excluir o painel enquanto houver registros no estoque.");
        }

        // Excluir o painel
        painelSolarRepository.delete(painel);
    }
}
