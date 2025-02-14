package in.gov.abdm.uhi.registry.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import in.gov.abdm.uhi.common.dto.*;
import in.gov.abdm.uhi.common.dto.Error;
import in.gov.abdm.uhi.registry.util.GlobalConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

@RestControllerAdvice
public class ControllerAdvice {
    private static final Logger LOGGER = LogManager.getLogger(ControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> serverExceptionHandler(Exception ex) {

        LOGGER.error("ControllerAdvice called::{}", ex.getMessage());
        if (ex.getClass() == JsonParseException.class) {
            return handleInvalidRequestException(ex);
        } else if (ex.getClass() == IOException.class || ex.getClass() == NoSuchAlgorithmException.class ||
                ex.getClass() == InvalidKeySpecException.class || ex.getClass() == NoSuchProviderException.class) {
            return handleLookupException(ex);
        } else if(ex.getClass() == JsonProcessingException.class) {
            return handleJsonProcessorException();
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    null);
        }
    }
    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<Response> handleJsonProcessorException(Exception ex) {
        return handleJsonProcessorException();
    }

    @ExceptionHandler(GatewayException.class)
    public ResponseEntity<Response> handleInternalServerError(Exception ex) {
        LOGGER.error(GlobalConstants.INSIDE_CONTROLLER_ADVICE,ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Response.builder().message(MessageAck.builder().ack(Ack.builder().status("NACK").build()).build()).error(Error.builder().message(GatewayError.INTERNAL_SERVER_ERROR.getMessage()).code(GatewayError.INTERNAL_SERVER_ERROR.getCode()).build()).build());
    }

    @ExceptionHandler(AuthHeaderNotFoundError.class)
    public ResponseEntity<Response> handleAuthHeaderNotFoundError(Exception ex) {
        LOGGER.error(GlobalConstants.INSIDE_CONTROLLER_ADVICE,ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
               Response.builder().message(MessageAck.builder().ack(Ack.builder().status("NACK").build()).build()).error(Error.builder().message(GatewayError.AUTH_HEADER_NOT_FOUND.getMessage()).code(GatewayError.AUTH_HEADER_NOT_FOUND.getCode()).build()).build());
    }

    @ExceptionHandler(LookupException.class)
    public ResponseEntity<Response> handleLookupError(Exception ex) {
        LOGGER.error(GlobalConstants.INSIDE_CONTROLLER_ADVICE,ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Response.builder().message(MessageAck.builder().ack(Ack.builder().status("NACK").build()).build()).error(Error.builder().message(GatewayError.LOOKUP_FAILED.getMessage()).code(GatewayError.LOOKUP_FAILED.getCode()).build()).build());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> handleResourceNotFound(Exception ex) {
        LOGGER.error(GlobalConstants.INSIDE_CONTROLLER_ADVICE,ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
               Response.builder().message(MessageAck.builder().ack(Ack.builder().status("NACK").build()).build()).error(Error.builder().message(GatewayError.NO_RECORDS_FOUND.getMessage()).code(GatewayError.NO_RECORDS_FOUND.getCode()).build()).build());
    }

    @ExceptionHandler(HeaderVerificationFailedError.class)
    public ResponseEntity<Response> handleHeaderVerificationFailed(Exception ex) {
        LOGGER.error(GlobalConstants.INSIDE_CONTROLLER_ADVICE,ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Response.builder().message(MessageAck.builder().ack(Ack.builder().status("NACK").build()).build()).error(Error.builder().message(GatewayError.HEADER_VERFICATION_FAILED.getMessage()).code(GatewayError.HEADER_VERFICATION_FAILED.getCode()).build()).build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response> handleHeaderIllegalArgs(Exception ex) {
        LOGGER.error(GlobalConstants.INSIDE_CONTROLLER_ADVICE,ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Response.builder().message(MessageAck.builder().ack(Ack.builder().status("NACK").build()).build()).error(Error.builder().message(GatewayError.INVALID_SIGNATURE.getMessage()).code(GatewayError.INVALID_SIGNATURE.getCode()).build()).build());
    }

    private ResponseEntity<Response> handleInvalidRequestException(Exception ex) {

        Error err = new Error(String.valueOf(GatewayError.INVALID_REQUEST.getCode()), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Response.builder().message(MessageAck.builder().ack(Ack.builder().status("NACK").build()).build()).error(err).build());
    }

    private ResponseEntity<Response> handleLookupException(Exception ex) {
        LOGGER.error("Lookup call failed ::");
        Error err = new Error(String.valueOf(GatewayError.INTERNAL_SERVER_ERROR.getCode()), GatewayError.INTERNAL_SERVER_ERROR.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
               Response.builder().message(MessageAck.builder().ack(Ack.builder().status("NACK").build()).build()).error(err).build());
    }

    private ResponseEntity<Response> handleJsonProcessorException() {
        LOGGER.error("Invalid JSON failed ::");
        Error err = new Error(String.valueOf(GatewayError.INVALID_JSON_ERROR.getCode()), GatewayError.INVALID_JSON_ERROR.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Response.builder().message(MessageAck.builder().ack(Ack.builder().status("NACK").build()).build()).error(err).build());
    }
}
