package br.com.fiap.global.service;


import br.com.fiap.global.model.user.UserAccount;
import br.com.fiap.global.model.user.Perfil;
import br.com.fiap.global.repository.UserRepository;
import br.com.fiap.global.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Injeção do PasswordEncoder

    public void saveUser(String username, String password, String email, List<Perfil> perfilList) {
        Set<Perfil> perfis = new HashSet<>();
        for (Perfil perfilName : perfilList) {
            Perfil perfil = perfilRepository.findByName(perfilName.getName());
            if (perfil != null) {
                perfis.add(perfil);
            }
        }

        // Criptografa a senha antes de salvar
        String encodedPassword = passwordEncoder.encode(password);
        UserAccount user = new UserAccount(username, encodedPassword, email, perfis);

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Pesquisa o usuário pelo username
        UserAccount userAccount = userRepository.findByUsername(username);
        //Valida se o usuário existe
        if (userAccount == null)
            throw new UsernameNotFoundException("Usuário não encontrado");

        //Transformar as Roles do usuário em SimpleGrantedAuthority
        Set<SimpleGrantedAuthority> authorities = userAccount.getPerfis().stream()
                .map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toSet());

        //retornar um User
        return new User(username, userAccount.getPassword(), authorities);
    }
}