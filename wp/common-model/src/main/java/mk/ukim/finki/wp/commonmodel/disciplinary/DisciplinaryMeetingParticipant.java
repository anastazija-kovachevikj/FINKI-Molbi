package mk.ukim.finki.wp.commonmodel.disciplinary;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import mk.ukim.finki.wp.commonmodel.base.Professor;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DisciplinaryMeetingParticipant {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Professor professor;

    @ManyToOne
    private DisciplinaryMeeting meeting;

}
