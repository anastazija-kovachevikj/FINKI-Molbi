package mk.ukim.finki.molbi.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.molbi.model.base.Course;
import mk.ukim.finki.molbi.model.base.Student;
import mk.ukim.finki.molbi.model.dtos.CourseEnrollmentWithoutRequirementsStudentRequestDto;
import mk.ukim.finki.molbi.model.dtos.FilterDto;
import mk.ukim.finki.molbi.model.exceptions.*;
import mk.ukim.finki.molbi.model.requests.CourseEnrollmentWithoutRequirementsStudentRequest;
import mk.ukim.finki.molbi.model.requests.RequestSession;
import mk.ukim.finki.molbi.repository.*;
import mk.ukim.finki.molbi.service.CourseEnrollmentWithoutRequirementsStudentRequestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CourseEnrollmentWithoutRequirementsStudentRequestImpl
        implements CourseEnrollmentWithoutRequirementsStudentRequestService {

    private final CourseEnrollmentWithoutRequirementsStudentRequestRepository requestRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final RequestSessionRepository sessionRepository;

    @Transactional
    @Override
    public Page<CourseEnrollmentWithoutRequirementsStudentRequest> findByRequestSessionWithPagination(Long sessionId, int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, Sort.Direction.ASC, "id");
        return requestRepository.findByRequestSession_Id(sessionId, pageRequest);
    }


    @Transactional
    @Override
    public Page<CourseEnrollmentWithoutRequirementsStudentRequest> findByRequestSessionFilteredWithPagination(Long sessionId,
                                                                                                              int pageNum,
                                                                                                              int pageSize,
                                                                                                              FilterDto dto) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, Sort.Direction.ASC, "id");

        return requestRepository.findAllFiltered(sessionId, dto.getApproved(),
                dto.getProcessed(), !dto.getStudent().isEmpty() ? dto.getStudent() : null, pageRequest);

    }


    @Override
    public CourseEnrollmentWithoutRequirementsStudentRequest findById(Long id) {

        return requestRepository.findById(id)
                .orElseThrow(() -> new CourseWithoutRequirementRequestNotFoundException(id));
    }

    @Override
    public void create(CourseEnrollmentWithoutRequirementsStudentRequestDto dto) {
        if (dto.getStudent().endsWith(".") || dto.getCourse().endsWith(".") || dto.getDescription().isBlank()) {
            throw new AllFieldsNotFilledException();
        }

        CourseEnrollmentWithoutRequirementsStudentRequest req = new CourseEnrollmentWithoutRequirementsStudentRequest();

        Student student = studentRepository.findById(dto.getStudent())
                .orElseThrow(() -> new StudentNotFoundException(dto.getStudent()));

        String description = dto.getDescription();

        RequestSession session = sessionRepository.findById(dto.getRequestSession())
                .orElseThrow(() -> new RequestSessionNotFoundException(dto.getRequestSession()));

        Course course = courseRepository.findById(dto.getCourse())
                .orElseThrow(() -> new CourseNotFoundException(dto.getCourse()));

        req.setStudent(student);
        req.setDescription(description);
        req.setRequestSession(session);
        req.setDateCreated(LocalDate.now());
        req.setCourse(course);
        req.setIsProcessed(false);
        requestRepository.save(req);

    }

    @Override
    public void edit(Long id, CourseEnrollmentWithoutRequirementsStudentRequestDto dto) {
        if (dto.getStudent().endsWith(".") || dto.getCourse().endsWith(".") || dto.getDescription().isBlank()) {
            throw new AllFieldsNotFilledException();
        }
        CourseEnrollmentWithoutRequirementsStudentRequest req = findById(id);

        Student student = studentRepository.findById(dto.getStudent())
                .orElseThrow(() -> new StudentNotFoundException(dto.getStudent()));

        Course course = courseRepository.findById(dto.getCourse())
                .orElseThrow(() -> new CourseNotFoundException(dto.getCourse()));

        req.setStudent(student);
        req.setCourse(course);
        req.setDescription(dto.getDescription());
        requestRepository.save(req);
    }

    @Override
    public void delete(Long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public void updateStatus(Long id, Boolean action, String rejectReason) {
        CourseEnrollmentWithoutRequirementsStudentRequest req = findById(id);
        if (!action && !req.canBeRejected()) {
            throw new RequestAlreadyProcessedOrAlreadyRejectedException(req);
        }

        req.setIsApproved(action);
        req.setRejectionReason(rejectReason);
        requestRepository.save(req);


    }

    @Override
    public void markAsProcessed(Long id) {
        CourseEnrollmentWithoutRequirementsStudentRequest req = findById(id);
        if (req.getIsApproved() == null || !req.canBeMarkedAsProcessed()) {
            throw new RequestNotApprovedOrAlreadyProcessedException(req);
        }
        req.setIsProcessed(true);
        req.setDateProcessed(LocalDate.now());
        requestRepository.save(req);
    }

}
