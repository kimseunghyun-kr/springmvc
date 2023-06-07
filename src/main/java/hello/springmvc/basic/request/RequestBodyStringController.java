package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

/*
for cases where data is directly written into the HTTP message body
-> usually has type json , xml , text
-> usually done through POST PUT PATCH HTTP methods

-> unlike request parameters, @RequestParam && @ModelAttribute cannot be used
   to interpret data that comes through the HTTP body (with the exception of a HTML FORM)
 */

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException{

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); //copy to String from byteString with UTF_8 Encoding

        log.info("messageBody = {}", messageBody);

        response.getWriter().write("ok");

    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException{

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);

        responseWriter.write("ok");
    }

//    public class HttpEntity<T> {
//
//	/**
//	 * The empty {@code HttpEntity}, with no body or headers.
//	 */
//	public static final HttpEntity<?> EMPTY = new HttpEntity<>();
//
//
//	private final HttpHeaders headers;
//
//	@Nullable
//	private final T body;

//    - HttpMessageConverter -> StringHttpMessageConverter (different call route from @ModelAttribute and @RequestParam route)
//    convenient abstraction to directly receive Http Body
    @PostMapping ("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity){

        String messageBody = httpEntity.getBody();

        log.info("messageBody = {}", messageBody);

        return new HttpEntity<>("ok");
//        has child classes responseEntity and requestEntity
    }

    @ResponseBody
    @PostMapping ("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody){

        log.info("messageBody = {}", messageBody);

        return "ok";
    }
}
