package com.neos.university.neosuniversitybootmvc;

import com.neos.university.neosuniversitybootmvc.controllers.MessageController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NeosuniversityBootMvcApplicationTests {

    @Test
    public void processMessage() {
        MessageController controller = new MessageController();
        Model model = new BindingAwareModelMap();
        String result=controller.processMessage("HUGO",model);
        assertEquals("message",result);
        assertEquals("HUGO",model.asMap().get("user"));
    }

}
