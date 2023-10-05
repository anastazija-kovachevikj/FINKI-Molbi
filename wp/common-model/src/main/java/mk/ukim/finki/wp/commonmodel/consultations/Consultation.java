package mk.ukim.finki.wp.commonmodel.consultations;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mk.ukim.finki.wp.commonmodel.base.Professor;
import mk.ukim.finki.wp.commonmodel.base.Room;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Consultation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Professor professor;

    @ManyToOne
    private Room room;

    @Enumerated(EnumType.STRING)
    private ConsultationType type;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;
}
