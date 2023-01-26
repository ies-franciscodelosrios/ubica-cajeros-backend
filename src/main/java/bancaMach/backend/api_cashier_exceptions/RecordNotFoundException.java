package bancaMach.backend.api_cashier_exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException{
    private String exceptionDescription;
    private Object value;

    public RecordNotFoundException(String exceptionDescription, Object value) {
        super(exceptionDescription);
        this.exceptionDescription = exceptionDescription;
        this.value = value;
    }

    public String getExceptionDescription() {
        return exceptionDescription;
    }

    public void setExceptionDescription(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
