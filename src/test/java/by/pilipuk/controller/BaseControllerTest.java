package by.pilipuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;

import java.util.Map;
import static by.pilipuk.util.Json.serialize;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    protected void performGetRequest(String url, Object expectedDto) {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get(url))
                    .andExpectAll(
                            status().isOk(),
                            content().contentType(MediaType.APPLICATION_JSON),
                            content().json(serialize(expectedDto))
                    );
        } catch (Exception e) {
            fail("Error executing GET request: " + e.getMessage());
        }
    }

    protected void performGetRequest(String url, Object urlVariables, Object expectedDto) {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get(url, urlVariables))
                    .andExpectAll(
                            status().isOk(),
                            content().contentType(MediaType.APPLICATION_JSON),
                            content().json(serialize(expectedDto))
                    );
        } catch (Exception e) {
            fail("Error executing GET request with path variable: " + e.getMessage());
        }
    }

    protected void performGetRequestWithParams(String url, MultiValueMap<String, String> params, Object expectedDto) {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get(url)
                            .params(params))
                    .andExpectAll(
                            status().isOk(),
                            content().contentType(MediaType.APPLICATION_JSON),
                            content().json(serialize(expectedDto))
                    );
        } catch (Exception e) {
            fail("Error executing GET request with params: " + e.getMessage());
        }
    }

    protected void performPostRequest(String url, Object postDto) {
        try {
            mockMvc.perform(MockMvcRequestBuilders.post(url)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(serialize(postDto)))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            fail("Error executing POST request to " + url + ": " + e.getMessage());
        }
    }

    protected void performGetHistogramRequest(String url, Map<String, Integer> expectedMap) {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get(url))
                    .andExpectAll(
                            status().isOk(),
                            content().contentType(MediaType.APPLICATION_JSON),
                            content().json(serialize(expectedMap))
                    );
        } catch (Exception e) {
            fail("Error executing GET histogram request: " + e.getMessage());
        }
    }
}