package com.livevox.phonebook.controller;

import com.livevox.phonebook.model.ContactEntity;
import com.livevox.phonebook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/index")
    public String showList(Model model) {
        model.addAttribute("contacts", contactService.findAll());
        model.addAttribute("contact", new ContactEntity());
        return "index";
    }

    @PostMapping("/contacts")
    public String addUser(@Valid ContactEntity contact, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return null;
        }

        contactService.save(contact);
        return "redirect:/index";
    }

}
