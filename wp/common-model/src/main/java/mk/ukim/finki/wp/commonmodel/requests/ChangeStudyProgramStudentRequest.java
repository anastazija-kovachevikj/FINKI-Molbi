package mk.ukim.finki.wp.commonmodel.requests;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import mk.ukim.finki.wp.commonmodel.base.StudyProgram;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class ChangeStudyProgramStudentRequest extends StudentRequest {

    @ManyToOne
    private StudyProgram newStudyProgram;

    @ManyToOne
    private StudyProgram oldStudyProgram;
}
