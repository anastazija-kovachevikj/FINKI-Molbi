package mk.ukim.finki.wp.commonmodel.projects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.wp.commonmodel.base.User;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ScientificProjectMember {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ScientificProject project;

    @ManyToOne
    private User member;
}
