package ru.stankin.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.stankin.project.service.Service;

import java.io.IOException;
import java.util.Map;

@Controller
public class ViewController {
    @Autowired
    private Service service;

    @GetMapping("/table")
    public String getTablePage(
            Map<String, Object> model,
            @RequestParam(value = "page", defaultValue = "0") int pageNumber
    ) {
        model.put("row", service.getTablePage(pageNumber));
        return "view";
    }

    @GetMapping(path = "/export")
    public ResponseEntity<Resource> exportTable() throws IOException {
        InputStreamResource file = new InputStreamResource(service.exportTable());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=export.csv")
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
}
