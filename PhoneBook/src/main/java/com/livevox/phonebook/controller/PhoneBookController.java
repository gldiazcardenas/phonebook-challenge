package com.livevox.phonebook.controller;

import com.livevox.phonebook.model.ContactEntity;
import com.livevox.phonebook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class PhoneBookController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/index")
    public String showContacts(@RequestParam(value = "q", required = false) String query, Model model) {
        model.addAttribute("contacts", contactService.filter(query));
        model.addAttribute("q", query);
        return "index";
    }

    @GetMapping("/create")
    public String addContact(Model model) {
        model.addAttribute("contact", new ContactEntity());
        return "add-contact";
    }

    @PostMapping("/add-contact")
    public String saveContact(@Valid ContactEntity contact, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("contact", contact);
            return "add-contact";
        }
        contactService.save(contact);
        return "redirect:/index";
    }

}
