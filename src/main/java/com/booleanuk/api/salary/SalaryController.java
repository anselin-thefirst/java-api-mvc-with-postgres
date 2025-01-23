package com.booleanuk.api.salary;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("salaries")
public class SalaryController {
    private SalaryRepository salaries;

    public SalaryController() throws SQLException {
        this.salaries = new SalaryRepository();

    }

    @GetMapping
    public List<Salary> getAll() throws SQLException {
        return this.salaries.getAll();
    }

    @GetMapping("/{id}")
    public Salary getOne(@PathVariable (name = "id") int id) throws SQLException {
        Salary salary = this.salaries.get(id);
        if (salary == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return salary;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Salary create(@RequestBody Salary salary) throws SQLException {
        Salary theSalary = this.salaries.add(salary);
        if (theSalary == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create the specified Salary");
        }
        return theSalary;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Salary update(@PathVariable (name = "id") int id, @RequestBody Salary salary) throws SQLException {
        Salary toUpdate = this.salaries.get(id);
        if (toUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return this.salaries.update(id, salary);
    }

    @DeleteMapping("/{id}")
    public Salary delete(@PathVariable (name = "id") int id) throws SQLException {
        Salary toDelete = this.salaries.get(id);
        if (toDelete == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return this.salaries.delete(id);
    }
}
