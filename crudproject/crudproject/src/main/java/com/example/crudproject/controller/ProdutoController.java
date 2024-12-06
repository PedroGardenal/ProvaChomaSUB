package com.example.crudproject.controller;

import com.example.crudproject.model.Produto;
import com.example.crudproject.service.ProdutoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> getAllProducts() {
        return produtoService.findAll();
    }

    @PostMapping
    public Produto createProduct(@RequestBody Produto produto) {
        // Aqui já deve funcionar, se os setters estiverem presentes
        return produtoService.save(produto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        produtoService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Produto getProductById(@PathVariable Long id) {
        return produtoService.findById(id);
    }

    @PutMapping("/{id}")
    public Produto updateProduct(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        // Busca o produto existente pelo ID
        Produto produtoExistente = produtoService.findById(id);

        if (produtoExistente != null) {
            // Atualiza os campos do produto existente
            produtoExistente.setNome(produtoAtualizado.getNome());
            produtoExistente.setPreco(produtoAtualizado.getPreco());
            produtoExistente.setStatus(produtoAtualizado.getStatus());

            // Salva as alterações
            return produtoService.save(produtoExistente);
        } else {
            throw new RuntimeException("Produto com ID " + id + " não encontrado.");
        }
    }

}
