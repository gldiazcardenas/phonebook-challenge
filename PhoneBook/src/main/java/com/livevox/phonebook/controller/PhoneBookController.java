package com.livevox.phonebook.controller;

import com.livevox.phonebook.model.ContactEntity;
import com.livevox.phonebook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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
        model.addAttribute("contactEntity", new ContactEntity());
        return "add-contact";
    }

    @PostMapping("/add-contact")
    public String saveContact(@Valid ContactEntity contactEntity, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("contactEntity", contactEntity);
            return "add-contact";
        }
        contactService.save(contactEntity);
        return "redirect:/index";
    }

    @PostMapping("/delete")
    public String deleteContact(@RequestParam(value = "id", required = false) Long id, Model model) {
        contactService.delete(id);
        return "redirect:/index";
    }

}
