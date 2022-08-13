package com.govtech.viswa.exception;

import com.govtech.viswa.entity.GlobalLog;
import com.govtech.viswa.service.GlobalLogService;
import com.govtech.viswa.service.MySession;
import com.govtech.viswa.util.Common;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Log4j2
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @Autowired
    GlobalLogService globalLogService;

    @Autowired
    MySession mySession;



    @ExceptionHandler(GlobalException.class)
    public final ResponseEntity<Object> handleAllExceptions(GlobalException ex) {
        Map < String, Object > body = new LinkedHashMap <>();
        body.put( "warning", ex.warning );
        body.put( "error", ex.message );

        if ("".equals( ex.message)) {
            String stacktrace = ExceptionUtils.getStackTrace( ex );
            body.put( "stacktrace", stacktrace );
        }
        else {
            body.put( "stacktrace", "" );
        }
        if ("".equals( ex.path)) {
            body.put( "path", ex.getStackTrace()[ 0 ].getFileName().split( "[.]" )[ 0 ] + " - " + ex.getStackTrace()[ 0 ].getMethodName() + " - " + ex.getStackTrace()[ 0 ].getLineNumber() );
        }
        else {
            body.put( "path", ex.path);
        }
        body.put( "timestamp", LocalDateTime.now() );
        body.put( "input", Common.isNullOrDefault(ex.input,  mySession.getInput() ) );


        GlobalLog  globalLog = new GlobalLog(
                body.get( "stacktrace" ).toString(),
                body.get( "error" ).toString(),
                body.get( "warning" ).toString(),
                body.get( "path" ).toString(),
                body.get( "input" ).toString()
        );

        globalLogService.save( globalLog );

        return new ResponseEntity <>( body, HttpStatus.EXPECTATION_FAILED );
    }


    @ExceptionHandler ( value = { Exception.class } )
    protected final ResponseEntity < Object > handleException ( RuntimeException ex ) {
        Map < String, Object > body = new LinkedHashMap <>();
        body.put( "type", "Exception " );
        body.put( "timestamp", LocalDateTime.now() );
        RuntimeException myex = ( ( RuntimeException ) ex );
        body.put( "error", myex.getMessage() );
        String stacktrace = ExceptionUtils.getStackTrace( myex );
        body.put( "stacktrace", stacktrace );
        body.put( "input", mySession.getInput() );
        body.put( "path", ex.getStackTrace()[ 0 ].getFileName().split( "[.]" )[ 0 ] + " - " + ex.getStackTrace()[ 0 ].getMethodName() + " - " + ex.getStackTrace()[ 0 ].getLineNumber() );

        GlobalLog  globalLog = new GlobalLog(
                body.get( "stacktrace" ).toString(),
                body.get( "error" ).toString(),
                null,
                body.get( "path" ).toString(),
                body.get( "input" ).toString()
        );

        globalLogService.save( globalLog );

        return new ResponseEntity <>( body, HttpStatus.NOT_ACCEPTABLE );
    }
}
