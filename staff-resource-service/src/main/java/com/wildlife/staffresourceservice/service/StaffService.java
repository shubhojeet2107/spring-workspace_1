
package com.wildlife.staffresourceservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wildlife.staffresourceservice.entity.Staff;
import com.wildlife.staffresourceservice.repository.StaffRepository;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff addStaff(Staff staff) {
        staff.setStaffId(null);
        return staffRepository.save(staff);
    }

    public Staff getStaffById(Long id) {
        List<Staff> staffList = staffRepository.findAll();

        for (Staff staff : staffList) {
            if (staff.getStaffId().equals(id)) {
                return staff;
            }
        }

        return null;
    }

    public Staff updateStaff(Long id, Staff updatedStaff) {
        List<Staff> staffList = staffRepository.findAll();

        for (Staff staff : staffList) {
            if (staff.getStaffId().equals(id)) {
                staff.setStaffName(updatedStaff.getStaffName());
                staff.setRole(updatedStaff.getRole());
                staff.setPhone(updatedStaff.getPhone());
                staff.setEmail(updatedStaff.getEmail());
                staff.setAssignedArea(updatedStaff.getAssignedArea());

                return staffRepository.save(staff);
            }
        }

        return null;
    }

    public boolean deleteStaff(Long id) {
        List<Staff> staffList = staffRepository.findAll();

        for (Staff staff : staffList) {
            if (staff.getStaffId().equals(id)) {
                staffRepository.delete(staff);
                return true;
            }
        }

        return false;
    }
}
