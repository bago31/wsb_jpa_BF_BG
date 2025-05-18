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
import java.util.List;

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
    public void testAddVisitToPatient() {
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

    @Transactional
    @Test
    public void testSearchPatientByLastName() {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setEmail("testwy@mail.com");
        patientEntity.setPatientNumber("t-12");
        patientEntity.setFirstName("Jan");
        patientEntity.setLastName("Kowalski");
        patientEntity.setIsSmoker(true);
        patientEntity.setTelephoneNumber("123456789");
        patientEntity.setDateOfBirth(LocalDate.of(1990, 5, 15));
        patientDao.save(patientEntity);

        List<PatientEntity> result = patientDao.findPatientByLastName("Kowalski");

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getLastName()).isEqualTo("Kowalski");

    }

    @Transactional
    @Test
    public void shouldFindPatientsWithMoreThanXVisits() {
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
        patientDao.addVisitToPatient(patient.getId(), doctor.getId(),LocalDateTime.of(2025, 5, 20, 10, 0),"Testowy opsi");
        patientDao.addVisitToPatient(patient.getId(), doctor.getId(),LocalDateTime.of(2025, 5, 20, 10, 0),"Testowy opsi");

        // when
        List<PatientEntity> result = patientDao.findPatientsWithMoreThanXVisits(2);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.stream()
                .anyMatch(p -> p.getId().equals(patient.getId())))
                .isTrue();
        assertThat(result.stream()
                .filter(p -> p.getId().equals(patient.getId()))
                .findFirst()
                .orElseThrow()
                .getVisits()
                .size()).isGreaterThan(2);

    }

    @Transactional
    @Test
    public void shouldFindOnlySmokers() {
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
        List<PatientEntity> result = patientDao.findBySmokingStatus(true);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(PatientEntity::getIsSmoker);
    }
}
