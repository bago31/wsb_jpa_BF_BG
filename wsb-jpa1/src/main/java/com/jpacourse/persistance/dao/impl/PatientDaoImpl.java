package com.jpacourse.persistance.dao.impl;

import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public List<PatientEntity> findPatientByLastName(String lastName) {
        return entityManager.createQuery(
                        "SELECT p FROM PatientEntity p WHERE p.lastName = :lastName", PatientEntity.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsWithMoreThanXVisits(long x) {
        return entityManager.createQuery(
                        "SELECT p FROM PatientEntity p WHERE SIZE(p.visits) > :x", PatientEntity.class)
                .setParameter("x", x)
                .getResultList();
}
    @Override
    public List<PatientEntity> findBySmokingStatus(boolean isSmoker) {
        String jpql = "SELECT p FROM PatientEntity p WHERE p.isSmoker = :isSmoker";
        return entityManager.createQuery(jpql, PatientEntity.class)
                .setParameter("isSmoker", isSmoker)
                .getResultList();
}}
