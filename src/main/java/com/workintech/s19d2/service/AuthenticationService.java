package com.workintech.s19d2.service;

import com.workintech.s19d2.dto.RegisterResponse;
import com.workintech.s19d2.dto.RegistrationMember;
import com.workintech.s19d2.entity.Member;
import com.workintech.s19d2.entity.Role;
import com.workintech.s19d2.repository.MemberRepository;
import com.workintech.s19d2.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class AuthenticationService {

    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;



    public Member register(String email, String password) {

        Optional<Member> registerOptional = memberRepository.findByEmail(email);
        if(registerOptional.isPresent())
            throw new IllegalArgumentException("User with given email already exist");


        List<Role> roles = new ArrayList<>();

       // addRoleUser(roles);
        addRoleAdmin(roles);

        Member member = new Member();
        member.setEmail(email);
        String encodedPassword = passwordEncoder.encode(password);
        member.setPassword(encodedPassword);
        member.setRoles(roles);


        return memberRepository.save(member);
    }

    private void addRoleAdmin(List<Role> roles) {
        Optional<Role> roleAdmin = roleRepository.findByAuthority(ROLE_ADMIN);
        if (!roleAdmin.isPresent()) {
            Role roleAdminEntity = new Role();
            roleAdminEntity.setAuthority(ROLE_ADMIN);
            roles.add(roleRepository.save(roleAdminEntity));
        } else {
            roles.add(roleAdmin.get());
        }
    }

    private void addRoleUser(List<Role> roles) {
        Optional<Role> roleUser = roleRepository.findByAuthority(ROLE_USER);
        if (!roleUser.isPresent()) {
            Role roleUserEntity = new Role();
            roleUserEntity.setAuthority(ROLE_USER);
            roles.add(roleRepository.save(roleUserEntity));
        } else {
            roles.add(roleUser.get());
        }
    }

}
