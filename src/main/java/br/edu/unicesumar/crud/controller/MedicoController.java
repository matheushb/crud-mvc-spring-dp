package br.edu.unicesumar.crud.controller;

import br.edu.unicesumar.crud.model.domain.Medico;
import br.edu.unicesumar.crud.model.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping
    public ResponseEntity<List<Medico>> find() {
        return ResponseEntity.ok(medicoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> findOne(@PathVariable String id){
        Optional<Medico> medico = medicoRepository.findById(id);
        return medico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Medico> create(@RequestBody Medico createMedicoDto) {
        return ResponseEntity.ok(medicoRepository.save(createMedicoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Medico> delete(@PathVariable String id) {
        medicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> update(@PathVariable String id, @RequestBody Medico updateMedicoDto) {
        Medico medico = medicoRepository.findById(id).orElse(new Medico());

        if(updateMedicoDto.getCpf() != null)
            medico.setCpf(updateMedicoDto.getCpf());

        if(updateMedicoDto.getCrm() != null)
            medico.setCrm(updateMedicoDto.getCrm());

        if(updateMedicoDto.getNome() != null)
            medico.setNome(updateMedicoDto.getNome());

        if(updateMedicoDto.getTelefone() != null)
            medico.setTelefone(updateMedicoDto.getTelefone());

        if(updateMedicoDto.getEspecialidade() != null)
            medico.setEspecialidade(updateMedicoDto.getEspecialidade());

        if(updateMedicoDto.getDataNascimento() != null)
            medico.setDataNascimento(updateMedicoDto.getDataNascimento());

        return ResponseEntity.ok(medicoRepository.save(medico));
    }



}
