package hello.springmvc.basic.request;


import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Controller
@Slf4j
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {} , age = {}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody //same as @RestController -> directly inserts return val into body of HTTP response
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName, //same as request.getparameter
            @RequestParam("age") int memberAge){

        log.info("username = {}, age = {}", memberName, memberAge);
        return "ok";
    }


    @ResponseBody //same as @RestController -> directly inserts return val into body of HTTP response
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, //same as request.getparameter
            @RequestParam int age){

        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody //same as @RestController -> directly inserts return val into body of HTTP response
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
            String username, //spring will auto search if parameters are
            // primitive types like String int Integer. (quite ambiguous tbh)
            int age){
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, //default is true
            @RequestParam(required = false) Integer age) {

//        null != ""; null gives 400 BAD request, "" gives "ok"
        log.info("username={}, age={}", username, age);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username, //default is true
            @RequestParam(required = false, defaultValue = "-1") Integer age) {

        //defaultValue makes required attribute redundant, as all value has a value set as default
//        empty value "" will also trigger defaultValue, default value will be used.
        log.info("username={}, age={}", username, age);
        return "ok";
    }


    /*
    parameters can also be received as a Map or MultivalueMap
    @RequestParam Map ,
    Map(key=value)
    @RequestParam MultiValueMap
    MultiValueMap(key=[value1, value2, ...] ex) (key=userIds, value=[id1, id2])
    MultiValueMap should be used if one is unsure if every key has one value associated to it
    but usually there is only one value associated to a parameter being requested
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"),
                paramMap.get("age"));
        return "ok";
    }

//    @ResponseBody
//    @RequestMapping("/model-attribute-v1")
//    public String modelAttributeV1(@RequestParam String username,
//                                   @RequestParam int age) {
//
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);
//        log.info("username={}, age={}", helloData.getUsername(),
//                helloData.getAge());
//        log.info("helloData = {}" , helloData);
//        return "ok";
//    }


    //above is simplified to below

    /*
    SpringMVC runs the following upon @ModelAttribute
    1. it initialises a HelloData Object
    2. based on the name of the request parameter, it finds the corresponding
       property within the HelloData Object
    3. It then calls the setter() of the object and binds the value of the parameter
       to the given property
    4. should such a binding not exist, it will create a BindException
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(),
                helloData.getAge());
        return "ok";
    }

    /*
    both @RequestParam and @ModelAttribute can be not explicitly stated
    Spring runs @RequestParam for primitives (classes) and classes that'
    are explicitly registered on ArgumentResolver
    and runs @ModelAttribute for other Object Data types
     */

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(),
                helloData.getAge());
        return "ok";
    }






}
