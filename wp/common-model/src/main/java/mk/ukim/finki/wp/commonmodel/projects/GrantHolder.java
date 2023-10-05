package mk.ukim.finki.wp.commonmodel.projects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class GrantHolder {

    @Id
    private String id;

    private String name;

    @Column(length = 3_000)
    private String description;
}
