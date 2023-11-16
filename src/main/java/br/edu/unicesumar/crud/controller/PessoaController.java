package br.edu.unicesumar.crud.controller;

import br.edu.unicesumar.crud.model.domain.Pessoa;
import br.edu.unicesumar.crud.model.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;


    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll() {
        return ResponseEntity.ok(pessoaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findOne(@PathVariable Long id){
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        if(pessoa.isPresent()) {
            return ResponseEntity.ok(pessoa.get());
        };

        return ResponseEntity.notFound().build();

    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Pessoa> findUserByUsername(@PathVariable String username) {

        Optional<Pessoa> pessoa = pessoaRepository.findByUsername(username);

        if(pessoa.isPresent())return ResponseEntity.ok(pessoa.get());

        return ResponseEntity.notFound().build();

    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Pessoa> findPessoaByName(@PathVariable String nome) {
        Optional<Pessoa> pessoa = pessoaRepository.findByNome(nome);
        if(pessoa.isPresent()) return ResponseEntity.ok(pessoa.get());
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa createPessoaDto) {
        return ResponseEntity.ok(pessoaRepository.save(createPessoaDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa updatePessoaDto) {
        Pessoa pessoa = pessoaRepository.findById(id).orElse(new Pessoa());

        pessoa.setId(updatePessoaDto.getId());
        pessoa.setDocumento(updatePessoaDto.getDocumento());
        pessoa.setNome(updatePessoaDto.getNome());
        pessoa.setUsername(updatePessoaDto.getUsername());

        return ResponseEntity.ok(pessoaRepository.save(pessoa));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pessoaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}