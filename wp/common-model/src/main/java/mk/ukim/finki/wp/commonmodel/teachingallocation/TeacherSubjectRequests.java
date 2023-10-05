package mk.ukim.finki.wp.commonmodel.teachingallocation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mk.ukim.finki.wp.commonmodel.base.Professor;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class TeacherSubjectRequests {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private JoinedSubject subject;

    private Double priority;

    private Boolean preferMultipleGroups;

    private Boolean preferAuditoriumExercises;

    private Boolean preferLabExercises;

    @Column(length = 2000)
    private String schedulePreferences;

    @Column(length = 2000)
    private String note;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TeacherSubjectRequests that = (TeacherSubjectRequests) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
