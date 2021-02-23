package upp.demo.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {
    private List<Coordinate> data;
}
