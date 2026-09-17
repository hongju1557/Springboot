package com.biz.test.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.biz.test.dto.Dept;
import com.biz.test.dto.Emp;
import com.biz.test.dto.EmpSearchCond;
import com.biz.test.dto.PageResult;
import com.biz.test.mapper.DeptMapper;
import com.biz.test.service.EmpService;

@Controller
public class EmpController {

    private final EmpService empService;
    private final DeptMapper deptMapper;

    public EmpController(
            EmpService empService,
            DeptMapper deptMapper) {

        this.empService = empService;
        this.deptMapper = deptMapper;
    }

    @GetMapping("/emps")
    public String emps(EmpSearchCond cond, Model model) {

        // Service에서 사원 목록 + 전체 개수 + 페이징 처리
        PageResult<Emp> pageResult =
                empService.findPage(cond);

        // 부서 목록은 아직 Mapper 직접 사용
        List<Dept> depts = deptMapper.findAll();

        model.addAttribute(
                "emps",
                pageResult.getContent()
        );

        model.addAttribute("cond", cond);
        model.addAttribute("pageResult", pageResult);
        model.addAttribute("depts", depts);

        return "emp";
    }

    @GetMapping("/emps/{empId}")
    public String detail(
            @PathVariable("empId") String empId,
            Model model) {

        Emp emp = empService.findById(empId);

        model.addAttribute("emp", emp);

        return "emp-detail";
    }

    @GetMapping("/emps/new")
    public String createForm() {

        return "emp-new";
    }

    @PostMapping("/emps/new")
    public String create(Emp emp) {

        empService.insert(emp);

        return "redirect:/emps";
    }
}