package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController //directly returns val on HTTP message
public class LogTestController {
//    @Slf4j in lombok writes the following auto
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

//      sout unable to set diff priorities, prints in both dev and actl production versions: not favoured
        System.out.println("name" + name);

        //        diff levels of logging, least severe to most severe
        log.trace("  trace log = {} , {}", name, name);
        log.debug("  debug log = {}", name);
        log.info("  info log = {}", name);
        log.warn("  warn log =  {}", name);
        log.error("  error log = {}", name);

//      do not do this. even if the log is not printed, the string concat will still occur
//      slows down process - eager evaluation is bad in this case
//        log.info("  info log " + name);
//        use the lazy eval version with {} above

        return "ok";
    }


}
