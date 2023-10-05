package mk.ukim.finki.wp.commonmodel.examschedule;

import jakarta.persistence.*;
import lombok.*;
import mk.ukim.finki.wp.commonmodel.accreditations.StudyCycle;
import mk.ukim.finki.wp.commonmodel.base.Semester;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SemesterExamSession {

    // 2022-23-JUNE
    @Id
    private String name;

    @Enumerated(EnumType.STRING)
    private ExamSession session;

    @Deprecated
    @ManyToOne
    private Semester semester;

    // 2022-23
    private String year;

    private LocalDate start;

    private LocalDate end;

    private LocalDate enrollmentStartDate;

    private LocalDate enrollmentEndDate;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<StudyCycle> cycle;
}
