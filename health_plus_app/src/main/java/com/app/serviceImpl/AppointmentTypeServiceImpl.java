package com.app.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.AppointmentType;
import com.app.model.AppointmentTypeViewModel;
import com.app.repository.AppointmentTypeRepository;
import com.app.service.AppointmentTypeService;

@Service
public class AppointmentTypeServiceImpl implements AppointmentTypeService {
    private AppointmentTypeRepository appointmentTypeRepository;

    private ModelMapper modelMapper;

    @Autowired
    public AppointmentTypeServiceImpl(AppointmentTypeRepository appointmentTypeRepository, ModelMapper modelMapper) {
        this.appointmentTypeRepository = appointmentTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AppointmentTypeViewModel> getAll() {
        List<AppointmentType> appointmentTypes = this.appointmentTypeRepository.findAllByOrderByName();
        List<AppointmentTypeViewModel> appointmentTypeViewModels = new ArrayList<>();
        for (AppointmentType appointmentType : appointmentTypes) {
            AppointmentTypeViewModel appointmentTypeViewModel = this.modelMapper.map(appointmentType, AppointmentTypeViewModel.class);
            appointmentTypeViewModels.add(appointmentTypeViewModel);
        }

        return appointmentTypeViewModels;
    }

    @Override
    public AppointmentType getById(long id) {
        return this.appointmentTypeRepository.findById(id).orElse(null);
    }
}
