package mk.ukim.finki.molbi.web;


import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import mk.ukim.finki.molbi.model.dtos.CourseEnrollmentWithoutRequirementsStudentRequestDto;
import mk.ukim.finki.molbi.model.dtos.FilterDto;
import mk.ukim.finki.molbi.model.exceptions.AllFieldsNotFilledException;
import mk.ukim.finki.molbi.model.exceptions.RequestAlreadyProcessedOrAlreadyRejectedException;
import mk.ukim.finki.molbi.model.requests.CourseEnrollmentWithoutRequirementsStudentRequest;
import mk.ukim.finki.molbi.model.requests.RequestSession;
import mk.ukim.finki.molbi.service.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/COURSE_ENROLLMENT_WITHOUT_REQUIREMENTS")
@AllArgsConstructor
public class CourseEnrollmentWithoutRequirementController {


    private final CourseEnrollmentWithoutRequirementsStudentRequestService requestService;
    private final RequestSessionService requestSessionService;
    private final CourseService courseService;
    private final ProfessorService professorService;

    @GetMapping("/{sessionId}")
    public String showRequests(@PathVariable Long sessionId,
                               @RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer results,
                               @ModelAttribute FilterDto dto,
                               Model model) {

        Page<CourseEnrollmentWithoutRequirementsStudentRequest> page =
                requestService.findByRequestSessionWithPagination(sessionId, pageNum, results);
        if (dto.getApproved() != null || dto.getProcessed() != null ||
                dto.getStudent() != null) {
            page = requestService.findByRequestSessionFilteredWithPagination(sessionId, pageNum, results, dto);
        }

        model.addAttribute("page", page);
        model.addAttribute("sessionId", sessionId);
        model.addAttribute("professors", professorService.listAll());
        model.addAttribute("filterDto", dto);

        return "cwr/list-cwr";
    }

    @GetMapping({"/{sessionId}/apply", "/{sessionId}/edit/{id}"})
    public String showFormPage(@PathVariable(required = false) Long sessionId,
                               @PathVariable(required = false) Long id,
                               Model model,
                               @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        if (id != null) {
            CourseEnrollmentWithoutRequirementsStudentRequest request = requestService.findById(id);
            model.addAttribute("request", request);
        }
        RequestSession requestSession = requestSessionService.findById(sessionId);
        model.addAttribute("courses", courseService.listBySemester(requestSession.getSemester()));
        model.addAttribute("professors", professorService.listAll());
        model.addAttribute("sessionId", sessionId);

        return "cwr/form-cwr";
    }


    @PostMapping("/apply")
    public String applyForRequest(@ModelAttribute CourseEnrollmentWithoutRequirementsStudentRequestDto dto,
                                  @RequestParam(required = false) Long id) {
        try {
            if (id != null) {
                requestService.edit(id, dto);
            } else {
                requestService.create(dto);
            }
            return "redirect:/COURSE_ENROLLMENT_WITHOUT_REQUIREMENTS/" + dto.getRequestSession();
        } catch (RuntimeException e) {
            if (id != null) {
                return "redirect:/COURSE_ENROLLMENT_WITHOUT_REQUIREMENTS/" + dto.getRequestSession() + "/edit/" + id + "?error=" + e.getMessage();
            } else {
                return "redirect:/COURSE_ENROLLMENT_WITHOUT_REQUIREMENTS/" + dto.getRequestSession() + "/apply?error=" + e.getMessage();
            }
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteRequest(@PathVariable Long id) {
        CourseEnrollmentWithoutRequirementsStudentRequest request = requestService.findById(id);
        requestService.delete(id);
        return "redirect:/COURSE_ENROLLMENT_WITHOUT_REQUIREMENTS/" + request.getRequestSession().getId();
    }

    @PostMapping("/updateStatus/{id}")
    public String updateRequestStatus(@PathVariable Long id,
                                      @RequestParam Boolean action,
                                      @RequestParam(required = false) String rejectionReason,
                                      HttpSession session) {
        if (!action && rejectionReason.isEmpty()) {
            return "redirect:/COURSE_ENROLLMENT_WITHOUT_REQUIREMENTS/details/{id}?error=" + new AllFieldsNotFilledException().getMessage();
        }
        CourseEnrollmentWithoutRequirementsStudentRequest request = requestService.findById(id);
        requestService.updateStatus(id, action, rejectionReason);
        session.setAttribute("rejectionReason", rejectionReason);
        return "redirect:/COURSE_ENROLLMENT_WITHOUT_REQUIREMENTS/" + request.getRequestSession().getId();
    }

    @PostMapping("/process/{id}")
    public String markAsProcessedRequest(@PathVariable Long id) {
        CourseEnrollmentWithoutRequirementsStudentRequest request = requestService.findById(id);
        requestService.markAsProcessed(id);
        return "redirect:/COURSE_ENROLLMENT_WITHOUT_REQUIREMENTS/" + request.getRequestSession().getId();
    }

    @GetMapping("/details/{id}")
    public String showDetailsPage(@PathVariable Long id,
                                  Model model,
                                  @RequestParam(required = false) boolean reject,
                                  @RequestParam(required = false) String error,
                                  HttpSession session) {
        CourseEnrollmentWithoutRequirementsStudentRequest request = requestService.findById(id);
        if (request.getIsApproved() != null && !request.getIsApproved() && reject) {
            throw new RequestAlreadyProcessedOrAlreadyRejectedException(request);
        }
        if (error != null) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("reject", reject);
        model.addAttribute("rejectionReason", session.getAttribute("rejectionReason"));


        model.addAttribute("request", request);
        return "cwr/details-cwr";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleExceptions(RuntimeException ex, Model model) {
        model.addAttribute("exceptionMessage", ex.getMessage());
        return "error";
    }

}
