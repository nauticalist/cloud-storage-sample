package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM credentials WHERE userid = #{userid}")
    List<Credential> findAllByUserId(int userid);

    @Select("SELECT * FROM credentials WHERE credentialId = #{credentialId}")
    Credential findByCredentialId(int credentialId);

    @Insert("INSERT INTO credentials (url, username, key, password, userId) VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int create(Credential credential);

    @Update("UPDATE credentials SET url = #{url}, username = #{username}, key = #{key}, password = #{password} WHERE credentialId = #{credentialId}")
    int update(int credentialId, String url, String username, String key, String password);

    @Delete("DELETE FROM credentials WHERE credentialId = #{credentialId}")
    boolean delete(int credentialId);
}
