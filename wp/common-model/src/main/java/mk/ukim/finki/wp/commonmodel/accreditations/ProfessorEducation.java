package mk.ukim.finki.wp.commonmodel.accreditations;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProfessorEducation {
    @Id
    private String id;

    @ManyToOne
    private ProfessorDetails professor;

    @ManyToOne
    private Education education;

    private Float displayOrder;
}
