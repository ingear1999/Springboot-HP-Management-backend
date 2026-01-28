package com.practice.hospitalmanagement.Controller;

import com.practice.hospitalmanagement.Dto.RequestDto.RequestAdminDto;
import com.practice.hospitalmanagement.Dto.RespondDto.RespondAdminDto;
import com.practice.hospitalmanagement.Service.AdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    // AdminController Contructor to call AdminService
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("saveAdmin")// rerurn and send the data (show everything that has been registered)
    public RespondAdminDto saveAdmin(@RequestBody RequestAdminDto adminDto){
        return adminService.registerAdmin(adminDto);
    }

    @GetMapping("/request{id}") // show the specify request data( example client request id 5 then the getmapping will return id 5)

    public RespondAdminDto getRespondById(@PathVariable int id){
         return adminService.findAdminById(id);
    }



}
