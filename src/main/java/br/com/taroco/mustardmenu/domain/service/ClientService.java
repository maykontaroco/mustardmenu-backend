package br.com.taroco.mustardmenu.domain.service;

import br.com.taroco.mustardmenu.domain.model.client.Client;
import br.com.taroco.mustardmenu.infrastructure.persistence.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository repository;

    public List<Client> getAll() {
        return repository.findAll();
    }

    public Client save(Client client) {
        return repository.save(client);
    }
}
