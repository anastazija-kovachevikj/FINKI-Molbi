package mk.ukim.finki.wp.commonmodel.teachingallocation.schedule;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mk.ukim.finki.wp.commonmodel.base.Semester;
import mk.ukim.finki.wp.commonmodel.base.StudyProgram;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class StudentGroup {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Short studyYear;

    private String lastNameRegex;

    @ManyToOne
    private Semester semester;


    @ManyToOne
    private StudyProgram program;


}
