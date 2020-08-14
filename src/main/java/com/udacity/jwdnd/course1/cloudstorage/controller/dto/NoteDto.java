package com.udacity.jwdnd.course1.cloudstorage.controller.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class NoteDto {
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;

    public NoteDto() {
    }

    public NoteDto(Integer noteId, String noteTitle, String noteDescription) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof NoteDto)) return false;

        NoteDto noteDto = (NoteDto) o;

        return new EqualsBuilder()
                .append(getNoteId(), noteDto.getNoteId())
                .append(getNoteTitle(), noteDto.getNoteTitle())
                .append(getNoteDescription(), noteDto.getNoteDescription())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getNoteId())
                .append(getNoteTitle())
                .append(getNoteDescription())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "NoteDto{" +
                "noteId=" + noteId +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteDescription='" + noteDescription + '\'' +
                '}';
    }
}
