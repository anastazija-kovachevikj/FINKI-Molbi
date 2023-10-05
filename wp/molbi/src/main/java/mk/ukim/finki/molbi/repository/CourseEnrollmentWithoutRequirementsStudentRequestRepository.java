package mk.ukim.finki.molbi.repository;

import mk.ukim.finki.molbi.model.requests.CourseEnrollmentWithoutRequirementsStudentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseEnrollmentWithoutRequirementsStudentRequestRepository extends
        JpaRepository<CourseEnrollmentWithoutRequirementsStudentRequest, Long> {
    Long countByRequestSession_Id(Long sessionId);

    Page<CourseEnrollmentWithoutRequirementsStudentRequest> findByRequestSession_Id(Long requestSessionId, Pageable pageable);

    @Query("SELECT req FROM CourseEnrollmentWithoutRequirementsStudentRequest req " +
            "WHERE (req.requestSession.id = :sessionId) " +
            "AND (:approved IS NULL OR req.isApproved = :approved) " +
            "AND (:processed IS NULL OR req.isProcessed = :processed) " +
            "AND (:student IS NULL OR req.student.index LIKE :student) ")
    Page<CourseEnrollmentWithoutRequirementsStudentRequest> findAllFiltered(
            @Param("sessionId") Long sessionId,
            @Param("approved") Boolean approved,
            @Param("processed") Boolean processed,
            @Param("student") String student,

            Pageable pageable
    );
}
