package br.edu.unicesumar.crud.controller;

import br.edu.unicesumar.crud.model.domain.Paciente;
import br.edu.unicesumar.crud.model.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PacienteController.class)
public class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PacienteRepository pacienteRepository;

    @Test
    public void testFindAllPacientes() throws Exception {
        Paciente paciente = new Paciente();
        Mockito.when(pacienteRepository.findAll()).thenReturn(Collections.singletonList(paciente));

        mockMvc.perform(get("/paciente")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testFindOnePaciente() throws Exception {
        String pacienteId = "1";
        Paciente paciente = new Paciente();
        Mockito.when(pacienteRepository.findById(pacienteId)).thenReturn(Optional.of(paciente));

        mockMvc.perform(get("/paciente/" + pacienteId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testInsertPaciente() throws Exception {
        Paciente paciente = new Paciente();

        mockMvc.perform(post("/paciente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(paciente)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatePaciente() throws Exception {
        String pacienteId = "1";
        Paciente paciente = new Paciente();

        Mockito.when(pacienteRepository.findById(pacienteId)).thenReturn(Optional.of(paciente));

        mockMvc.perform(put("/paciente/" + pacienteId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(paciente)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePaciente() throws Exception {
        String pacienteId = "1";

        mockMvc.perform(delete("/paciente/" + pacienteId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}