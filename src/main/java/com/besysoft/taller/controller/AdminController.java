package com.besysoft.taller.controller;

import com.besysoft.taller.dto.AdministrativoDTO;
import com.besysoft.taller.dto.mapper.IAdminMapper;
import com.besysoft.taller.model.Administrativo;
import com.besysoft.taller.service.interfaces.IAdminService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/administrativos")
@Api(value="Administrador Controller", tags="Administrador Controller")
public class AdminController {

    private final IAdminService service;
    private final IAdminMapper mapper;
    public Map<String,Object> mensajeBody= new HashMap<>();

    public AdminController(IAdminService service, IAdminMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> altaAdmin(@RequestBody @Valid AdministrativoDTO dto){
        Administrativo nuevo=this.service.altaAdmin(this.mapper.mapToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.mapper.mapToDto(nuevo));
    }

    @GetMapping
    public ResponseEntity<?> verAdmin(){
        List<AdministrativoDTO> adminis=this.mapper.mapToListDto(this.service.verAdmin());
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",adminis);
        return ResponseEntity.ok(mensajeBody);
    }
}
