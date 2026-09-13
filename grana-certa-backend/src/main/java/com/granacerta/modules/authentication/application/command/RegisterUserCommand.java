package com.granacerta.modules.authentication.application.command;

public record RegisterUserCommand(String email, String password, String name){
}
