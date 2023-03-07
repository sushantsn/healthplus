package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.LogViewModel;
import com.app.service.LogService;

@RestController
@RequestMapping("/log")
public class LogController {
    private LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("")
    public String getLog(Model model, @PageableDefault(size = 8) Pageable pageable) {
        Page<LogViewModel> log = this.logService.getAll(pageable);
        model.addAttribute("log", log);

        return "admin/log";
    }
}
