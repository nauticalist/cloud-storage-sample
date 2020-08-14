package com.udacity.jwdnd.course1.cloudstorage.controller.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class FileDto {
    private int fileId;

    private String fileName;

    private String dataURL;

    public FileDto() {
    }

    public FileDto(int fileId, String fileName, String dataURL) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.dataURL = dataURL;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDataURL() {
        return dataURL;
    }

    public void setDataURL(String dataURL) {
        this.dataURL = dataURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof FileDto)) return false;

        FileDto fileDto = (FileDto) o;

        return new EqualsBuilder()
                .append(getFileId(), fileDto.getFileId())
                .append(getFileName(), fileDto.getFileName())
                .append(getDataURL(), fileDto.getDataURL())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getFileId())
                .append(getFileName())
                .append(getDataURL())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", dataURL='" + dataURL + '\'' +
                '}';
    }
}
