package br.edu.unicesumar.crud.controller;

import br.edu.unicesumar.crud.model.domain.Paciente;
import br.edu.unicesumar.crud.model.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public ResponseEntity<List<Paciente>> find() {
        return ResponseEntity.ok(pacienteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> findOne(@PathVariable String id){
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        return paciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Paciente> create(@RequestBody Paciente createPacienteDto) {
        return ResponseEntity.ok(pacienteRepository.save(createPacienteDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Paciente> delete(@PathVariable String id) {
        pacienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> update(@PathVariable String id, @RequestBody Paciente updatePacienteDto) {
        Paciente paciente = pacienteRepository.findById(id).orElse(new Paciente());

        if(updatePacienteDto.getCpf() != null)
            paciente.setCpf(updatePacienteDto.getCpf());

        if(updatePacienteDto.getNome() != null)
            paciente.setNome(updatePacienteDto.getNome());

        if(updatePacienteDto.getTelefone() != null)
            paciente.setTelefone(updatePacienteDto.getTelefone());

        if(updatePacienteDto.getDataNascimento() != null)
            paciente.setDataNascimento(updatePacienteDto.getDataNascimento());

        return ResponseEntity.ok(pacienteRepository.save(paciente));
    }
}
