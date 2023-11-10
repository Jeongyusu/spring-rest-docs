package com.example.restdoc1_app.user;

import com.example.restdoc1_app.MyWithRestDoc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@SpringBootTest //통합 테스트 (메모리에 다 뜬다)
public class UserControllerTest extends MyWithRestDoc {


    @Test
    public void join_test() throws Exception {
        // given
        UserRequest.JoinDTO requestDTO = new UserRequest.JoinDTO();
        requestDTO.setUsername("love");
        requestDTO.setPassword("12345678");
        requestDTO.setEmail("love@nate.com");
        ObjectMapper om = new ObjectMapper();
        String requestBody=  om.writeValueAsString(requestDTO);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/join")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        // then

        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.username").value("love"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.password").value("12345678"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.email").value("love@nate.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document);
    }

    @Test
    public void user_info_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/users/"+id)
        );
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.username").value("ssar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.password").value("1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.email").value("ssar@nate.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty());
    }
}









