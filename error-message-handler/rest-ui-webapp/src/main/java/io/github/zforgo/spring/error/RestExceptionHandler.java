package io.github.zforgo.spring.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import javax.validation.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Function<Path, String> buildPathString = path -> StreamSupport.stream(path.spliterator(), false)
			.filter(node -> node.getKind() == ElementKind.PROPERTY)
			.map(Path.Node::toString)
			.collect(Collectors.joining("."));

	//TODO use it and make currying to unwrap MANVEx
	private static final Function<Stream<ConstraintViolation<?>>, List<ErrorResponse.Item>> mapToItem = stream ->
			stream.map(RestExceptionHandler::buildItem)
					.collect(Collectors.toList());

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  HttpHeaders headers, HttpStatus status, WebRequest request) {
		var items = ex.getBindingResult().getAllErrors()
				.stream()
				.map(error -> error.unwrap(ConstraintViolation.class))
				.map(RestExceptionHandler::buildItem)
				.collect(Collectors.toList());
		return ResponseEntity.badRequest().body(ErrorResponse.of(items));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	ResponseEntity<ErrorResponse> handle(ConstraintViolationException ex) {
		var items = ex.getConstraintViolations().stream()
				.map(RestExceptionHandler::buildItem)
				.collect(Collectors.toList());
		return ResponseEntity.badRequest().body(ErrorResponse.of(items));
	}

	private static ErrorResponse.Item buildItem(ConstraintViolation<?> violation) {
		var item = new ErrorResponse.Item()
				.withMessageKey(violation.getMessageTemplate())
				.withField(buildPathString.apply(violation.getPropertyPath()));
		return item;
	}

}
