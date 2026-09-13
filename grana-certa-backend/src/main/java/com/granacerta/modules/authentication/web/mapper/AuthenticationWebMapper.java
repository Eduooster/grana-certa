package com.granacerta.modules.authentication.web.mapper;

import com.granacerta.modules.authentication.application.command.LoginUserCommand;
import com.granacerta.modules.authentication.application.command.RegisterUserCommand;
import com.granacerta.security.jwt.AuthenticationToken;
import com.granacerta.modules.authentication.web.dto.AuthenticationResponse;
import com.granacerta.modules.authentication.web.dto.LoginRequest;

import com.granacerta.modules.authentication.web.dto.RegisterUserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationWebMapper {

    LoginUserCommand toCommand(LoginRequest request);

    AuthenticationResponse toResponse(AuthenticationToken result);

    RegisterUserCommand toRegisterCommand(RegisterUserRequest request);
}
