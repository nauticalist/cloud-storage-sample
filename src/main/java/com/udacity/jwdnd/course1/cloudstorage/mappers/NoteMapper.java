package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM notes WHERE userid = #{userid}")
    List<Note> findAllByUserId(int userid);

    @Select("SELECT * FROM notes WHERE noteId = #{noteId}")
    Note findByNoteId(int noteId);

    @Insert("INSERT INTO notes (noteTitle, noteDescription, userId) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int create(Note note);

    @Update("UPDATE notes SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} WHERE noteId = #{noteId}")
    int update(int noteId, String noteTitle, String noteDescription);

    @Delete("DELETE FROM notes WHERE noteId = #{noteId}")
    boolean delete(int noteId);
}
