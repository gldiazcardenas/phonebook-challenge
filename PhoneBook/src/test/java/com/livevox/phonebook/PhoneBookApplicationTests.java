package com.livevox.phonebook;

import com.livevox.phonebook.model.ContactEntity;
import com.livevox.phonebook.service.ContactService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PhoneBookApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ContactService contactService;

	@Test
	void contextLoads() {
		// do nothing
	}

	@Test
	public void shouldListContacts_Empty() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/index"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("No contacts")));
	}

	@Test
	public void shouldListContacts_WithOneContact() throws Exception {
		ContactEntity contact1 = new ContactEntity();
		contact1.setFirstName("Gabriel Leonardo");
		contact1.setLastName("Diaz Cardenas");

		when(contactService.filter(any())).thenReturn(Lists.newArrayList(contact1));

		mockMvc.perform(MockMvcRequestBuilders.get("/index"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Gabriel Leonardo")))
				.andExpect(content().string(containsString("Diaz Cardenas")));
	}

	@Test
	public void shouldListContacts_Filtered() throws Exception {
		ContactEntity contact1 = new ContactEntity();
		contact1.setFirstName("Gabriel Leonardo");
		contact1.setLastName("Diaz Cardenas");

		ContactEntity contact2 = new ContactEntity();
		contact2.setFirstName("Pepito");
		contact2.setLastName("Perez");

		when(contactService.filter(any())).thenReturn(Lists.newArrayList(contact1, contact2));
		when(contactService.filter(eq("pep"))).thenReturn(Lists.newArrayList(contact2));

		mockMvc.perform(MockMvcRequestBuilders.get("/index?q=pep"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Pepito")))
				.andExpect(content().string(not(containsString("Gabriel Leonardo"))));
	}

	@Test
	public void shouldStartContactCreate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/create"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("First Name")))
				.andExpect(content().string(containsString("Last Name")))
				.andExpect(content().string(containsString("Phone")));
	}

	@Test
	public void shouldAttemptAddContact_ReturnValidationError() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/add-contact"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<span class=\"error\">must not be blank</span>")));
	}

}
