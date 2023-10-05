package mk.ukim.finki.wp.commonmodel.accreditations;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDependencies {

    @Column(length = 5000)
    private String dependencies;
}
