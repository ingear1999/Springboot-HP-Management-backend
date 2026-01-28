package com.practice.hospitalmanagement.Service;


import com.practice.hospitalmanagement.Dto.RequestDto.RequestAdminDto;
import com.practice.hospitalmanagement.Dto.RespondDto.RespondAdminDto;
import com.practice.hospitalmanagement.Entity.usersEntity.Admin;
import com.practice.hospitalmanagement.Repository.AdminRepositoryImpl;

import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepositoryImpl adminRepositoryImpl;


    public AdminService(AdminRepositoryImpl adminRepositoryImpl) {
        this.adminRepositoryImpl = adminRepositoryImpl;
    }

    public RespondAdminDto registerAdmin(RequestAdminDto admin){
        Admin adminEntity = new Admin();

        // Convert DTO TO ENTITY
        adminEntity.setUserName(admin.getUsername());
        adminEntity.setPassword(admin.getPassword());
        adminEntity.setEmails(admin.getEmail());

        //Save TO Respository
        Admin saved = adminRepositoryImpl.save(adminEntity); // saved from admin that declare in jap as Admin

          // Convert ENTITY TO DTO BY RespondAdmin
        return new RespondAdminDto(
                saved.getId(), // Admin save!!
                saved.getUserName(),
                saved.getEmails()
        );
    }

    public RespondAdminDto findAdminById(int id) {
        return adminRepositoryImpl.findById(id)
                .map(Admin -> new RespondAdminDto(
                        Admin.getId(),
                        Admin.getUserName(),
                        Admin.getEmails()
                ))
                .orElse(null);
    }
}
