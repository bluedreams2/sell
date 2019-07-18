package com.imooc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;



/*@RunWith(SpringRunner.class)
@SpringBootTest*/
@Slf4j
public class LoggerTest {


    @Test
    public void tess1(){
        log.debug("debug..");
        log.info("info..");
        log.info("error..");
    }

 }
