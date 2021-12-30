package com.nerdy.test.service.mapper;

public interface RequestDtoMapper<R, M> {
    M mapToModel(R dto);
}
