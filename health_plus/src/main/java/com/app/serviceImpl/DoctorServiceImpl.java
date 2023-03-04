package com.app.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.entities.Doctor;
import com.app.entities.Location;
import com.app.entities.User;
import com.app.entities.WeekSchedule;
import com.app.model.DoctorSelectViewModel;
import com.app.model.DoctorViewModel;
import com.app.model.bind.DoctorRegistrationModel;
import com.app.model.bind.EditDoctorModel;
import com.app.model.bind.EditDoctorPictureModel;
import com.app.model.bind.UserRegistrationModel;
import com.app.repository.DoctorRepository;
import com.app.service.DoctorService;
import com.app.service.LocationService;
import com.app.service.UserService;
import com.app.service.WeekScheduleService;

@Service
public class DoctorServiceImpl implements DoctorService {
    private DoctorRepository doctorRepository;

    private ModelMapper modelMapper;

    private UserService userService;

    private LocationService locationService;

    private WeekScheduleService weekScheduleService;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, ModelMapper modelMapper,
                             UserService userService, LocationService locationService,
                             WeekScheduleService weekScheduleService) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.locationService = locationService;
        this.weekScheduleService = weekScheduleService;
    }

    @Override
    public void create(DoctorRegistrationModel registrationModel) {
        User user = this.createDoctorUser(registrationModel);
        Location location = this.locationService.getById(registrationModel.getLocationPoint());
        WeekSchedule weekSchedule = this.weekScheduleService.createDefault();

        Doctor doctor = this.modelMapper.map(registrationModel, Doctor.class);
        doctor.setUser(user);
        doctor.setLocationPoint(location);
        doctor.setWeekSchedule(weekSchedule);


        this.doctorRepository.saveAndFlush(doctor);
    }

    @Override
    public void save(EditDoctorModel editDoctorModel) {
        Doctor currentDoctor = this.doctorRepository.findById(editDoctorModel.getId()).orElse(null);
        Doctor doctor = this.modelMapper.map(editDoctorModel, Doctor.class);

        doctor.setWeekSchedule(currentDoctor.getWeekSchedule());
        doctor.setPatients(currentDoctor.getPatients());
        doctor.setUser(currentDoctor.getUser());
        doctor.setRating(currentDoctor.getRating());
        doctor.setPicturePath(currentDoctor.getPicturePath());

        this.doctorRepository.saveAndFlush(doctor);
    }

    @Override
    public void savePicture(EditDoctorPictureModel editDoctorPictureModel) {
        Doctor doctor = this.doctorRepository.findById(editDoctorPictureModel.getId()).orElse(null);
        doctor.setPicturePath(editDoctorPictureModel.getPicturePath());

        this.doctorRepository.saveAndFlush(doctor);
    }

    @Override
    public Doctor getById(long id) {
        return this.doctorRepository.getReferenceById(id);
    }

    @Override
    public DoctorViewModel getViewModelById(long id) {
        Doctor doctor = this.doctorRepository.getReferenceById(id);

        return this.modelMapper.map(doctor, DoctorViewModel.class);
    }

    @Override
    public EditDoctorPictureModel getPictureModelByUserId(long id) {
        Doctor doctor = this.doctorRepository.findOneByUserId(id);

        return this.modelMapper.map(doctor, EditDoctorPictureModel.class);
    }

    @Override
    public List<DoctorSelectViewModel> getAllSelect() {
        List<Doctor> doctors = this.doctorRepository.findAllByOrderByFirstNameAscLastName();
        List<DoctorSelectViewModel> doctorSelectViewModels = new ArrayList<>();
        for (Doctor doctor : doctors) {
            DoctorSelectViewModel doctorSelectViewModel = this.modelMapper.map(doctor, DoctorSelectViewModel.class);
            doctorSelectViewModels.add(doctorSelectViewModel);
        }

        return doctorSelectViewModels;
    }

    @Override
    public Doctor getByUserId(long userId) {
        return this.doctorRepository.findOneByUserId(userId);
    }


    @Override
    public EditDoctorModel getEditModelByUserId(long userId) {
        Doctor doctor = this.doctorRepository.findOneByUserId(userId);

        return this.modelMapper.map(doctor, EditDoctorModel.class);
    }

    @Override
    public DoctorSelectViewModel getModelByUserId(long userId) {
        Doctor doctor = this.doctorRepository.findOneByUserId(userId);

        return this.modelMapper.map(doctor, DoctorSelectViewModel.class);
    }

    @Override
    public Page<DoctorViewModel> getAll(Pageable pageable) {
        Page<Doctor> doctors = this.doctorRepository.findAll(pageable);
        List<DoctorViewModel> doctorViewModels = new ArrayList<>();
        for (Doctor doctor : doctors) {
            DoctorViewModel doctorViewModel = this.modelMapper.map(doctor, DoctorViewModel.class);
            doctorViewModels.add(doctorViewModel);
        }

        return (Page<DoctorViewModel>) new PageImpl(doctorViewModels, pageable, doctors.getTotalElements());
    }

    private User createDoctorUser(DoctorRegistrationModel registrationModel) {
        UserRegistrationModel userRegistrationModel = this.modelMapper.map(registrationModel, UserRegistrationModel.class);
        String DEFAULT_DOCTOR_ROLE = "ROLE_DOCTOR";
        userRegistrationModel.setAdditionalRole(DEFAULT_DOCTOR_ROLE);
        return this.userService.addUserDetails(userRegistrationModel);
    }

	@Override
	public List<Doctor> getAllDoctorDetails() {
		// TODO Auto-generated method stub
		return doctorRepository.findAll();
	}
}
