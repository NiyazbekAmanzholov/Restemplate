package Package.controller;

import Package.pojo.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/getobject")
@RestController
public class GetForObjectController {
    private final RestTemplate restTemplate;

    @Autowired
    public GetForObjectController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @RequestMapping("/foo")
    public Foo foo() {
        //Можно получить даже у другого микросервиса
        return restTemplate
                .getForObject("http://localhost:8080/resttemplate/baz", Foo.class);
    }

    @RequestMapping("/baz")//Tо что мы получаем
    public String baz() {
        return "Привет Чувак)";
    }
}
