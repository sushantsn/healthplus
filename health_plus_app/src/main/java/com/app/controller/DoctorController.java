package com.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.app.entities.Doctor;
import com.app.entities.User;
import com.app.model.DoctorViewModel;
import com.app.model.LocationViewModel;
import com.app.model.bind.DoctorRegistrationModel;
import com.app.model.bind.EditDoctorModel;
import com.app.model.bind.EditDoctorPictureModel;
import com.app.service.DoctorService;
import com.app.service.LocationService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
	private DoctorService doctorService;

    private LocationService settlePointService;

    @Autowired
    public DoctorController(DoctorService doctorService, LocationService settlePointService) {
        this.doctorService = doctorService;
        this.settlePointService = settlePointService;
    }
	// any one should be able view the doctors
	@GetMapping("/view")
	public String viewDoctors() {
		return "You are able to view the doctors , w/o authentication ....";
	}

	 @GetMapping("/doctors/{id}")
	    public String getDoctor(@PathVariable long id, Model model) {
	        DoctorViewModel doctorViewModel = this.doctorService.getViewModelById(id);

	        model.addAttribute("doctorViewModel", doctorViewModel);

	        return "doctor/doctor";
	    }

//	    @GetMapping({"/", "/doctors"})
//	    public String getDoctors(Model model, @PageableDefault(size = 8) Pageable pageable) {
//	        Page<DoctorViewModel> doctors = this.doctorService.getAll(pageable);
//	        model.addAttribute("doctors", doctors);
	//
//	        return "doctor/doctors";
//	    }
	   	@GetMapping("/doctorslist")
		public List<Doctor> getDoctors()
		{
			System.out.println("in get emps");
			return doctorService.getAllDoctorDetails();
		}

	    @GetMapping("/register-doctor")
	    public String getDoctorRegister(@ModelAttribute DoctorRegistrationModel doctorRegistrationModel, Model model) {
	        List<LocationViewModel> settlePoints = this.settlePointService.getAll();
	        model.addAttribute("settlePoints", settlePoints);

	        return "doctor/register";
	    }

	    @PostMapping("/register-doctor")
	    public String registerDoctor(@Valid @ModelAttribute DoctorRegistrationModel doctorRegistrationModel, BindingResult bindingResult, Model model) {
	        if (bindingResult.hasErrors()) {
	            List<LocationViewModel> settlePoints = this.settlePointService.getAll();
	            model.addAttribute("settlePoints", settlePoints);

	            return "doctor/register";
	        }

	        this.doctorService.create(doctorRegistrationModel);

	        return "redirect:/";
	    }

	    @GetMapping("/doctor/edit")
	    public String getEditDoctor(Model model, Authentication principal) {
	        List<LocationViewModel> settlePoints = this.settlePointService.getAll();
	        model.addAttribute("settlePoints", settlePoints);

	        long userId = ((User) principal.getPrincipal()).getId();
	        EditDoctorModel editDoctorModel = this.doctorService.getEditModelByUserId(userId);

	        model.addAttribute("editDoctorModel", editDoctorModel);

	        return "doctor/edit";
	    }

	    @PostMapping("/doctor/edit")
	    public String editDoctor(@Valid @ModelAttribute EditDoctorModel editDoctorModel, BindingResult bindingResult,
	                             Authentication principal, Model model) {
	        if (bindingResult.hasErrors()) {
	            List<LocationViewModel> settlePoints = this.settlePointService.getAll();
	            model.addAttribute("settlePoints", settlePoints);

	            return "doctor/edit";
	        }

	        long userId = ((User) principal.getPrincipal()).getId();
	        EditDoctorModel editDoctorModelId = this.doctorService.getEditModelByUserId(userId);
	        editDoctorModel.setId(editDoctorModelId.getId());

	        this.doctorService.save(editDoctorModel);

	        return "redirect:/";
	    }

	    @GetMapping("/doctor/picture")
	    public ResponseEntity<EditDoctorPictureModel> getDoctorPicture(Authentication principal) {
	        long userId = ((User) principal.getPrincipal()).getId();
	        EditDoctorPictureModel editDoctorPictureModel = this.doctorService.getPictureModelByUserId(userId);
	        editDoctorPictureModel.setPicturePath("/mm_pics/" + editDoctorPictureModel.getPicturePath());

	        return ResponseEntity.ok(editDoctorPictureModel);
	    }

	    @PostMapping("/doctor/edit-picture")
	    @ResponseBody
	    public String addPictures(MultipartHttpServletRequest request, Authentication principal) {
	        Iterator<String> itr = request.getFileNames();
	        String imageFolderPath = "C:/dabs_mm_pics/doctor_pic/";

	        MultipartFile picture = request.getFile(itr.next());

	        if (picture.isEmpty()) {
	            return "Error";
	        }

	        //this.validateEventPicture(picture);

	        //Generating unique random name for the picture so it wouldn't override other with the same name.
	        UUID uniquePicName = UUID.randomUUID();
	        String imageFormat = FilenameUtils.getExtension(picture.getOriginalFilename());
	        String pictureName = uniquePicName + "." + imageFormat;
	        String filePath = imageFolderPath + pictureName;
	        File dest = new File(filePath);

	        try {
	            picture.transferTo(dest);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        long userId = ((User) principal.getPrincipal()).getId();
	        long doctorId = this.doctorService.getByUserId(userId).getId();

	        EditDoctorPictureModel editDoctorPictureModel = new EditDoctorPictureModel();
	        editDoctorPictureModel.setId(doctorId);
	        editDoctorPictureModel.setPicturePath(pictureName);

	        this.doctorService.savePicture(editDoctorPictureModel);

	        return "Success";
	    }

}
