package model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Well {
    private int id;
    private String name;
//    private List<Equipment> equipmentList;

    public Well(String name) {
        this.name = name;
    }
}


