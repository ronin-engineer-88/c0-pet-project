package source.exception;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import source.constant.ResponseCode;
import source.dto.response.ResponseDto;
import source.service.ResponseFactory;
import static source.dto.response.ApiError.createFieldApiError;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ApiExceptionHandler {

    private final ResponseFactory responseFactory;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseDto> handleBusinessException(BusinessException e,
                                                               HttpServletRequest request,
                                                               HttpServletResponse response) {
        ResponseCode code = e.getResponseCode();
        if (code != null) {
            return ResponseEntity
                    .status(code.getStatus())
                    .body(responseFactory.response(code));
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(responseFactory.response(ResponseCode.INTERNAL_SERVER_ERROR));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    ResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var apiErrors = e.getFieldErrors().stream()
                .map(v -> createFieldApiError(v.getField(), v.getDefaultMessage(), v.getRejectedValue()))
                .toList();

        return responseFactory.invalidParams(apiErrors);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    ResponseDto handleThrowable(Throwable e) {
        return responseFactory.response(ResponseCode.INTERNAL_SERVER_ERROR);
    }
}
