package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.controller.dto.CredentialDto;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;

import java.util.List;

public interface CredentialService {
    List<CredentialDto> getAllCredentials(String username) throws Exception;

    CredentialDto getCredentialByCredentialId(Integer credentialId) throws Exception;

    int createCredential(Credential credential, String username) throws Exception;

    boolean deleteCredential(Integer credentialId) throws Exception;

    int updateCredential(Credential credential) throws Exception;
}
