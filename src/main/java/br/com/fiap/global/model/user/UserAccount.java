package br.com.fiap.global.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="GS_JV_USER")
@Getter @Setter @NoArgsConstructor
@SequenceGenerator(name="seq_user", sequenceName="seq_JV_user", allocationSize=1, initialValue = 1)
public class UserAccount implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "cd_user")
    private Long id;

    @Column(name = "ds_username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "ds_password", nullable = false)
    private String password;

    @Column(name = "ds_email", nullable = false, unique = true)
    private String email;

    // Relação com a tabela Perfil
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "GS_JV_USUARIO_PERFIL",
            joinColumns = @JoinColumn(name = "cd_user"),
            inverseJoinColumns = @JoinColumn(name = "cd_role")
    )
    private Set<Perfil> perfis;

    public UserAccount(String username, String password, String email, Set<Perfil> perfis) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.perfis = perfis;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfis.stream()
                .map(perfil -> new SimpleGrantedAuthority("ROLE_" + perfil.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Você pode customizar essa lógica conforme necessário
    }

    @Override
    public boolean isEnabled() {
        return true;  // Você pode customizar essa lógica conforme necessário
    }
}