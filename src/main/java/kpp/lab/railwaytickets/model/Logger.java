package kpp.lab.railwaytickets.model;

import org.springframework.stereotype.Service;

@Service
public class Logger implements BaseLogger{

    public String logFileName;

    //public Logger(String logFileName) {
    //    this.logFileName = logFileName;
    //}

    @Override
    public void log(String message) {

    }
}
