package com.example.service.property.controller;

import com.example.service.property.model.DataPropertie;
import com.example.service.property.model.DataPropertieDTO;
import com.example.service.property.model.DataUpdatePropertieDTO;
import com.example.service.property.service.PropertieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/propertie")
@SecurityRequirement(name = "bearer-key")
@CrossOrigin(origins = "http://172.27.64.1:8082")
public class PropertieController {
    @Autowired
    private PropertieService service;
    @PostMapping("{id}")
    @Transactional
    @Operation(summary ="Register a property", description ="Register a new property with the requested information and validate the real client")
    @CacheEvict(value = "Property", allEntries = true)
    public ResponseEntity<DataPropertie> register(@RequestBody @Valid DataPropertieDTO dto, @PathVariable("id") Long id, UriComponentsBuilder builder){
        var propertie = this.service.registerPropertie(dto,id);
        var uri = builder.path("propertie/{id}").buildAndExpand(propertie.id()).toUri();
        return ResponseEntity.created(uri).body(propertie);
    }
    @GetMapping("{id}")
    @Operation(summary ="Specific property search", description ="Search for a specific property and search by id")
    @Cacheable(value = "Property", key = "#id")
    public ResponseEntity<Page<DataPropertie>> list(@PageableDefault(sort = {"id"})Pageable pageable, @PathVariable("id") Long id){
        return ResponseEntity.ok(this.service.listPropertie(pageable,id));
    }
    @GetMapping
    @Operation(summary ="Search all properties", description ="Searches for all properties and returns them in order")
    @Cacheable(value = "Property")
    public  ResponseEntity<Page<DataPropertie>> listAll(@PageableDefault(sort = {"id"})Pageable pageable){
        return ResponseEntity.ok(this.service.listPropertieAll(pageable));
    }
    @PutMapping("{id}")
    @Transactional
    @Operation(summary ="Update property information", description ="Search for the required property and update the desired information")
    @CachePut(value = "Property",key = "#id")
    public ResponseEntity<DataPropertie> update (@RequestBody @Valid DataUpdatePropertieDTO dto, @PathVariable("id") Long id){
        var propertie = this.service.updatePropertie(dto,id);
        return ResponseEntity.ok(propertie);
    }
    @DeleteMapping("{id}")
    @Transactional
    @Operation(summary ="Delete a property", description ="Get the property ID and delete it")
    @CachePut(value = "Property",key = "#id")
    public ResponseEntity delete(@PathVariable("id") Long id){
        this.service.deletePropertie(id);
        return ResponseEntity.noContent().build();
    }
}
