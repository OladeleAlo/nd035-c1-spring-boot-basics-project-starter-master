package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService {
    private CredentialsMapper credentialsMapper;

    public CredentialsService(CredentialsMapper credentialsMapper) {
        this.credentialsMapper = credentialsMapper;
    }

    public int addCredentials(Credentials credentials){
        return credentialsMapper.insertCredentials(new Credentials(null,credentials.getUrl(),credentials.getUsername(),credentials.getKey(),credentials.getPassword(),credentials.getUserid()));
    }

    public Credentials getCredentials(int credentialid){
        return credentialsMapper.getCredentials(credentialid);
    }
    public List<Credentials> getAllCredentials(int userid){
        return credentialsMapper.getAllCredentials(userid);
    }

    public void deleteCredentials(int credentialid){
        credentialsMapper.deleteCredentials(credentialid);
    }
    public int updateCredentials(Credentials credentials){
        return credentialsMapper.updateCredentials(credentials);
    }
}
