package com.qa.controllers;


import com.qa.models.Note;
import com.qa.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//CrossOrigin

@RestController
@CrossOrigin()
public class NotesController {
    //Dependency injection

    @Autowired
    private NoteRepository repository;


    @RequestMapping(value = "notes", method = RequestMethod.GET)
    public List<Note> listAllNotes(){
        return repository.findAll();
    }

    //take the data that is sent to us and then map it to our note object and store in our database
    @RequestMapping(value = "notes", method = RequestMethod.POST)
    public Note addNote(@RequestBody Note note){
        //A browser can't make a post request but postman can!
        return repository.saveAndFlush(note);
    }

    @RequestMapping(value = "notes/{id}", method = RequestMethod.GET)
    public Note getNote(@PathVariable Long id){
        return repository.findOne(id);
    }

    @RequestMapping(value = "notes/{id}", method = RequestMethod.DELETE)
    public Note deleteNote(@PathVariable Long id){
        Note existing = repository.findOne(id);
        repository.delete(existing);
        return existing;
    }


}
