package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private FilesMapper fileMapper;

    public  FileService(FilesMapper fileMapper) {
        this.fileMapper = fileMapper;
    }
        @PostConstruct
        public void postConstruct() {
            System.out.println("Creating FileService bean");
        }


        public List<Files> getFilesForUser(int userid){
            return fileMapper.getAllFiles(userid);
        }

    public void addFile(MultipartFile multipartFile , Integer userid) throws IOException {
        Files files = new Files();
        files.setUserid(userid);
        files.setFilename(multipartFile.getOriginalFilename());
        files.setContenttype((multipartFile.getContentType()));
        files.setFilesize("" + multipartFile.getSize());
        files.setFiledata(multipartFile.getBytes());
        fileMapper.insertFile(files);

    }

    public Files findFile(Integer fileid) {
        return fileMapper.getFile(fileid);
    }

    public void deleteFile(Integer fileid) {
        fileMapper.deleteFile(fileid);
        System.out.println(fileid);
    }


}
