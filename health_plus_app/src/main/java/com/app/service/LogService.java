package com.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.entities.Log;
import com.app.model.LogViewModel;


public interface LogService {
    void save(Log log);

    Page<LogViewModel> getAll(Pageable pageable);
}
