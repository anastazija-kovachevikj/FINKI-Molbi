package mk.ukim.finki.wp.commonmodel.requests;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class InstallmentPaymentStudentRequest extends StudentRequest {

    private Integer installmentsNum;
}
