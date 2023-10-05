package mk.ukim.finki.wp.commonmodel.requests;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import mk.ukim.finki.wp.commonmodel.base.Course;
import mk.ukim.finki.wp.commonmodel.base.Professor;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class LateCourseEnrollmentStudentRequest extends StudentRequest {

    @ManyToOne
    private Course course;

    @ManyToOne
    private Professor professor;

    private Boolean professorApproved;

    @Override
    public boolean canBeApproved() {
        return professorApproved;
    }

    public boolean canBeApprovedByProfessor() {
        return professorApproved == null || !professorApproved;
    }
}
