package com.besysoft.taller.controller;

import com.besysoft.taller.dto.AdministrativoDTO;
import com.besysoft.taller.dto.mapper.IAdminMapper;
import com.besysoft.taller.model.Administrativo;
import com.besysoft.taller.service.interfaces.IAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administrativos")
public class AdminController {

    private final IAdminService service;
    private final IAdminMapper mapper;

    public AdminController(IAdminService service, IAdminMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> altaAdmin(@RequestBody AdministrativoDTO dto){
        Administrativo nuevo=this.service.altaAdmin(this.mapper.mapToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.mapper.mapToDto(nuevo));
    }
}
