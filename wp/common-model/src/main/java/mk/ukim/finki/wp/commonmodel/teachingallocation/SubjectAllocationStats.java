package mk.ukim.finki.wp.commonmodel.teachingallocation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class SubjectAllocationStats {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private JoinedSubject subject;

    private Integer numberOfTeachers = 0;

    private Integer numberOfFirstTimeStudents;

    private Integer numberOfReEnrollmentStudents;

    private Integer numberOfGroups;

    private Integer calculatedNumberOfGroups;

    private Integer averageStudentsPerGroup;

    // to be calculated dynamically
    @Deprecated
    private Float coveredLectureGroups;
    @Deprecated
    private Float coveredExerciseGroups;
    @Deprecated
    private Float coveredLabGroups;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SubjectAllocationStats that = (SubjectAllocationStats) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
