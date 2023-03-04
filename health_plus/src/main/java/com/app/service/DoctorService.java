package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.entities.Doctor;
import com.app.model.DoctorSelectViewModel;
import com.app.model.DoctorViewModel;
import com.app.model.bind.DoctorRegistrationModel;
import com.app.model.bind.EditDoctorModel;
import com.app.model.bind.EditDoctorPictureModel;

public interface DoctorService {
    void create(DoctorRegistrationModel registrationModel);

    void save(EditDoctorModel editDoctorModel);

    void savePicture(EditDoctorPictureModel editDoctorPictureModel);

    Doctor getById(long id);

    DoctorViewModel getViewModelById(long id);

    EditDoctorPictureModel getPictureModelByUserId(long id);

    Doctor getByUserId(long userId);

    EditDoctorModel getEditModelByUserId(long userId);

    DoctorSelectViewModel getModelByUserId(long userId);

    List<DoctorSelectViewModel> getAllSelect();

    Page<DoctorViewModel> getAll(Pageable pageable);

	List<Doctor> getAllDoctorDetails();
}
