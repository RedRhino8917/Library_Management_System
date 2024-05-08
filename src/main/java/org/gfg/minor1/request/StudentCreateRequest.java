package org.gfg.minor1.request;

import jakarta.persistence.Column;
import lombok.*;
import org.gfg.minor1.model.Student;
import org.gfg.minor1.model.StudentType;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateRequest {
    private String name;
    private String email;
    private String phoneNo;
    private String address;
    private String password;

    // this value will be accessible to those which are actually beans. that is why removing from here
//    @Value("${student.authority}")

    private String authority;

    public Student toStudent() {
        return Student.builder().
                name(this.name).
                email(this.email).
                phoneNo(this.phoneNo).
                address(this.address).
                password(this.password).
                authority(this.authority).
                status(StudentType.ACTIVE).
                build();
    }
}
