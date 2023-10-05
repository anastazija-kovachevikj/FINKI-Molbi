package mk.ukim.finki.wp.commonmodel.nns;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TeachingAndScientificMeeting {

    @Id
    private String meetingNumber;

    private LocalDate date;

    private Integer presentMembers;


}
