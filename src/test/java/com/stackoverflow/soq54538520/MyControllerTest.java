package com.stackoverflow.soq54538520;

import com.stackoverflow.soq54538520.MyControllerTest.TestConfig;
import com.stackoverflow.soq54538520.service.ServiceB;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ContextConfiguration(classes = {TestConfig.class, Application.class})
public class MyControllerTest {

    @Profile("test")
    @Configuration
    static class TestConfig {

        @Bean
        @Primary
        public ServiceB mockB() {
            ServiceB mockService = Mockito.mock(ServiceB.class);
            Mockito.when(mockService.greeting()).thenReturn("I am mock Service B!");
            return mockService;
        }
    }
    @Autowired
    private MockMvc mvc;

    @Test
    public void testGreeting() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(content().string(equalTo("A response: I am real ServiceA!, B response: I am real ServiceB!")));
    }
    @Test
    public void testGreetingMock() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("A response: I am real ServiceA!, B response: I am Mock Service B!")));
    }
}
