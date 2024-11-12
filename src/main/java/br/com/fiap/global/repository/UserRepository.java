package br.com.fiap.global.repository;

import br.com.fiap.global.model.user.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findByUsername(String username);

}