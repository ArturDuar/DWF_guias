package sv.edu.udb.repository.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private Long id;
    private String name;
    private String type;
    private Integer releaseYear;
}
