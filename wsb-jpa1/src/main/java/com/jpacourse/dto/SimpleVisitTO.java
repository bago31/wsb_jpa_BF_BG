package com.jpacourse.dto;

import java.time.LocalDateTime;
import java.util.Collection;

public class SimpleVisitTO {
    private Long id;
    private String description;
    private LocalDateTime time;
    private Long doctorId;
    private Long patientId;
    private Collection<MedicalTreatmentTO> medicalTreatments;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctor(Long doctor) {
        this.doctorId = doctor;
    }

    public Long getPatient() {
        return patientId;
    }

    public void setPatient(Long patient) {
        this.patientId = patient;
    }

    public Collection<MedicalTreatmentTO> getMedicalTreatments() {
        return medicalTreatments;
    }

    public void setMedicalTreatments(Collection<MedicalTreatmentTO> medicalTreatments) {
        this.medicalTreatments = medicalTreatments;
    }
}
