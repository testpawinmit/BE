package com.mit.pawin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/commonApi")
@CrossOrigin
public class CommonController {

    private static final Logger log = LoggerFactory.getLogger(CommonController.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /*@RequestMapping(value = "/restart", method = RequestMethod.POST)
    public void restart(@RequestBody CommonResponseDto commonRequestDto) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ response={}, message={}}", commonRequestDto.getResponse(), commonRequestDto.getMessage());

        Process p1;
        Process p2;
        try {
            CommonScheduler pKiller = new CommonScheduler();
            pKiller.killProcess("taskkill /PID " + commonRequestDto.getMessage() + " /F");

            List<String> cmdList1 = new ArrayList<String>();
            List<String> cmdList2 = new ArrayList<String>();
            cmdList1.add("cmd");
            cmdList1.add("/c");
            //cmdList.add("dir");
            cmdList1.add("javac Process.java");
            ProcessBuilder pb1 = new ProcessBuilder();
            pb1.command(cmdList1);
            p1 = pb1.start();

            p1.waitFor();

            cmdList2.add("cmd");
            cmdList2.add("/c");
            //cmdList.add("dir");
            cmdList2.add("java Process");
            ProcessBuilder pb2 = new ProcessBuilder();
            pb2.command(cmdList2);
            p2 = pb2.start();

            p2.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    p1.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

        }

    }*/
}
