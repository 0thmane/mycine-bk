package io.hitos.mycine.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="CIN_PRM_ROOM")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String abbr;
    private String color;
    @OneToMany(mappedBy = "room")
    private List<Session> lSessions;
}
