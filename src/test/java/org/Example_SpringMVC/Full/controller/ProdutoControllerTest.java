package org.Example_SpringMVC.Full.controller;

import org.Example_SpringMVC.Full.conf.AppWebConfiguration;
import org.Example_SpringMVC.Full.conf.DataSourceConfigurationTest;
import org.Example_SpringMVC.Full.conf.JPAConfiguration;
import org.Example_SpringMVC.Full.conf.SecurityConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import javax.servlet.Filter;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //faz o carregamento das demais configurações de MVC do Spring
@ContextConfiguration(classes = {JPAConfiguration.class, AppWebConfiguration.class, DataSourceConfigurationTest.class, SecurityConfiguration.class})
@ActiveProfiles("test")
public class ProdutoControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private Filter springSecurityFilterChain;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain).build();
    }

    @Test
    public void deveRetornarParaHomeComOsLivros() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers
                        .model()
                        .attributeExists("produtos"))
                .andExpect(MockMvcResultMatchers
                        .forwardedUrl("/WEB-INF/views/home.jsp"));
    }

    @Test
    public void somenteAdminDeveAcessarProdutosForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/produtos/form")
                    .with(SecurityMockMvcRequestPostProcessors
                            .user("joao").password("123").roles("USER")))
                .andExpect(MockMvcResultMatchers.status().is(403));
    }
}
