package org.gfg.minor1.service;

import org.gfg.minor1.model.Student;
import org.gfg.minor1.repository.StudentRepository;
import org.gfg.minor1.request.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private StudentRepository studentRepository;

    @Value("${admin.authority}")
    private String adminAuthority;

    public Student createAdmin(StudentCreateRequest studentCreateRequest) {
        List<Student> studentList = studentRepository.findByPhoneNo(studentCreateRequest.getPhoneNo());
        Student studentFromDB= null;
        if(studentList == null || studentList.isEmpty()){
            studentCreateRequest.setAuthority(adminAuthority);
            studentFromDB = studentRepository.save(studentCreateRequest.toStudent());
            return studentFromDB;
        }
        studentFromDB = studentList.get(0);
        return studentFromDB;

    }
}
