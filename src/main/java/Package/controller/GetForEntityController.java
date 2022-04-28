package Package.controller;

import Package.pojo.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/resttemplate")
@RestController
public class GetForEntityController {
    private final RestTemplate restTemplate;

    @Autowired
    public GetForEntityController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @RequestMapping("/foo")
    public ResponseEntity<Foo> foo() {
        //Можно получить даже у другого микросервиса
        return restTemplate
                .getForEntity("http://localhost:8080/resttemplate/baz", Foo.class);
    }

    @RequestMapping("/baz")//Tо что мы получаем
    public Foo baz() {
        Foo foo = new Foo();
        foo.setId(1);
        foo.setName("Niyaz");
        return foo;
    }
}