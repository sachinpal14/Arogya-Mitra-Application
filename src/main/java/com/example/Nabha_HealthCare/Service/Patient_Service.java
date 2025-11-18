package com.example.Nabha_HealthCare.Service;

import com.example.Nabha_HealthCare.Entity.Patient;
import com.example.Nabha_HealthCare.Repositories.Patient_Repo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Patient_Service {

    @Autowired
    private Patient_Repo patientRepository;

    public Patient addPatient(Patient p) {
        return patientRepository.save(p);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient updatePatient(Long id, Patient p) {
        Patient existing = getPatientById(id);
        existing.setName(p.getName());
        existing.setGender(p.getGender());
        existing.setEmail(p.getEmail());
        existing.setPassword(p.getPassword());
        existing.setDisease(p.getDisease());
        existing.setAge(p.getAge());
        existing.setHospital(p.getHospital());
        return patientRepository.save(existing);
    }

    public void dischargePatient(Long id) {
        patientRepository.deleteById(Math.toIntExact(id));
    }

    public List<Patient> getPatientsByHospital(Long hospitalId) {
        return patientRepository.findByHospital_HospitalId(hospitalId);
    }

    public List<Patient> getPatientByName(String name) {
        return patientRepository.findByNameContainingIgnoreCase(name);
    }
}
