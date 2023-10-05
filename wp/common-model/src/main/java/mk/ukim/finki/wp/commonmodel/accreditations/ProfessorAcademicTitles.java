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
public class ProfessorAcademicTitles {

    @Id
    private String id;

    @ManyToOne
    private ProfessorDetails professor;

    @ManyToOne
    private AcademicTitle academicTitle;
}
