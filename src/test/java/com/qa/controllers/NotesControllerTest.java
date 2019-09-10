package com.qa.controllers;



import com.qa.models.Note;
import com.qa.repository.NoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesControllerTest {

    @InjectMocks
    private NotesController notesController;

    @Mock
    private NoteRepository repository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllNotes(){
        List<Note> notesList = new ArrayList<>();
        Note note = new Note();
        note.setDescription("blah");
        note.setName("blah");
        notesList.add(note);
        when(repository.findAll()).thenReturn(notesList);
        assertEquals(notesController.listAllNotes().get(0).getName(), "blah");

        //assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/notes", List.class)).contains("Hello World!");
    }

    @Test
    public void getNoteTest(){
        Note note = new Note();
        note.setDescription("oof");
        note.setName("oooff");

        Note note2 = new Note();
        note2.setDescription("oof");
        note2.setName("jeff");

        Note note3 = new Note();
        note3.setDescription("oof");
        note3.setName("bobby");

        when(repository.findOne(anyLong())).thenReturn(note).thenReturn(note2).thenReturn(note3);
        assertEquals(notesController.getNote(1L).getName(), "oooff");
        assertEquals(notesController.getNote(7L).getName(), "jeff");
        assertEquals(notesController.getNote(20L).getName(), "bobby");
    }

    @Test
    public void deleteNoteTest(){
        Note note = new Note();
        note.setDescription("oof");
        note.setName("oooff");

        Note note2 = new Note();
        note2.setDescription("oof");
        note2.setName("jeff");

        Note note3 = new Note();
        note3.setDescription("oof");
        note3.setName("bobby");

        when(repository.findOne(anyLong())).thenReturn(note).thenReturn(note2).thenReturn(note3);
        assertEquals(notesController.deleteNote(1L).getName(), "oooff");
        assertEquals(notesController.deleteNote(7L).getName(), "jeff");
        assertEquals(notesController.deleteNote(20L).getName(), "bobby");
    }

    @Test
    public void addNoteTest(){
        Note note = new Note();
        note.setDescription("oof");
        note.setName("John");


        when(repository.saveAndFlush(note)).thenReturn(note);
        assertEquals(notesController.addNote(note).getName(), "John");

    }



}
