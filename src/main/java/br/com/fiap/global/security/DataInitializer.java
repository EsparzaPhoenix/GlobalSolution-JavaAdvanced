package br.com.fiap.global.security;

import br.com.fiap.global.model.painel.PainelSolar;
import br.com.fiap.global.model.painel.enums.TipoPainel;
import br.com.fiap.global.model.user.Perfil;
import br.com.fiap.global.model.user.UserAccount;
import br.com.fiap.global.repository.PainelSolarRepository;
import br.com.fiap.global.repository.PerfilRepository;
import br.com.fiap.global.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   PerfilRepository perfilRepository,
                                   PainelSolarRepository painelSolarRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            // Inicializa Roles (Perfis)
            Perfil adminRole = new Perfil();
            adminRole.setName("ROLE_ADMIN");
            adminRole.setLabel("Administrador");
            perfilRepository.save(adminRole);

            Perfil userRole = new Perfil();
            userRole.setName("ROLE_USER");
            userRole.setLabel("Usuário");
            perfilRepository.save(userRole);

            // Inicializa Usuários
            UserAccount admin = new UserAccount();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEmail("admin@example.com");
            admin.setPerfis(Set.of(adminRole));
            userRepository.save(admin);

            UserAccount user = new UserAccount();
            user.setUsername("user1");
            user.setPassword(passwordEncoder.encode("password"));
            user.setEmail("user1@example.com");
            user.setPerfis(Set.of(userRole));
            userRepository.save(user);

            // Inicializa Painéis Solares
            PainelSolar painelMono = new PainelSolar();
            painelMono.setMarca("SolarMax");
            painelMono.setModelo("SM-1000");
            painelMono.setVoltagem("24V");
            painelMono.setTipo(TipoPainel.MONOCRISTALINO);
            painelMono.setValor(1500.0);
            painelMono.setDimensoes(1.6); // Dimensões em metros quadrados
            painelSolarRepository.save(painelMono);

            PainelSolar painelPoli = new PainelSolar();
            painelPoli.setMarca("EcoPower");
            painelPoli.setModelo("EP-500");
            painelPoli.setVoltagem("12V");
            painelPoli.setTipo(TipoPainel.POLICRISTALINO);
            painelPoli.setValor(900.0);
            painelPoli.setDimensoes(1.2); // Dimensões em metros quadrados
            painelSolarRepository.save(painelPoli);

            PainelSolar painelAmorfo = new PainelSolar();
            painelAmorfo.setMarca("AmorphTech");
            painelAmorfo.setModelo("AT-300");
            painelAmorfo.setVoltagem("48V");
            painelAmorfo.setTipo(TipoPainel.AMORFO);
            painelAmorfo.setValor(2000.0);
            painelAmorfo.setDimensoes(1.8); // Dimensões em metros quadrados
            painelSolarRepository.save(painelAmorfo);

        };
    }
}
