package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface NotesMapper {
    @Select("Select * from Notes where userid=#{userid}")
    List<Notes> getAllNotes(int userid);

    @Select("Select * from Notes where noteid=#{noteid}")
    Notes getOneNote(int noteid);

    @Insert("Insert into Notes(notetitle,notedescription,userid) values(#{notetitle},#{notedescription},#{userid})")
    @Options(useGeneratedKeys = true,keyProperty = "noteid")
    int insertNotes(Notes notes);

    @Delete("Delete from Notes where noteid=#{noteid}")
    void deleteNote(int noteid);

    @Update("Update Notes Set notetitle=#{notetitle}, notedescription =#{notedescription} WHERE noteid =#{noteid}")
    int updateNote(Notes notes);
}
