package mk.ukim.finki.wp.commonmodel.teachingallocation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mk.ukim.finki.wp.commonmodel.base.SemesterType;
import mk.ukim.finki.wp.commonmodel.base.Subject;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class JoinedSubject {

    @Id
    private String abbreviation;

    @Column(length = 1000)
    private String name;

    private String codes;

    @Enumerated(EnumType.STRING)
    private SemesterType semesterType;

    @ManyToOne
    private Subject mainSubject;


    private Integer weeklyLecturesClasses;

    private Integer weeklyAuditoriumClasses;

    private Integer weeklyLabClasses;
}
