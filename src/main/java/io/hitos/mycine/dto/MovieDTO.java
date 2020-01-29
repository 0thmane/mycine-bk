package io.hitos.mycine.dto;

import io.hitos.mycine.model.Classification;
import io.hitos.mycine.model.Person;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MovieDTO {
    private Long id;
    private String title;
    private ClassificationDTO classification;
    private List<PersonDTO> madeBy;
    private List<PersonDTO> actors;
    private String description;
    private String trailerLink;
}
