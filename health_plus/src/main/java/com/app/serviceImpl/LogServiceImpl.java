package com.app.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.entities.Log;
import com.app.model.LogViewModel;
import com.app.repository.LogRepository;
import com.app.service.LogService;

@Service
public class LogServiceImpl implements LogService {
    private LogRepository logRepository;

    private ModelMapper modelMapper;

    @Autowired
    public LogServiceImpl(LogRepository logRepository, ModelMapper modelMapper) {
        this.logRepository = logRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(Log log) {
        this.logRepository.saveAndFlush(log);
    }

    @Override
    public Page<LogViewModel> getAll(Pageable pageable) {
        Page<Log> log = this.logRepository.findAll(pageable);
        List<LogViewModel> logViewModel = new ArrayList<>();
        for (Log aLog : log) {
            LogViewModel aLogViewModel = this.modelMapper.map(aLog, LogViewModel.class);
            logViewModel.add(aLogViewModel);
        }

        return (Page<LogViewModel>) new PageImpl(logViewModel, pageable, log.getTotalElements());
    }
}
