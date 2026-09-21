package com.biz.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PathVariable;

import com.biz.test.dto.Dept;
import com.biz.test.service.DeptService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DeptController {

    private final DeptService deptService;

    // 부서 목록 조회
    @GetMapping("/depts")
    public String list(Model model) {

        model.addAttribute("depts", deptService.findAll());

        return "depts";
    }

    // 부서 등록
@PostMapping("/depts")
public String create(
        Dept dept,
        RedirectAttributes redirectAttributes) {

    // 부서 코드 확인
    if (dept.getDeptId() == null
            || dept.getDeptId().isBlank()
            || dept.getDeptId().length() > 2) {

        redirectAttributes.addFlashAttribute(
                "error", "부서 코드는 1~2글자로 입력하세요."
        );
        return "redirect:/depts";
    }

    // 부서명 확인
    if (dept.getDeptTitle() == null
            || dept.getDeptTitle().isBlank()
            || dept.getDeptTitle().length() > 35) {

        redirectAttributes.addFlashAttribute(
                "error", "부서명은 1~35글자로 입력하세요."
        );
        return "redirect:/depts";
    }

    // 위치 코드 확인
    if (dept.getLocationId() == null
            || dept.getLocationId().isBlank()
            || dept.getLocationId().length() > 2) {

        redirectAttributes.addFlashAttribute(
                "error", "위치 코드는 1~2글자로 입력하세요."
        );
        return "redirect:/depts";
    }

    try {
        deptService.save(dept);

        redirectAttributes.addFlashAttribute(
                "msg", "부서가 등록되었습니다."
        );

    } catch (DuplicateKeyException e) {
        redirectAttributes.addFlashAttribute(
                "error", "이미 등록된 부서 코드입니다."
        );

    } catch (DataIntegrityViolationException e) {
        redirectAttributes.addFlashAttribute(
                "error",
                "등록할 수 없는 값입니다. 위치 코드와 입력 내용을 확인하세요."
        );
    }

    return "redirect:/depts";
}

@PostMapping("/depts/{deptId}/delete")
public String delete(
        @PathVariable("deptId") String deptId,
        RedirectAttributes redirectAttributes) {

    try {
        deptService.delete(deptId);

        redirectAttributes.addFlashAttribute(
                "msg", "부서가 삭제되었습니다."
        );

    } catch (IllegalStateException | IllegalArgumentException e) {
        redirectAttributes.addFlashAttribute(
                "error", e.getMessage()
        );

    } catch (DataIntegrityViolationException e) {
        redirectAttributes.addFlashAttribute(
                "error",
                "다른 데이터에서 사용 중인 부서는 삭제할 수 없습니다."
        );
    }

    return "redirect:/depts";
}
}
