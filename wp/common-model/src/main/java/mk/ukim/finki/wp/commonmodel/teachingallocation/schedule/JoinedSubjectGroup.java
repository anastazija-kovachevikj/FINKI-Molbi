package mk.ukim.finki.wp.commonmodel.teachingallocation.schedule;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mk.ukim.finki.wp.commonmodel.base.Professor;
import mk.ukim.finki.wp.commonmodel.base.Room;
import mk.ukim.finki.wp.commonmodel.base.Semester;
import mk.ukim.finki.wp.commonmodel.teachingallocation.JoinedSubject;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class JoinedSubjectGroup {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Semester semester;

    @ManyToOne
    private JoinedSubject joinedSubject;

    @ManyToOne
    private Professor professor;

    @ManyToOne
    private Professor assistant;

    @ManyToMany
    private List<StudentGroup> studentGroups;

    @ManyToMany
    private List<Room> rooms;

    private Integer numberOfFirstEnrollments;

    private Integer numberOfReEnrollments;
}
