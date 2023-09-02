package br.com.taroco.mustardmenu.domain.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidPaymentException extends RuntimeException {

    public InvalidPaymentException() {
        super("Pagamento inválido");
    }

    @ExceptionHandler(InvalidPaymentException.class)
    public ResponseEntity<Object> handleUserNotFoundException(InvalidPaymentException exception) {
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

}
