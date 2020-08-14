package com.udacity.jwdnd.course1.cloudstorage.services;


public interface HashService {
    String getHashedValue(String data, String salt);
}
