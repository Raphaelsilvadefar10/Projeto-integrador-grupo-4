package com.projeto.confeitaria;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoApiTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void rejeitaProdutoComPrecoNegativo() throws Exception {
        mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nome":"Bolo","preco":-1.00,"categoria":"Bolos"}
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cadastraProdutoPreservandoDuasCasasDecimais() throws Exception {
        mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nome":"Bolo de teste","preco":12.35,"categoria":"Bolos"}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.preco").value(12.35));
    }
}
