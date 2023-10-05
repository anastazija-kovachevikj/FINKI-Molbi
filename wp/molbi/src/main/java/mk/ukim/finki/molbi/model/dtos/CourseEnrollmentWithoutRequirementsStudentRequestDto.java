package mk.ukim.finki.molbi.model.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseEnrollmentWithoutRequirementsStudentRequestDto {
    private String course;

    private String description;

    private Long requestSession;

    private String student;
}
