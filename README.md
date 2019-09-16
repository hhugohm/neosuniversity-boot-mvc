# Spring Boot 2.x

----------------------------------
Pasos para instalación de SPRING BOOT MVC
----------------------------------
1.- Crear un proyecto mediante Initialzr
https://start.spring.io/

2.- Java Project con la siguiente informacion
* Maven Project
* Group: com.neos.university
* Artifact: neosuniversity-boot-mvc
* Dependenies: Web, Thymeleaf

3.- Generar el proyecto

4.- Importar el proyecto mediante IntelliJ-->Opcion Import maven Projects Automatically

5.- Estudiar las siguientes interfaces
* @SpringBootConfiguration
* @EnableAutoConfiguration
* @ComponentScan

6.- Crear un paquete  controllers

7.- Crear un controlador MessageController
```ruby
@Controller
public class MessageController {

    @GetMapping("/greetings")
    public String processMessage(
            @RequestParam(defaultValue = "Desconocido", required = false) String name, Model model){

        model.addAttribute("user",name);

        return "message";
    }
}
```

8.- Crear un archivo message.html en templates
```ruby
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Hola Mundo Spring Boot 2.x</title>
</head>
<body>
<h1 th:text="${user} + ' bienvenido a Spring Boot 2.x'" ></h1>

</body>
</html>
```
9.- Crear un archivo index.html en static
```ruby
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Index</title>
</head>
<body>
<form action="/greetings" method="get">
    <b>Nombre</b>
    <input  type="text" name="name" />
    <input type="submit"  value="Enviar" />
</form>

</body>
</html>
``

10.- Correr el projecto y ejecutar en browser
http://localhost:8080/

11.- Prueba unitarias. Agregar el siguiente codigo en la clase NeosuniversityBootMvcApplication
```ruby
 @Test
    public void processMessage() {
        MessageController controller = new MessageController();
        Model model = new BindingAwareModelMap();
        String result=controller.processMessage("HUGO",model);
        assertEquals("message",result);
        assertEquals("HUGo",model.asMap().get("user"));
    }
 ```
   
12.- Agregar la clase MessageControllerMock
```ruby
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
```

13.- Ejecutar las pruebas unitarias
