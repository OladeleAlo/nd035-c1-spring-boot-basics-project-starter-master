package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.*;

import java.io.File;
import java.util.List;

@Mapper
public interface FilesMapper {
    @Select("Select * from Files where userid=#{userid} ")
    List<Files> getAllFiles(int userid);

    @Select("Select * from Files where fileid=#{Fileid} ")
    Files getFile(int Fileid);

    @Insert("Insert into Files(filename,contenttype,filesize,userid,filedata) values(#{filename},#{contenttype},#{filesize}" +
            ",#{userid},#{filedata})")
    @Options(useGeneratedKeys = true,keyProperty = "fileid")
    int insertFile(Files files);

    @Delete("Delete from files where fileid=#{fileid}")
    void deleteFile(int fileid);

    @Delete("Delete from files where userid=#{userid}")
    void deleteAllFiles(int userid);

}
