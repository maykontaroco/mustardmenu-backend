package br.com.taroco.mustardmenu.domain.service;

import br.com.taroco.mustardmenu.domain.model.user.User;
import br.com.taroco.mustardmenu.infrastructure.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserRepository repository;

    public boolean validarLogin(String username, String password) {
        User user = repository.findByUsernameAndPassword(username, password);
        return user != null;
    }
}
