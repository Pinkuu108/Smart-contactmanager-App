/*
 * package com.smart.controller;
 * 
 * import java.io.File; import java.nio.file.Files; import java.nio.file.Path;
 * import java.nio.file.Paths; import java.security.Principal;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.core.io.ClassPathResource; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.multipart.MultipartFile;
 * 
 * import com.smart.dao.ContactRepository; import com.smart.dao.UserReposatory;
 * import com.smart.entity.Contact; import com.smart.entity.User; import
 * com.smart.helper.Message;
 * 
 * import jakarta.servlet.http.HttpSession;
 * 
 * @Controller
 * 
 * @RequestMapping("/user") public class UserController {
 * 
 * @Autowired UserReposatory userReposatory;
 * 
 * @Autowired private ContactRepository contactRepository;
 * 
 * // method for adding common data to reponse
 * 
 * @ModelAttribute public void addCommonData(Model model, Principal principal) {
 * 
 * if (principal != null) { String username = principal.getName(); User user =
 * userReposatory.getUserByuserName(username); model.addAttribute("user", user);
 * } }
 * 
 * 
 * // Dashbord home
 * 
 * @GetMapping("/index") public String getuser(Model model, Principal principal)
 * { model.addAttribute("titel", "User DashBoard"); return
 * "normal/user_dashboard"; }
 * 
 * @GetMapping("/add-contact") public String openAddCOntactForm(Model model) {
 * model.addAttribute("titel", "Add Contact"); model.addAttribute("contact", new
 * Contact());
 * 
 * return "normal/add_contact_form"; }
 * 
 * @PostMapping("/process-contact") public String
 * processContact( @ModelAttribute Contact
 * contact, @RequestParam("profileImage") MultipartFile file, Principal
 * principal, HttpSession session) {
 * 
 * try { String userName = principal.getName(); User user =
 * this.userReposatory.getUserByuserName(userName);
 * 
 * // image upload if (file.isEmpty()) { contact.setImage("contact.png"); } else
 * { contact.setImage(file.getOriginalFilename());
 * 
 * File saveDir = new ClassPathResource("static/img").getFile(); Path path =
 * Paths.get(saveDir.getAbsolutePath() + File.separator +
 * file.getOriginalFilename()); Files.copy(file.getInputStream(), path); }
 * 
 * contact.setUser(user); this.contactRepository.save(contact);
 * 
 * session.setAttribute("message", new Message("Contact added successfully!",
 * "alert-success"));
 * 
 * } catch (Exception e) { e.printStackTrace(); session.setAttribute("message",
 * new Message("Something went wrong!", "alert-danger")); }
 * 
 * return "redirect:/user/add-contact"; }
 * 
 * @GetMapping("/show-contacts") public String showContacts(Model model,
 * Principal principal) {
 * 
 * String userName = principal.getName(); User user =
 * this.userReposatory.getUserByuserName(userName);
 * 
 * model.addAttribute("contacts", this.contactRepository.findByUser(user));
 * model.addAttribute("title", "Show Contacts");
 * 
 * return "normal/show_contacts"; }
 * 
 * @GetMapping("/contact/{cId}") public String
 * showContactDetail(@PathVariable("cId") Integer cId, Model model) {
 * 
 * Contact contact = this.contactRepository.findById(cId).get();
 * model.addAttribute("contact", contact); model.addAttribute("title",
 * "Contact Detail");
 * 
 * return "normal/contact_detail"; }
 * 
 * @GetMapping("/delete/{cId}") public String deleteContact(@PathVariable("cId")
 * Integer cId, HttpSession session) {
 * 
 * try { this.contactRepository.deleteById(cId); session.setAttribute("message",
 * new Message("Contact deleted successfully!", "alert-success")); } catch
 * (Exception e) { e.printStackTrace(); session.setAttribute("message", new
 * Message("Something went wrong!", "alert-danger")); }
 * 
 * return "redirect:/user/show-contacts"; }
 * 
 * // open update contact form
 * 
 * @GetMapping("/update-contact/{cId}") public String
 * openUpdateForm(@PathVariable("cId") Integer cId, Model model) {
 * 
 * Contact contact = this.contactRepository.findById(cId).get();
 * model.addAttribute("contact", contact); model.addAttribute("title",
 * "Update Contact");
 * 
 * return "normal/update_contact"; }
 * 
 * // process update contact
 * 
 * @PostMapping("/update-contact") public String updateContact(@ModelAttribute
 * Contact contact, @RequestParam("profileImage") MultipartFile file, Principal
 * principal, HttpSession session) {
 * 
 * try { // get logged in user String userName = principal.getName(); User user
 * = this.userReposatory.getUserByuserName(userName);
 * 
 * // old contact Contact oldContact =
 * this.contactRepository.findById(contact.getcId()).get();
 * 
 * // image handling if (!file.isEmpty()) { File saveDir = new
 * ClassPathResource("static/img").getFile(); Path path =
 * Paths.get(saveDir.getAbsolutePath() + File.separator +
 * file.getOriginalFilename()); Files.copy(file.getInputStream(), path);
 * 
 * contact.setImage(file.getOriginalFilename()); } else {
 * contact.setImage(oldContact.getImage()); }
 * 
 * contact.setUser(user); this.contactRepository.save(contact);
 * 
 * session.setAttribute("message", new Message("Contact updated successfully!",
 * "alert-success"));
 * 
 * } catch (Exception e) { e.printStackTrace(); session.setAttribute("message",
 * new Message("Something went wrong!", "alert-danger")); }
 * 
 * return "redirect:/user/show-contacts"; }
 * 
 * @GetMapping("/search") public String searchContacts(@RequestParam("query")
 * String query, Model model, Principal principal) {
 * 
 * String userName = principal.getName(); User user =
 * this.userReposatory.getUserByuserName(userName);
 * 
 * model.addAttribute("contacts",
 * this.contactRepository.findByNameContainingAndUser(query, user));
 * model.addAttribute("title", "Search Result");
 * 
 * return "normal/show_contacts"; }
 * 
 * @GetMapping("/delete/{cid}") public String deleteContact(@PathVariable("cid")
 * Integer cid, Principal principal, HttpSession session) {
 * 
 * try { // get logged-in user String username = principal.getName(); User user
 * = this.userReposatory.getUserByuserName(username);
 * 
 * // get contact Contact contact = this.contactRepository.findById(cid).get();
 * 
 * // security check: delete only own contact if
 * (user.getId().equals(contact.getUser().getId())) {
 * this.contactRepository.delete(contact); session.setAttribute("message", new
 * Message("Contact deleted successfully!", "alert-success")); } else {
 * session.setAttribute("message", new
 * Message("You are not allowed to delete this contact!", "alert-danger")); }
 * 
 * } catch (Exception e) { e.printStackTrace(); session.setAttribute("message",
 * new Message("Something went wrong while deleting!", "alert-danger")); }
 * 
 * return "redirect:/user/show-contacts"; }
 * 
 * }
 */
package com.smart.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smart.dao.ContactRepository;
import com.smart.dao.UserReposatory;
import com.smart.entity.Contact;
import com.smart.entity.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserReposatory userReposatory;

	@Autowired
	private ContactRepository contactRepository;

	// ================= COMMON DATA =================
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		if (principal != null) {
			String username = principal.getName();
			User user = userReposatory.getUserByuserName(username);
			model.addAttribute("user", user);
		}
	}

	// ================= DASHBOARD =================
	@GetMapping("/index")
	public String userDashboard(Model model) {
		model.addAttribute("title", "User Dashboard");
		return "normal/user_dashboard";
	}

	// ================= ADD CONTACT =================
	@GetMapping("/add-contact")
	public String openAddContactForm(Model model) {
		model.addAttribute("title", "Add Contact");
		model.addAttribute("contact", new Contact());
		return "normal/add_contact_form";
	}

	@PostMapping("/process-contact")
	public String processContact(
			@ModelAttribute Contact contact,
			@RequestParam("profileImage") MultipartFile file,
			Principal principal,
			HttpSession session) {

		try {
			User user = userReposatory.getUserByuserName(principal.getName());

			if (file.isEmpty()) {
				contact.setImage("contact.png");
			} else {
				File saveDir = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(saveDir.getAbsolutePath(), file.getOriginalFilename());
				Files.copy(file.getInputStream(), path);
				contact.setImage(file.getOriginalFilename());
			}

			contact.setUser(user);
			contactRepository.save(contact);

			session.setAttribute("message",
					new Message("Contact added successfully!", "alert-success"));

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message",
					new Message("Something went wrong!", "alert-danger"));
		}

		return "redirect:/user/add-contact";
	}

	// ================= SHOW CONTACTS =================
	@GetMapping("/show-contacts")
	public String showContacts(Model model, Principal principal) {
		User user = userReposatory.getUserByuserName(principal.getName());
		model.addAttribute("contacts", contactRepository.findByUser(user));
		model.addAttribute("title", "Show Contacts");
		return "normal/show_contacts";
	}

	// ================= CONTACT DETAIL =================
	@GetMapping("/contact/{cId}")
	public String showContactDetail(@PathVariable Integer cId, Model model) {
		Contact contact = contactRepository.findById(cId).orElse(null);
		model.addAttribute("contact", contact);
		model.addAttribute("title", "Contact Detail");
		return "normal/contact_detail";
	}

	// ================= UPDATE CONTACT =================
	@GetMapping("/update-contact/{cId}")
	public String openUpdateForm(@PathVariable Integer cId, Model model) {
		Contact contact = contactRepository.findById(cId).orElse(null);
		model.addAttribute("contact", contact);
		model.addAttribute("title", "Update Contact");
		return "normal/update_contact";
	}

	@PostMapping("/update-contact")
	public String updateContact(
			@ModelAttribute Contact contact,
			@RequestParam("profileImage") MultipartFile file,
			Principal principal,
			HttpSession session) {

		try {
			User user = userReposatory.getUserByuserName(principal.getName());
			Contact oldContact = contactRepository.findById(contact.getcId()).orElse(null);

			if (!file.isEmpty()) {
				File saveDir = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(saveDir.getAbsolutePath(), file.getOriginalFilename());
				Files.copy(file.getInputStream(), path);
				contact.setImage(file.getOriginalFilename());
			} else if (oldContact != null) {
				contact.setImage(oldContact.getImage());
			}

			contact.setUser(user);
			contactRepository.save(contact);

			session.setAttribute("message",
					new Message("Contact updated successfully!", "alert-success"));

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message",
					new Message("Something went wrong!", "alert-danger"));
		}

		return "redirect:/user/show-contacts";
	}

	// ================= SEARCH =================
	@GetMapping("/search")
	public String searchContacts(
			@RequestParam("query") String query,
			Model model,
			Principal principal) {

		User user = userReposatory.getUserByuserName(principal.getName());
		model.addAttribute("contacts",
				contactRepository.findByNameContainingAndUser(query, user));
		model.addAttribute("title", "Search Result");
		return "normal/show_contacts";
	}

	// ================= DELETE CONTACT (FINAL & SECURE) =================
	@GetMapping("/delete/{cid}")
	public String deleteContact(
			@PathVariable Integer cid,
			Principal principal,
			HttpSession session) {

		try {
			User user = userReposatory.getUserByuserName(principal.getName());
			Contact contact = contactRepository.findById(cid).orElse(null);

			if (contact == null) {
				session.setAttribute("message",
						new Message("Contact not found!", "alert-danger"));
			} else if (user.getId().equals(contact.getUser().getId())) {
				contactRepository.delete(contact);
				session.setAttribute("message",
						new Message("Contact deleted successfully!", "alert-success"));
			} else {
				session.setAttribute("message",
						new Message("You are not allowed to delete this contact!", "alert-danger"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message",
					new Message("Something went wrong!", "alert-danger"));
		}

		return "redirect:/user/show-contacts";
	}
}
