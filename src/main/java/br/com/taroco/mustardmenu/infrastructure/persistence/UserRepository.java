package br.com.taroco.mustardmenu.infrastructure.persistence;

import br.com.taroco.mustardmenu.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);
}
