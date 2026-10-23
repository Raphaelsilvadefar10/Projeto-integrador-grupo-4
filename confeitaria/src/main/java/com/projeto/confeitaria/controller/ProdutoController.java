package com.projeto.confeitaria.controller;

import com.projeto.confeitaria.model.Produto;
import com.projeto.confeitaria.repository.ProdutoRepository;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Listar todos os produtos
    @GetMapping
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    // Buscar produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(
            @PathVariable Long id) {

        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cadastrar produto
    @PostMapping
    public Produto cadastrar(@Valid @RequestBody Produto produto) {
        produto.setId(null);
        return produtoRepository.save(produto);
    }

    // Atualizar produto
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Produto dados) {

        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setNome(dados.getNome());
                    produto.setDescricao(dados.getDescricao());
                    produto.setPreco(dados.getPreco());
                    produto.setCategoria(dados.getCategoria());
                    produto.setImagem(dados.getImagem());

                    return ResponseEntity.ok(
                            produtoRepository.save(produto)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Excluir produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
