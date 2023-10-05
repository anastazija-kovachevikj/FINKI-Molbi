package mk.ukim.finki.molbi.service;

import mk.ukim.finki.molbi.model.dtos.CourseEnrollmentWithoutRequirementsStudentRequestDto;
import mk.ukim.finki.molbi.model.dtos.FilterDto;
import mk.ukim.finki.molbi.model.requests.CourseEnrollmentWithoutRequirementsStudentRequest;
import org.springframework.data.domain.Page;

public interface CourseEnrollmentWithoutRequirementsStudentRequestService {

    Page<CourseEnrollmentWithoutRequirementsStudentRequest> findByRequestSessionWithPagination(Long sessionId, int pageNum, int pageSize);

    Page<CourseEnrollmentWithoutRequirementsStudentRequest> findByRequestSessionFilteredWithPagination(Long sessionId, int pageNum, int pageSize, FilterDto dto);

    CourseEnrollmentWithoutRequirementsStudentRequest findById(Long id);

    void create(CourseEnrollmentWithoutRequirementsStudentRequestDto dto);

    void edit(Long id, CourseEnrollmentWithoutRequirementsStudentRequestDto dto);

    void delete(Long id);

    void updateStatus(Long id, Boolean action, String rejectReason);

    void markAsProcessed(Long id);
    
}
