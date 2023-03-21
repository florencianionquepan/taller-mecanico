package com.besysoft.taller.dto;

import javax.validation.constraints.NotNull;

public class ManoObraMecanicoDTO {
    private Long id;
    @NotNull
    private MecanicoDTO mecanico;
}
