package com.example.service.property.controller;

import com.example.service.property.modal.DataPropertie;
import com.example.service.property.modal.DataPropertieDTO;
import com.example.service.property.modal.DataUpdatePropertieDTO;
import com.example.service.property.service.PropertieService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/propertie")
@SecurityRequirement(name = "bearer-key")
public class PropertieController {
    @Autowired
    private PropertieService service;
    @PostMapping("{id}")
    @Transactional
    public ResponseEntity<DataPropertie> register(@RequestBody @Valid DataPropertieDTO dto, @PathVariable("id") Long id, UriComponentsBuilder builder){
        var propertie = this.service.registerPropertie(dto,id);
        var uri = builder.path("propertie/{id}").buildAndExpand(propertie.id()).toUri();
        return ResponseEntity.created(uri).body(propertie);
    }
    @GetMapping("{id}")
    public ResponseEntity<Page<DataPropertie>> list(@PageableDefault(sort = {"id"})Pageable pageable, @PathVariable("id") Long id){
        return ResponseEntity.ok(this.service.listPropertie(pageable,id));
    }
    @GetMapping
    public  ResponseEntity<Page<DataPropertie>> listAll(@PageableDefault(sort = {"id"})Pageable pageable){
        return ResponseEntity.ok(this.service.listPropertieAll(pageable));
    }
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DataPropertie> update (@RequestBody @Valid DataUpdatePropertieDTO dto, @PathVariable("id") Long id){
        var propertie = this.service.updatePropertie(dto,id);
        return ResponseEntity.ok(propertie);
    }
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") Long id){
        this.service.deletePropertie(id);
        return ResponseEntity.noContent().build();
    }
}
