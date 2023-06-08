package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

//@ResponseBody
//@Controller
@RestController //this is the addition of both ResponseBody{class level} and Controller annotation interfaces. ctrl click to find out
@Slf4j
public class ResponseBodyController {

//    this directly gets the HTTPSERVLETRESPONSE and writes. @ResponseBody has no effect
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

//    this is a responseEntity, @ResponseBody has no action on this as well
//    mostly works for primitives and non primitive return type data
    @GetMapping("/response-body-string-v2")
    public HttpEntity<String> responseBodyV2(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

//    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(){
        return "ok";
    }


    //to change ResponseCode dynamically, use this
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("ajax");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
//        even if 404 {HttpStatus.NOT_FOUND, helloData still follows haha
    }


//    due to responseStatus being an annotation interface, unable to change on the fly
    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("ajax");
        helloData.setAge(20);
        return helloData;
    }


}
