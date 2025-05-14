package com.jpacourse.persistance.dao.impl;

import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao
{

    @Override
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String visitDescription) {
        PatientEntity patient = entityManager.find(PatientEntity.class, doctorId);;
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);

        VisitEntity visit = new VisitEntity();
        visit.setTime(visitTime);
        visit.setDescription(visitDescription);
        visit.setDoctor(doctor);
        visit.setPatient(patient);

        patient.getVisits().add(visit);

        entityManager.merge(patient);
    }
}
