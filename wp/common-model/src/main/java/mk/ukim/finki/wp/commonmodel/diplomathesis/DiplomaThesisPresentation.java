package mk.ukim.finki.wp.commonmodel.diplomathesis;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiplomaThesisPresentation {

    private String location;

    private LocalDateTime presentationStartTime;
}
