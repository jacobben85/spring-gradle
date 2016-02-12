package spring.gradle.controller;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Rest API end point test
 */
public class RestApiControllerTest {

    @Test
    public void testHome() throws Exception {
        Map<String, String> expected = new HashMap<>();
        expected.put("title", "Spring gradle");

        RestApiController restApiController = new RestApiController();
        Map<String, Object> response =  restApiController.home();
        Assert.assertEquals(expected, response);
    }
}