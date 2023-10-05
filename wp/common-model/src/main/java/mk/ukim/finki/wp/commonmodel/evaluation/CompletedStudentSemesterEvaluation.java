package mk.ukim.finki.wp.commonmodel.evaluation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mk.ukim.finki.wp.commonmodel.base.Semester;
import mk.ukim.finki.wp.commonmodel.base.Student;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class CompletedStudentSemesterEvaluation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Semester semester;

}
