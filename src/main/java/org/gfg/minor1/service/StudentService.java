package org.gfg.minor1.service;

import org.gfg.minor1.model.*;
import org.gfg.minor1.repository.StudentRepository;
import org.gfg.minor1.request.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Value("${student.authority}")
    private String studentAuthority;
    public Student createStudent(StudentCreateRequest studentCreateRequest) {
        List<Student> studentList =  studentRepository.findByPhoneNo(studentCreateRequest.getPhoneNo());
        Student studentFromDB = null;
        if (studentList == null || studentList.isEmpty()){
            studentCreateRequest.setAuthority(studentAuthority);
            studentFromDB = studentRepository.save(studentCreateRequest.toStudent());
            return studentFromDB;
        }
        studentFromDB = studentList.get(0);
        return studentFromDB;
    }
    public List<Student> filter(StudentFilterType filterBy, Operator operator, String value) {
        switch (operator) {
            case EQUALS:
                switch (filterBy) {
                    case CONTACT:
                        return studentRepository.findByPhoneNo(value);
                }
            default:
                return new ArrayList<>();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNo) throws UsernameNotFoundException {
        if(! studentRepository.findByPhoneNo(phoneNo).isEmpty()){
            return studentRepository.findByPhoneNo(phoneNo).get(0);
        }
        return null;
    }
}
