package ozbank.ozbankapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ozbank.ozbankapp.dto.ResponseDto;

@ControllerAdvice
public class OzBankGlobalExceptionHandler {

	@ExceptionHandler(OzBankException.class)
	public ResponseEntity<ResponseDto> handleCustomException(OzBankException ex) {
		return new ResponseEntity<ResponseDto>(new ResponseDto(ex.getMessage()), HttpStatus.NOT_FOUND);

	}

}
