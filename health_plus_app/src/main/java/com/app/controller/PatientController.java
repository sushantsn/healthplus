package com.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {
	// any one should be able view the patients
	@GetMapping("/view")
	public String viewPatients() {
		return "You are able to view the patients , w/o authentication ....";
	}

	// customer should be able to book patients
//	@GetMapping("/book")
//	public String bookPatients(Authentication auth) {
//		System.out.println(auth);
//		StringBuilder sb = new StringBuilder("Hello , ");
//		sb.append(auth.getName()).append("  you are logged in under ").append(auth.getAuthorities())
//				.append(" Proceed to purchasing of patients ...");
//		return sb.toString();
//	}

	// admin should be able to add the patients
	@GetMapping("/add")
	public String addPatients() {
		return "As admin , you will be able to add the patients";
	}

	// only authenticated user (any role !) can browse the categories
	@GetMapping("/speciality")
	public String browsePatients() {
		return "Any Authenticated user(any role) can browse through the categories";

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/delete")
	public String deletePatient() {
		return "As admin , you will be able to delete the patients";

	}

}
