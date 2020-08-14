package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;

public interface AuthenticationService extends AuthenticationProvider {
    Authentication authenticate(Authentication authentication);

    boolean supports(Class<?> authentication);
}
