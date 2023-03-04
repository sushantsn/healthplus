package com.app.service;

import java.util.List;

import com.app.entities.Location;
import com.app.model.LocationViewModel;


public interface LocationService {
    List<LocationViewModel> getAll();

    Location getById(long id);
}
