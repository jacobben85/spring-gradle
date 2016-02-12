package spring.gradle.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.HashMap;

/**
 * Rest controller
 */
@RestController
@RequestMapping(value = "/rest", produces = {"application/json"})
public class RestApiController {

    @RequestMapping("")
    public Map<String, Object> home() {

        Map<String, Object> model = new HashMap<>();
        model.put("title", "Spring gradle");

        return model;
    }
}
