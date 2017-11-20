package br.com.integration;

import br.com.util.DefaultTestAnnotations;
import br.com.util.IntegrationTestUtil;
import br.com.json.bean.Login;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@DefaultTestAnnotations
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTest extends IntegrationTestUtil{
	
	
    @Test
    public void loginSuccess() throws IOException, Exception  {
    	
    	Login loginCorreto = new Login("user1", "123");
    	
    	MvcResult result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(loginCorreto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString("token")))
                .andReturn();
    	
    	String content = result.getResponse().getContentAsString();
    	
    	assertThat(content, containsString("token"));
    	
    }
    
    @Test
    public void loginError() throws IOException, Exception  {
    	
    	Login loginErrado = new Login("usuario nao existe", "1234");

    	mockMvc.perform(post("/api/auth/login")
    			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(loginErrado)))
                .andExpect(status().isForbidden());
    }
    	
}
