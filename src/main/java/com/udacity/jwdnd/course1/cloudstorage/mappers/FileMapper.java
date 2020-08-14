package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM files WHERE userid = #{userid}")
    List<File> findAllByUserId(int userid);

    @Select("SELECT * FROM files WHERE fileId = #{fileId}")
    File findByFileId(int fileId);

    @Select("SELECT * FROM files WHERE filename = #{fileName}")
    File findByFileName(String fileName);

    @Insert("INSERT INTO files (filename, contenttype, filesize, filedata, userId) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{fileData}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int create(File file);

    @Delete("DELETE FROM files WHERE fileId = #{fileId}")
    boolean delete(int fileId);
}
