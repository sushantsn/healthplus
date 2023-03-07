package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Doctor;
import com.app.entities.User;
import com.app.model.DoctorSelectViewModel;
import com.app.model.PatientViewModel;
import com.app.model.bind.EditPatientModel;
import com.app.model.bind.PatientRegistrationModel;
import com.app.service.DoctorService;
import com.app.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private PatientService patientService;

    private DoctorService doctorService;

    @Autowired
    public PatientController(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }
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
    @GetMapping("/patient/{id}")
    public String getPatient(@PathVariable long id, Model model) {
        PatientViewModel patientViewModel = this.patientService.getById(id);

        model.addAttribute("patientViewModel", patientViewModel);

        return "patient/patient";
    }

    //TODO: admin
    @GetMapping("/patients")
    public String getPatients(Model model, @PageableDefault(size = 8) Pageable pageable) {
        Page<PatientViewModel> patients = this.patientService.getAll(pageable);
        model.addAttribute("patients", patients);

        return "patient/patients";
    }

    @GetMapping("/doctor/patients")
    public String getDoctorPatients(Model model, @PageableDefault(size = 8) Pageable pageable, Authentication principal) {
        long userId = ((User) principal.getPrincipal()).getId();
        Doctor doctor = this.doctorService.getByUserId(userId);

        Page<PatientViewModel> patients = this.patientService.getPatientsByDoctorId(pageable, doctor.getId());
        model.addAttribute("patients", patients);

        return "patient/patients";
    }

    @GetMapping("/register-patient")
    public String getPatientRegister(@ModelAttribute PatientRegistrationModel patientRegistrationModel, Model model) {
        List<DoctorSelectViewModel> doctors = this.doctorService.getAllSelect();
        model.addAttribute("doctors", doctors);

        return "patient/register";
    }

    @PostMapping("/register-patient")
    public String registerPatient(@Valid @ModelAttribute PatientRegistrationModel patientRegistrationModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<DoctorSelectViewModel> doctors = this.doctorService.getAllSelect();
            model.addAttribute("doctors", doctors);

            return "patient/register";
        }

        this.patientService.create(patientRegistrationModel);

        return "redirect:/";
    }

    @GetMapping("/patient/edit")
    public String getEditPatient(Model model, Authentication principal) {
        long userId = ((User) principal.getPrincipal()).getId();

        EditPatientModel editPatientModel = this.patientService.getEditModelByUserId(userId);

        model.addAttribute("editPatientModel", editPatientModel);

        return "patient/edit";
    }

    @PostMapping("/patient/edit")
    public String editPatient(@Valid @ModelAttribute EditPatientModel editPatientModel, BindingResult bindingResult, Authentication principal) {
        if(bindingResult.hasErrors()){
            return "patient/edit";
        }

        long userId = ((User) principal.getPrincipal()).getId();
        EditPatientModel editPatientModelId = this.patientService.getEditModelByUserId(userId);
        editPatientModel.setId(editPatientModelId.getId());

        this.patientService.save(editPatientModel);

        return "redirect:/";
    }

}
