package mk.ukim.finki.wp.commonmodel.base;

import jakarta.persistence.Embeddable;

@Embeddable
public class EntityReference {

    private String referenceId;
    private String referenceClass;
}
