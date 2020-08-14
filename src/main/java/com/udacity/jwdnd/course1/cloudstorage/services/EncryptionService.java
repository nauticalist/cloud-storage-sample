package com.udacity.jwdnd.course1.cloudstorage.services;


public interface EncryptionService {

    String encryptValue(String data, String key);

    String decryptValue(String data, String key);
}
