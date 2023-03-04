package com.app.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.entities.Appointment;
import com.app.entities.AppointmentType;
import com.app.exc_handler.AppointmentNotFoundException;
import com.app.model.AppointmentDateViewModel;
import com.app.model.AppointmentViewModel;
import com.app.model.DoctorSelectViewModel;
import com.app.model.PatientBasicViewModel;
import com.app.model.bind.AddAppointmentModel;
import com.app.repository.AppointmentRepository;
import com.app.service.AppointmentService;
import com.app.service.AppointmentTypeService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentRepository appointmentRepository;

    private ModelMapper modelMapper;

    private AppointmentTypeService appointmentTypeService;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, ModelMapper modelMapper,
                                  AppointmentTypeService appointmentTypeService) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
        this.appointmentTypeService = appointmentTypeService;
    }

    @Override
    public void save(AddAppointmentModel addAppointmentModel) {
        Appointment appointment = this.modelMapper.map(addAppointmentModel, Appointment.class);

        AppointmentType appointmentType = this.appointmentTypeService.getById(addAppointmentModel.getType());
        appointment.setAppointmentType(appointmentType);

        this.appointmentRepository.saveAndFlush(appointment);
    }

    @Override
    public List<AppointmentDateViewModel> getAllForDateAndDoctor(Date date, long doctorId) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        Date endDate = cal.getTime();

        List<Appointment> appointments = this.appointmentRepository.findAllBetweenDatesByDoctorId(date, endDate, doctorId);

        List<AppointmentDateViewModel> appointmentDateViewModels = new ArrayList<>();

        for (Appointment appointment : appointments) {
            AppointmentDateViewModel appointmentDateViewModel = this.modelMapper.map(appointment, AppointmentDateViewModel.class);
            appointmentDateViewModels.add(appointmentDateViewModel);
        }

        return appointmentDateViewModels;
    }

    @Override
    public List<AppointmentViewModel> getAllForPatientById(long patientId) {
        List<Appointment> appointments = this.appointmentRepository.findAllByPatientIdOrderByDate(patientId);
        List<AppointmentViewModel> appointmentViewModels = new ArrayList<>();

        for (Appointment appointment : appointments) {
            appointmentViewModels.add(mapAppointmentViewModel(appointment));
        }

        return appointmentViewModels;
    }

    @Override
    public Page<AppointmentViewModel> getAllForPatientById(long patientId, Pageable pageable) {
        Page<Appointment> appointments = this.appointmentRepository.findAllByPatientIdOrderByDate(patientId, pageable);
        List<AppointmentViewModel> appointmentViewModels = new ArrayList<>();

        for (Appointment appointment : appointments) {
            appointmentViewModels.add(mapAppointmentViewModel(appointment));
        }

        return (Page<AppointmentViewModel>) new PageImpl(appointmentViewModels, pageable, appointments.getTotalElements());
    }

    @Override
    public List<AppointmentViewModel> getAllForDoctorById(long doctorId) {
        List<Appointment> appointments = this.appointmentRepository.findAllByDoctorIdOrderByDate(doctorId);
        List<AppointmentViewModel> appointmentViewModels = new ArrayList<>();

        for (Appointment appointment : appointments) {
            appointmentViewModels.add(mapAppointmentViewModel(appointment));
        }

        return appointmentViewModels;
    }

    @Override
    public Page<AppointmentViewModel> getAllForDoctorById(long doctorId, Pageable pageable) {
        Page<Appointment> appointments = this.appointmentRepository.findAllByDoctorIdOrderByDate(doctorId, pageable);
        List<AppointmentViewModel> appointmentViewModels = new ArrayList<>();

        for (Appointment appointment : appointments) {
            appointmentViewModels.add(mapAppointmentViewModel(appointment));
        }

        return (Page<AppointmentViewModel>) new PageImpl(appointmentViewModels, pageable, appointments.getTotalElements());
    }

    @Override
    public AppointmentViewModel getByDateAndDoctorId(Date date, long doctorId) {
        Appointment appointment = this.appointmentRepository.findOneByDateAndDoctorId(date, doctorId);
        if (appointment == null) {
            throw new AppointmentNotFoundException();
        }

        return mapAppointmentViewModel(appointment);
    }

    @Override
    public AppointmentViewModel getById(long id) {
        Appointment appointment = this.appointmentRepository.findById(id).orElse(null);
        if (appointment == null) {
            throw new AppointmentNotFoundException();
        }

        return mapAppointmentViewModel(appointment);
    }

    private AppointmentViewModel mapAppointmentViewModel(Appointment appointment) {
        AppointmentViewModel appointmentViewModel = this.modelMapper.map(appointment, AppointmentViewModel.class);
        PatientBasicViewModel patientBasicViewModel = this.modelMapper.map(appointment.getPatient(), PatientBasicViewModel.class);
        appointmentViewModel.setPatientBasicViewModel(patientBasicViewModel);
        DoctorSelectViewModel doctorSelectViewModel = this.modelMapper.map(appointment.getDoctor(), DoctorSelectViewModel.class);
        appointmentViewModel.setDoctorSelectViewModel(doctorSelectViewModel);
        appointmentViewModel.setType(appointment.getAppointmentType().getName());

        return appointmentViewModel;
    }
}
