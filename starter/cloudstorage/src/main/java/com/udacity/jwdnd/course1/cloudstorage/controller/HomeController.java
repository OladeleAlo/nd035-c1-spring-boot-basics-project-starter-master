package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.MessageForm;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private MessageListService messageListService;
    private FileService fileService;
    private UserService userService;
    private NotesService notesService;
    private CredentialsService credentialsService;

    public HomeController(MessageListService  messageListService,FileService fileService, UserService userService,NotesService notesService,CredentialsService credentialsService) {
        this.messageListService = messageListService;
        this.fileService = fileService;
        this.userService = userService;
        this.notesService = notesService;
        this.credentialsService = credentialsService;
    }

    @GetMapping()
    public String getHomePage(Authentication authentication, MessageForm messageForm, Model model) {
        model.addAttribute("files",fileService.getFilesForUser(userService.getUser(authentication.getName()).getUserid()));
       // model.addAttribute("notes",this.notesService.getAllNotes(userService.getUser(authentication.getName()).getUserid()));
        model.addAttribute("greetings", this.messageListService.getMessages());
        return "home";
    }

    @PostMapping()
    public String addMessage(MessageForm messageForm, Model model) {
        messageListService.addMessage(messageForm.getText());
        model.addAttribute("greetings", messageListService.getMessages());
        messageForm.setText("");
        return "home";
    }
}
