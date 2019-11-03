package com.bookmanagement.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bookmanagement.entity.ApiError;

/**
 * This is CustomGlobalExceptionHandler class
 * 
 * @author Aung Ba
 *
 */
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * This method is used to handle argument is not valid in method
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}

		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);

		return handleExceptionInternal(ex, apiError, headers, apiError.getHttpStatus(), request);
	}

	/**
	 * This method is used to handle bind exception
	 */
	@Override
	protected ResponseEntity<Object> handleBindException(final BindException bindException,
			final HttpHeaders httpHeaders, final HttpStatus httpStatus, final WebRequest webRequest) {
		final List<String> errors = new ArrayList<String>();

		for (final FieldError error : bindException.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}

		for (final ObjectError objectError : bindException.getBindingResult().getGlobalErrors()) {
			errors.add(objectError.getObjectName() + ": " + objectError.getDefaultMessage());
		}

		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, bindException.getLocalizedMessage(), errors);
		return handleExceptionInternal(bindException, apiError, httpHeaders, apiError.getHttpStatus(), webRequest);
	}

	/**
	 * This method is used to handle method argument mixmatch
	 * 
	 * @param methodArgumentTypeMismatchException exception
	 * @param httpHeaders                         httpheaders
	 * @param httpStatus                          httpstatus
	 * @param webRequest                          webRequest
	 * @return ResponseEntity
	 */
	protected ResponseEntity<Object> handleMethodArgumentTypeMixMatch(
			final MethodArgumentTypeMismatchException methodArgumentTypeMismatchException,
			final HttpHeaders httpHeaders, final HttpStatus httpStatus, final WebRequest webRequest) {
		final String error = methodArgumentTypeMismatchException.getName() + " should be of type "
				+ methodArgumentTypeMismatchException.getRequiredType().getName();
		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
				methodArgumentTypeMismatchException.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
	}

	/**
	 * This method is used to handle if the requested url has no method.
	 * 
	 * @param noHandlerFoundException exception
	 * @param httpHeaders             httpHeaders
	 * @param httpStatus              httpStatus
	 * @param webRequest              webRequest
	 * @return ResponseEntity
	 */
	protected ResponseEntity<Object> handleNoHandlerFound(final NoHandlerFoundException noHandlerFoundException,
			final HttpHeaders httpHeaders, final HttpStatus httpStatus, final WebRequest webRequest) {
		final String error = "No handler found for " + noHandlerFoundException.getHttpMethod() + " "
				+ noHandlerFoundException.getRequestURL();
		final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, noHandlerFoundException.getLocalizedMessage(),
				error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
	}

	/**
	 * This method is used to handle if the request method is not supported
	 * 
	 * @param httpRequestMethodNotSupportedException exception
	 * @param httpHeaders                            httpHeaders
	 * @param httpStatus                             httpStatus
	 * @param webRequest                             webRequest
	 * @return ResponseEntity
	 */
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			final HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException,
			final HttpHeaders httpHeaders, final HttpStatus httpStatus, final WebRequest webRequest) {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(httpRequestMethodNotSupportedException.getMethod());
		stringBuilder.append(" method is not supported for this request. Supported methods are ");
		httpRequestMethodNotSupportedException.getSupportedHttpMethods()
				.forEach(method -> stringBuilder.append(method + " "));

		final ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED,
				httpRequestMethodNotSupportedException.getLocalizedMessage(), stringBuilder.toString());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
	}

	/**
	 * This method is used to handle if the media type is not supported
	 * 
	 * @param httpMediaTypeNotSupportedException exception
	 * @param httpHeaders                        httpHeaders
	 * @param httpStatus                         httpStatus
	 * @param webRequest                         webRequest
	 * @return ResponseEntity
	 */
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
			final HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException, final HttpHeaders httpHeaders,
			final HttpStatus httpStatus, final WebRequest webRequest) {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(httpMediaTypeNotSupportedException.getContentType());
		stringBuilder.append(" media type is not supported. Supported media types are ");
		httpMediaTypeNotSupportedException.getSupportedMediaTypes()
				.forEach(mediaType -> stringBuilder.append(mediaType + " "));

		final ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
				httpMediaTypeNotSupportedException.getLocalizedMessage(), stringBuilder.toString());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
	}

	/**
	 * This method is used to handle other exceptions
	 * 
	 * @param exception  exception
	 * @param webRequest webRequest
	 * @return ResponseEntity
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleAll(final Exception exception, final WebRequest webRequest) {
		final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage(),
				"error occurred");
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
	}
}
