package ru.stankin.project.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.stankin.project.persistence.entity.Entity;
import ru.stankin.project.persistence.repository.Repository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private Repository repository;

    public List<Entity> getTablePage(int pageNum) {
        return repository.findAll(pageNum);
    }

    public ByteArrayInputStream exportTable() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.EXCEL);
        csvPrinter.printRecord("SEP=,");
        csvPrinter.printRecord(List.of("id", "model", "speed", "service_date"));
        for (Entity e : repository.findAll()) {
            csvPrinter.printRecord(List.of(e.getId(), e.getModel(), e.getSpeed(), e.getServiceDate().toString()));
        }
        csvPrinter.flush();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
