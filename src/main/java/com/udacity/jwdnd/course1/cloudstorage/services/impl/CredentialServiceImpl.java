package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.controller.dto.CredentialDto;
import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialServiceImpl implements CredentialService {

    private final CredentialMapper credentialMapper;
    private final UserService userService;
    private final EncryptionService encryptionService;

    @Autowired
    public CredentialServiceImpl(CredentialMapper credentialMapper, UserService userService, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }


    @Override
    public List<CredentialDto> getAllCredentials(String username) throws Exception {
        User user = userService.getUser(username);
        if (user == null)
            throw new Exception("Unable to find user:  " + username);

        List<Credential> credentials = credentialMapper.findAllByUserId(user.getUserId());
        return credentials.stream().map(this::mapCredentialToCredentialDto).collect(Collectors.toList());
    }

    @Override
    public CredentialDto getCredentialByCredentialId(Integer credentialId) throws Exception {
        Credential credential = credentialMapper.findByCredentialId(credentialId);

        if (credential == null)
            throw new Exception("Unable to credential with id:  " + credentialId);

        if (!this.verifyOwner(credential.getUserId())) {
            throw new Exception("Unauthorized access to credential detected: " + credentialId);
        }

        String decryptedPassword = encryptionService.decryptValue(credential.getPassword(), credential.getKey());
        credential.setPassword(decryptedPassword);

        return this.mapCredentialToCredentialDto(credential);
    }

    @Override
    public int createCredential(Credential credential, String username) throws Exception {
        User user = userService.getUser(username);
        if (user == null)
            throw new Exception("Unable to find user:  " + username);

        String encryptionKey = this.generateEncryptionKey();
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encryptionKey);

        int newCredentialId = credentialMapper.create(new Credential(null,
                credential.getUrl(), credential.getUsername(), encryptionKey, encryptedPassword, user.getUserId()));

        if(newCredentialId < 1)
            throw new Exception("Unable to save credential");

        return newCredentialId;
    }

    @Override
    public boolean deleteCredential(Integer credentialId) throws Exception {
        Credential credential = credentialMapper.findByCredentialId(credentialId);

        if ( credential == null) {
            throw new Exception("Unable to credential with id:  " + credentialId);
        }

        if (!this.verifyOwner(credential.getUserId())) {
            throw new Exception("Unauthorized access to credential detected: " + credentialId);
        }

        return credentialMapper.delete(credentialId);
    }

    @Override
    public int updateCredential(Credential credential) throws Exception {
        Credential credentialToUpdate = credentialMapper.findByCredentialId(credential.getCredentialId());

        if (credentialToUpdate == null)
            throw new Exception("Unable to find credential with id:  " + credential.getCredentialId());

        if (!this.verifyOwner(credentialToUpdate.getUserId())) {
            throw new Exception("Unauthorized access to credential detected: " + credentialToUpdate.getCredentialId());
        }

        String encryptionKey = this.generateEncryptionKey();
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encryptionKey);

        int updatedCredential = credentialMapper.update(credential.getCredentialId(), credential.getUrl(),
                credential.getUsername(), encryptionKey, encryptedPassword);

        if (updatedCredential < 1)
            throw new Exception("Unable to save credential");

        return updatedCredential;
    }

    private String generateEncryptionKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    private CredentialDto mapCredentialToCredentialDto(Credential credential) {
        String decryptedPassword = encryptionService.decryptValue(credential.getPassword(), credential.getKey());
        return new CredentialDto(credential.getCredentialId(), credential.getUrl(), credential.getUsername(), credential.getPassword(), decryptedPassword);
    }

    private boolean verifyOwner(int userId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        User user = userService.getUser(username);

        return (user.getUserId().equals(userId));
    }
}
