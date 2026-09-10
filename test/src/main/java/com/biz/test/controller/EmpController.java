package com.biz.test.controller;

import java.util.List;
import com.biz.test.dto.*;
import com.biz.test.mapper.EmpMapper;
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

    public EmpController(EmpMapper empMapper){
        this.empMapper = empMapper;
    }
    
    @GetMapping("/emps")
    public String emps(EmpSearchCond cond, Model model){

        List<Emp> list = empMapper.selectByCond(cond);

        model.addAttribute("emps",list);
        model.addAttribute("cond", cond);

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
