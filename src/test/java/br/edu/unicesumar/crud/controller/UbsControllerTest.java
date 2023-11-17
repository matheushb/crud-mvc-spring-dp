package br.edu.unicesumar.crud.controller;

import br.edu.unicesumar.crud.model.domain.Ubs;
import br.edu.unicesumar.crud.model.repository.UbsRepository;
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

@WebMvcTest(UbsController.class)
public class UbsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UbsRepository ubsRepository;

    @Test
    public void testFindAllUbs() throws Exception {
        Ubs ubs = new Ubs();
        Mockito.when(ubsRepository.findAll()).thenReturn(Collections.singletonList(ubs));

        mockMvc.perform(get("/ubs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testFindOneUbs() throws Exception {
        String ubsId = "1";
        Ubs ubs = new Ubs();
        Mockito.when(ubsRepository.findById(ubsId)).thenReturn(Optional.of(ubs));

        mockMvc.perform(get("/ubs/" + ubsId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testInsertUbs() throws Exception {
        Ubs ubs = new Ubs(); // Defina os dados da Ubs para inserção

        mockMvc.perform(post("/ubs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ubs)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateUbs() throws Exception {
        String ubsId = "1";
        Ubs ubs = new Ubs(); // Defina os dados da Ubs para atualização

        Mockito.when(ubsRepository.findById(ubsId)).thenReturn(Optional.of(ubs));

        mockMvc.perform(put("/ubs/" + ubsId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(ubs)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUbs() throws Exception {
        String ubsId = "1";

        mockMvc.perform(delete("/ubs/" + ubsId)
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
