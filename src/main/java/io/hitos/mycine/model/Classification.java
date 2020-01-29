package io.hitos.mycine.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "CIN_PRM_CLASSIFICATION")
public class Classification {
    @Id
    private Long id;
    private String name;
}
