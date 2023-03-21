package com.besysoft.taller.controller;

import com.besysoft.taller.service.interfaces.IManoObraService;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/manoObras")
public class ManoObraController {

    private final IManoObraService service;

    public ManoObraController(IManoObraService service) {
        this.service = service;
    }

    public Map<String,Object> mensajeBody= new HashMap<>();

}
