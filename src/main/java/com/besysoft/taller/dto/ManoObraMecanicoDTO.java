package com.besysoft.taller.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ManoObraMecanicoDTO {
    private Long id;
    @NotNull
    private MecanicoDTO mecanico;
}
