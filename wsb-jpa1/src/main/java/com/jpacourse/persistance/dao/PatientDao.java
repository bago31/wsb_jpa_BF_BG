package com.jpacourse.persistance.dao;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long>
{
    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String visitDescription);
    List<PatientEntity> findPatientByLastName(String lastName);
    List<PatientEntity> findPatientsWithMoreThanXVisits(long VisitCount);
    List<PatientEntity> findBySmokingStatus(boolean isSmoker);
}