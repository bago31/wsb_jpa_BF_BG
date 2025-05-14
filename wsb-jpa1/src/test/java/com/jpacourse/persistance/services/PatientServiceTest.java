package com.jpacourse.persistance.services;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.dao.*;
import com.jpacourse.persistance.entity.*;
import com.jpacourse.persistance.enums.Specialization;
import com.jpacourse.persistance.enums.TreatmentType;
import com.jpacourse.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PatientServiceTest
{
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private MedicalTreatmentDao medicalTreatmentDao;
    @Autowired
    private VisitDao visitDao;


    @Transactional
    @Test
    public void testCascadeDelete() {
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

        PatientEntity savedPatient = patientDao.save(patientEntity);

        MedicalTreatmentEntity medicalTreatmentEntity = new MedicalTreatmentEntity();
        medicalTreatmentEntity.setDescription("Testowy opis");
        medicalTreatmentEntity.setType(TreatmentType.USG);

        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setTime(LocalDateTime.now());
        visitEntity.setDoctor(doctor);
        visitEntity.setPatient(savedPatient);

        VisitEntity savedVisit = visitDao.save(visitEntity);

        savedPatient.getVisits().add(savedVisit);

        patientDao.save(savedPatient);

        medicalTreatmentEntity.setVisit(savedVisit);
        MedicalTreatmentEntity medicalTreatment = medicalTreatmentDao.save(medicalTreatmentEntity);

        // when
        patientService.deletePatientById(patientEntity.getId());
        // then
        PatientEntity deletedPatient = patientDao.findOne(patientEntity.getId());
        assertThat(deletedPatient).isNull();

        VisitEntity deletedVisit = visitDao.findOne(visitEntity.getId());
        assertThat(deletedVisit).isNull();

        DoctorEntity doctorAfterDelete = doctorDao.findOne(doctorEntity.getId());
        assertThat(doctorAfterDelete).isNotNull();
    }

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

        PatientEntity savedPatient = patientDao.save(patientEntity);

        MedicalTreatmentEntity medicalTreatmentEntity = new MedicalTreatmentEntity();
        medicalTreatmentEntity.setDescription("Testowy opis");
        medicalTreatmentEntity.setType(TreatmentType.USG);

        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setTime(LocalDateTime.now());
        visitEntity.setDoctor(doctor);
        visitEntity.setPatient(savedPatient);

        VisitEntity savedVisit = visitDao.save(visitEntity);

        savedPatient.getVisits().add(savedVisit);

        patientDao.save(savedPatient);

        medicalTreatmentEntity.setVisit(savedVisit);
        MedicalTreatmentEntity medicalTreatment = medicalTreatmentDao.save(medicalTreatmentEntity);
        // when
       final PatientTO patient = patientService.findById(patientEntity.getId());
        // then
//        assertThat(patient.getIsSmoker()).isEqualTo(true);
        assertThat(patient).isNotNull();

    }


}
