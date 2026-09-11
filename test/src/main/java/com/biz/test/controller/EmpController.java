package com.biz.test.controller;

import java.util.List;
import com.biz.test.dto.*;
import com.biz.test.mapper.DeptMapper;
import com.biz.test.mapper.EmpMapper;
import com.biz.test.mapper.DeptMapper;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller 
public class EmpController {

    private final EmpMapper empMapper;
    private final DeptMapper deptMapper;

    public EmpController(EmpMapper empMapper,
                     DeptMapper deptMapper) {

    this.empMapper = empMapper;
    this.deptMapper = deptMapper;
    }
@GetMapping("/emps")
public String emps(EmpSearchCond cond, Model model) {

    List<Emp> list = empMapper.selectByCond(cond);

    int totalCount = empMapper.countByCond(cond);

    PageResult<Emp> pageResult =
            new PageResult<>(
                    list,
                    totalCount,
                    cond.getPage(),
                    cond.getPageSize()
            );

    List<Dept> depts = deptMapper.findAll();

    model.addAttribute("emps", list);
    model.addAttribute("cond", cond);
    model.addAttribute("pageResult", pageResult);
    model.addAttribute("depts", depts);

    return "emp";
}

    @GetMapping("/emps/{empId}")
    public String detail(@PathVariable("empId") String empId, Model model ) {
        Emp emp = empMapper.findById(empId);

        model.addAttribute("emp", emp);

        return "emp-detail";
    }
    @GetMapping("/emps/new")
    public String createForm() {
        return "emp-new";
}

@PostMapping("/emps/new")
public String create(Emp emp) {

    empMapper.insert(emp);

    return "redirect:/emps";
}


    
    
}
