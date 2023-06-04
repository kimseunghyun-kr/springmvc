package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //directly returns val on HTTP message
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

//      sout unable to set diff priorities, prints in both dev and actl production versions: not favoured
        System.out.println("name" + name);

        //        diff levels of logging, least severe to most severe
        log.trace("  info log = {} , {}", name, name);
        log.debug("  info log = {}", name);
        log.warn("  info log =  {}", name);
        log.info("  info log = {}", name);
        log.error("  info log = {}", name);


        return "ok";
    }


}
