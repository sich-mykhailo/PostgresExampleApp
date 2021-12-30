package com.nerdy.test.service.mapper;

public interface ResponseDtoMapper<R, M> {
    R mapToDto(M t);
}
