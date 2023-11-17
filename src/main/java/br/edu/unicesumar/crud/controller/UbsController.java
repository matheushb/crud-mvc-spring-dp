package br.edu.unicesumar.crud.controller;

import br.edu.unicesumar.crud.model.domain.Ubs;
import br.edu.unicesumar.crud.model.repository.UbsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ubs")
public class UbsController {
    @Autowired
    private UbsRepository ubsRepository;

    @GetMapping
    public ResponseEntity<List<Ubs>> find() {
        return ResponseEntity.ok(ubsRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubs> findOne(@PathVariable String id){
        Optional<Ubs> ubs = ubsRepository.findById(id);
        return ubs.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ubs> create(@RequestBody Ubs createUbsDto) {
        return ResponseEntity.ok(ubsRepository.save(createUbsDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ubs> delete(@PathVariable String id) {
        ubsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ubs> update(@PathVariable String id, @RequestBody Ubs updateUbsDto) {
        Ubs ubs = ubsRepository.findById(id).orElse(new Ubs());

        if(updateUbsDto.getEndereco() != null) {
            ubs.setEndereco(updateUbsDto.getEndereco());
        }

        return ResponseEntity.ok(ubsRepository.save(ubs));
    }
}
