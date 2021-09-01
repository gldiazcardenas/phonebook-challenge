package com.livevox.phonebook.service;

import com.livevox.phonebook.model.ContactEntity;
import com.livevox.phonebook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Iterable<ContactEntity> findAll() {
        return contactRepository.findAll();
    }

    public Iterable<ContactEntity> filter(String q) {
        if (!StringUtils.hasText(q)) {
            return findAll();
        }
        return contactRepository.findByFirstNameContainingOrLastNameContainingOrPhoneContaining(q, q, q);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(ContactEntity contact) {
        contactRepository.save(contact);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        contactRepository.deleteById(id);
    }
}
