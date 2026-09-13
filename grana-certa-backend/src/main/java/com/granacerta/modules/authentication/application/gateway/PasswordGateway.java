package com.granacerta.modules.authentication.application.gateway;

public interface PasswordGateway {

    String hash(String password);
    boolean matches(String password, String hashPassword);


}
