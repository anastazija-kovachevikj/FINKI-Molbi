package mk.ukim.finki.wp.commonmodel.teachingallocation;

import jakarta.persistence.*;
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
public class CoursePreference {

    @Id
    @Column(name = "subject_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "subject_id",insertable = false,updatable = false)
    private JoinedSubject subject;

    @Enumerated(EnumType.STRING)
    private LectureSharing lectureSharing;

    @Enumerated(EnumType.STRING)
    private LectureSharing auditoriumExercisesSharing;

    private boolean preferOnlineLectures;

    private boolean preferOnlineExercises;

    private boolean labExercisesAsConsultations;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CoursePreference that = (CoursePreference) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
