package com.app.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Location;
import com.app.model.LocationViewModel;
import com.app.repository.LocationRepository;
import com.app.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
    private LocationRepository settlePointRepository;

    private ModelMapper modelMapper;

    @Autowired
    public LocationServiceImpl(LocationRepository settlePointRepository, ModelMapper modelMapper) {
        this.settlePointRepository = settlePointRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<LocationViewModel> getAll() {
        List<Location> locations = this.settlePointRepository.findAllByOrderByName();
        List<LocationViewModel> locationViewModels = new ArrayList<>();
        for (Location location : locations) {
            LocationViewModel locationViewModel = this.modelMapper.map(location, LocationViewModel.class);
            locationViewModels.add(locationViewModel);
        }

        return locationViewModels;
    }

    @Override
    public Location getById(long id) {
        return this.settlePointRepository.findById(id).orElse(null);
    }
}
