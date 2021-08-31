package com.livevox.phonebook.service;

import com.livevox.phonebook.model.ContactEntity;
import com.livevox.phonebook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Iterable<ContactEntity> findAll() {
        return contactRepository.findAll();
    }

    public void save(ContactEntity contact) {

    }

}
