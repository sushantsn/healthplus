package com.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
	// any one should be able view the doctors
	@GetMapping("/view")
	public String viewDoctors() {
		return "You are able to view the doctors , w/o authentication ....";
	}

	// customer should be able to book doctors
	@GetMapping("/book")
	public String bookDoctors(Authentication auth) {
		System.out.println(auth);
		StringBuilder sb = new StringBuilder("Hello , ");
		sb.append(auth.getName()).append("  you are logged in under ").append(auth.getAuthorities())
				.append(" Proceed to purchasing of doctors ...");
		return sb.toString();
	}

	// admin should be able to add the doctors
	@GetMapping("/add")
	public String addDoctors() {
		return "As admin , you will be able to add the doctors";
	}

	// only authenticated user (any role !) can browse the categories
	@GetMapping("/speciality")
	public String browseDoctors() {
		return "Any Authenticated user(any role) can browse through the categories";

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/delete")
	public String deleteDoctor() {
		return "As admin , you will be able to delete the doctors";

	}

}
