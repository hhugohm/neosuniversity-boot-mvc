package com.neos.university.neosuniversitybootmvc;

import com.neos.university.neosuniversitybootmvc.controllers.MessageController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MessageController.class)
    public class MessageControllerMock {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGreetingsWithOutname() throws Exception {
        mvc.perform(get("/greetings")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(view().name("message"))
                .andExpect(model().attribute("user", is("Desconocido")));
    }

    @Test
    public void testGreetingsWithName() throws Exception {
        mvc.perform(get("/greetings")
                .param("name", "HUGO").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(view().name("message"))
                .andExpect(model().attribute("user", is("HUGO")));
    }
}

