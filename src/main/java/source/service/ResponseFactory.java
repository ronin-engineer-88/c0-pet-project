package source.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import source.constant.ResponseCode;
import source.dto.response.ApiError;
import source.dto.response.Meta;
import source.dto.response.ResponseDto;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ResponseFactory {

    @Value("${spring.application.name}")
    String appName;


    public ResponseDto response(ResponseCode responseCode) {
        var meta = Meta.builder()
                .status(responseCode.getCode())
                .serviceId(appName)
                .build();

        return new ResponseDto(meta, null);
    }

    public ResponseDto response(Object payload) {
        var meta = Meta.builder()
                .status(ResponseCode.SUCCESS.getCode())
                .serviceId(appName)
                .build();

        return new ResponseDto(meta, payload);
    }

    public ResponseDto invalidParams(Collection<ApiError> errors) {
        var meta = Meta.builder()
                .status(ResponseCode.INVALID_PARAMS.getCode())
                .serviceId(appName)
                .errors(errors)
                .build();

        return new ResponseDto(meta, null);
    }
}