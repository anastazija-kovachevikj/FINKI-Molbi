package mk.ukim.finki.wp.commonmodel.teachingallocation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class TeacherAllocationStats {

    //todo: this should be view
    @Id
    private String id;

    @ManyToOne
    private Professor professor;

    private Float numberOfLectureSubjects;
    private Float numberOfExerciseSubjects;
    private Float numberOfLabSubjects;

    private Float numberOfLectureGroups;
    private Float numberOfExerciseGroups;
    private Float numberOfLabGroups;

    private Float numberOfLectureEnGroups;
    private Float numberOfExerciseEnGroups;

    private Integer totalLectureStudents;
    private Integer totalExerciseStudents;
    private Integer totalLabStudents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TeacherAllocationStats that = (TeacherAllocationStats) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
