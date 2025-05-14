package com.jpacourse.persistance.dao;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.entity.*;
import com.jpacourse.persistance.enums.Specialization;
import com.jpacourse.persistance.enums.TreatmentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PatientDaoTest {
    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private MedicalTreatmentDao medicalTreatmentDao;

    @Autowired
    private AddressDao addressDao;

    @Transactional
    @Test
    public void testShouldSaveAndGetPatientWithNewProperty() {
        // given
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity("Sieradz");
        addressEntity.setAddressLine1("Harcerska");
        addressEntity.setPostalCode("98-200");

        final AddressEntity address = addressDao.save(addressEntity);

        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setFirstName("Kuba");
        doctorEntity.setLastName("Piwowar");
        doctorEntity.setTelephoneNumber("123456789");
        doctorEntity.setEmail("test@mail.com");
        doctorEntity.setDoctorNumber("D-1");
        doctorEntity.setAddress(address);
        doctorEntity.setSpecialization(Specialization.OCULIST);

        final DoctorEntity doctor = doctorDao.save(doctorEntity);

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setAddress(address);
        patientEntity.setEmail("testwy@mail.com");
        patientEntity.setPatientNumber("t-12");
        patientEntity.setFirstName("Jan");
        patientEntity.setLastName("Kowalski");
        patientEntity.setIsSmoker(true);
        patientEntity.setTelephoneNumber("123456789");
        patientEntity.setDateOfBirth(LocalDate.of(1990, 5, 15));

        PatientEntity patient = patientDao.save(patientEntity);

        MedicalTreatmentEntity medicalTreatmentEntity = new MedicalTreatmentEntity();
        medicalTreatmentEntity.setDescription("Testowy opis");
        medicalTreatmentEntity.setType(TreatmentType.USG);

        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setTime(LocalDateTime.now());
        visitEntity.setDoctor(doctor);
        visitEntity.setPatient(patient);

        VisitEntity savedVisit = visitDao.save(visitEntity);

        patientDao.save(patient);

        medicalTreatmentEntity.setVisit(savedVisit);
        MedicalTreatmentEntity medicalTreatment = medicalTreatmentDao.save(medicalTreatmentEntity);
        // when
        patientDao.addVisitToPatient(patient.getId(), doctor.getId(),LocalDateTime.of(2025, 5, 20, 10, 0),"Testowy opsi");
        // then

        PatientEntity updatedPatient = patientDao.findOne(patient.getId());
        assertThat(updatedPatient.getVisits()).hasSize(1);

        VisitEntity addedVisit = updatedPatient.getVisits().iterator().next();
        assertThat(addedVisit).isNotNull();
        assertThat(addedVisit.getDescription()).isEqualTo("Testowy opsi");
        assertThat(addedVisit.getDoctor().getId()).isEqualTo(doctor.getId());
        assertThat(addedVisit.getPatient().getId()).isEqualTo(patient.getId());

    }
}
