package mk.ukim.finki.wp.commonmodel.evaluation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mk.ukim.finki.wp.commonmodel.base.Course;
import mk.ukim.finki.wp.commonmodel.base.Professor;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class CourseProfessorEvaluation {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Professor professor;

    private short grade;

    @Column(length = 5000)
    private String comment;
}
