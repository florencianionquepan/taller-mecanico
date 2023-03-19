package com.besysoft.taller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//este DTO lo utiliza OrdenTrabajoDTO
public class ManoObraDTO {
    @NotNull
    private Long id;
    @NotNull
    @NotEmpty
    private String detalle;
    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime duracionHs;
}
