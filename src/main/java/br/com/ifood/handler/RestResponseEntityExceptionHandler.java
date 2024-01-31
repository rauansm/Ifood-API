package br.com.ifood.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Log4j2
public class RestResponseEntityExceptionHandler {
	@ExceptionHandler(APIException.class)
	public ResponseEntity<ErrorApiResponse> handlerGenericException(APIException ex) {
		return ex.buildErrorResponseEntity();
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorApiResponse> handlerGenericException(Exception ex) {
		log.error("Exception: ", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ErrorApiResponse.builder().description("INTERNAL SERVER ERROR!")
						.message("POR FAVOR INFORME AO ADMINISTRADOR DO SISTEMA!")
						.timestamp(LocalDateTime.now())
						.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.type(ProblemType.ERRO_DE_SISTEMA.getUri())
						.title(ProblemType.ERRO_DE_SISTEMA.getTitle())
						.build());
	}


	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorApiResponse> HttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ErrorApiResponse.builder().description("Dados incorretos")
						.message("O corpo da requisição está inválido. Verifique erro de sintaxe.")
						.timestamp(LocalDateTime.now())
						.status(HttpStatus.BAD_REQUEST.value())
						.type(ProblemType.MENSAGEM_INCOMPREENSIVEL.getUri())
						.title(ProblemType.MENSAGEM_INCOMPREENSIVEL.getTitle())
						.build());

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorApiResponse> AccessDeniedException(AccessDeniedException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(ErrorApiResponse.builder()
						.message("Você não possui permissão para executar essa operação!")
						.timestamp(LocalDateTime.now())
						.status(HttpStatus.FORBIDDEN.value())
						.type(ProblemType.ACESSO_NEGADO.getUri())
						.title(ProblemType.ACESSO_NEGADO.getTitle())
						.build());
	}
}
