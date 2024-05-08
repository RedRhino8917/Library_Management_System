package org.gfg.minor1.controller;

import org.gfg.minor1.exceptions.TxnException;
import org.gfg.minor1.model.Student;
import org.gfg.minor1.request.StudentCreateRequest;
import org.gfg.minor1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/create")
    public Student createAdmin(@RequestBody StudentCreateRequest studentCreateRequest) throws TxnException {
        // put validation, student phone is blank throw an excception
        if(StringUtils.isEmpty(studentCreateRequest.getPhoneNo())){
            throw new TxnException("student phone no can not be null.");
        }
        return adminService.createAdmin(studentCreateRequest);
    }
}
