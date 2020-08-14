package com.udacity.jwdnd.course1.cloudstorage.controller.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CredentialDto {
    private Integer credentialId;

    private String url;

    private String username;

    private String password;

    private String decryptedPassword;

    public CredentialDto() {
    }

    public CredentialDto(Integer credentialId, String url, String username, String password, String decryptedPassword) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.password = password;
        this.decryptedPassword = decryptedPassword;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDecryptedPassword() {
        return decryptedPassword;
    }

    public void setDecryptedPassword(String decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof CredentialDto)) return false;

        CredentialDto that = (CredentialDto) o;

        return new EqualsBuilder()
                .append(getCredentialId(), that.getCredentialId())
                .append(getUrl(), that.getUrl())
                .append(getUsername(), that.getUsername())
                .append(getPassword(), that.getPassword())
                .append(getDecryptedPassword(), that.getDecryptedPassword())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getCredentialId())
                .append(getUrl())
                .append(getUsername())
                .append(getPassword())
                .append(getDecryptedPassword())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "CredentialDto{" +
                "credentialId=" + credentialId +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", decryptedPassword='" + decryptedPassword + '\'' +
                '}';
    }
}
