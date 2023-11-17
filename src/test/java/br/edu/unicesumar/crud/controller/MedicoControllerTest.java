package br.edu.unicesumar.crud.controller;

import br.edu.unicesumar.crud.model.domain.Medico;
import br.edu.unicesumar.crud.model.repository.MedicoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Collections;
import java.util.Optional;

@WebMvcTest(MedicoController.class)
public class MedicoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicoRepository medicoRepository;

    @Test
    public void testFindAllMedicos() throws Exception {
        Medico medico = new Medico();
        Mockito.when(medicoRepository.findAll()).thenReturn(Collections.singletonList(medico));

        mockMvc.perform(get("/medico")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testFindOneMedico() throws Exception {
        String medicoId = "1";
        Medico medico = new Medico();
        Mockito.when(medicoRepository.findById(medicoId)).thenReturn(Optional.of(medico));

        mockMvc.perform(get("/medico/" + medicoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testInsertMedico() throws Exception {
        Medico medico = new Medico();

        mockMvc.perform(post("/medico")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(medico)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateMedico() throws Exception {
        String medicoId = "1";
        Medico medico = new Medico();

        Mockito.when(medicoRepository.findById(medicoId)).thenReturn(Optional.of(medico));

        mockMvc.perform(put("/medico/" + medicoId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(medico)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMedico() throws Exception {
        String medicoId = "1";

        mockMvc.perform(delete("/medico/" + medicoId)
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
