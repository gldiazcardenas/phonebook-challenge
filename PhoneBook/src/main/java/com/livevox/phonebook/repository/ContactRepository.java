package com.livevox.phonebook.repository;

import com.livevox.phonebook.model.ContactEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<ContactEntity, Long> {

    Iterable<ContactEntity> findByFirstNameContainingOrLastNameContainingOrPhoneContaining(
            String firstName,
            String lastName,
            String phone
    );

}
