package com.govtech.viswa.exception;

import com.govtech.viswa.util.Common;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.exception
 * @class GlobalException
 */


@Data
@EqualsAndHashCode ( callSuper = false )
public class GlobalException extends RuntimeException implements Serializable {

    final Object error;
    String message = "";
    String warning = "";
    String path = "";
    String input = "";

    public GlobalException(Object error, Throwable cause ) {
        super( error.toString(), cause );
        this.error = Common.isNull(error);
    }

    public GlobalException(Object error ) {
        super( error.toString(), null );
        this.error = Common.isNull(error);
    }

    public GlobalException(String message ) {
        super( message, null );
        this.error = message;
        this.message = message;
    }

    public GlobalException(String message, Object error ) {
        super( error.toString(), null );
        this.error = Common.isNull(error);
        this.message = Common.isNull(message);;
    }

    public GlobalException(String message, String warning, Object error ) {
        super( error.toString(), null );
        this.error = Common.isNull(error);;
        this.message = Common.isNull(message);;
        this.warning = Common.isNull(warning);;
    }

    public GlobalException(String message, String warning, String path, Object error ) {
        super( error.toString(), null );
        this.error = Common.isNull(error);;
        this.message = Common.isNull(message);;
        this.warning = Common.isNull(warning);;
        this.path = Common.isNull(path);;
    }
    
    public GlobalException( String message, String warning, String path, Object input, Object error ) {
        super( error.toString(), null );
        this.error = Common.isNull(error);;
        this.message = Common.isNull(message);;
        this.warning = Common.isNull(warning);;
        this.path = Common.isNull(path);;
        this.input = input == null ? "" :  input.toString();
    }


    
}
