package com.projeto.confeitaria.controller;

import com.projeto.confeitaria.model.Cliente;
import com.projeto.confeitaria.repository.ClienteRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Listar todos os clientes
    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    // Buscar cliente pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cadastrar cliente
    @PostMapping
    public Cliente cadastrar(@RequestBody Cliente cliente) {
        cliente.setId(null);
        return clienteRepository.save(cliente);
    }

    // Atualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(
            @PathVariable Long id,
            @RequestBody Cliente dados) {

        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNome(dados.getNome());
                    cliente.setEmail(dados.getEmail());
                    cliente.setTelefone(dados.getTelefone());
                    cliente.setEndereco(dados.getEndereco());

                    return ResponseEntity.ok(
                            clienteRepository.save(cliente)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Excluir cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
