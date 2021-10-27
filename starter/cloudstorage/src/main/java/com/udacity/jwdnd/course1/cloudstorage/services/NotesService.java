package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NotesService {

    private NotesMapper notesMapper;

    public NotesService(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating NotesService bean");
    }


    public List<Notes> getAllNotes(int userid){
        return notesMapper.getAllNotes(userid);
    }

    public Notes getNotes(int noteid){
        return notesMapper.getOneNote(noteid);
    }

    public int addNotes(Notes notes){
        return notesMapper.insertNotes(new Notes(null, notes.getNotetitle(), notes.getNotedescription(),notes.getUserid()));

    }

    public int updateNotes(Notes notes){
        return notesMapper.updateNote(new Notes(notes.getNoteid(), notes.getNotetitle(), notes.getNotedescription(),notes.getUserid()));

    }

    public void deleteNotes(int noteid){
        notesMapper.deleteNote(noteid);
    }


}
