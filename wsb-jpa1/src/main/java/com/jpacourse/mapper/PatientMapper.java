package com.jpacourse.mapper;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.mapper.AddressMapper;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;

import java.util.List;
import java.util.stream.Collectors;

public final class PatientMapper
{

    public static PatientTO mapToTO(final PatientEntity patientEntity)
    {
        if (patientEntity == null)
        {
            return null;
        }
        List<VisitTO> visitTOs = patientEntity.getVisits()
                .stream()
                .map(VisitMapper::mapToTO)
                .toList();


        final PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setAddress(AddressMapper.mapToTO(patientEntity.getAddress()));
        patientTO.setVisits(visitTOs);
        patientTO.setIsSmoker(patientEntity.getIsSmoker());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTO.setEmail(patientEntity.getEmail());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setDateOfBirth(patientEntity.getDateOfBirth());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        return patientTO;
    }

    public static PatientEntity mapToEntity(final PatientTO patientTO)
    {
        if(patientTO == null)
        {
            return null;
        }
        List<VisitEntity> visitEntities = patientTO.getVisits()
                .stream()
                .map(VisitMapper::mapToEntity)
                .toList();

        PatientEntity patientEntity = new PatientEntity();
;       patientEntity.setId(patientTO.getId());
        patientEntity.setAddress(AddressMapper.mapToEntity(patientTO.getAddress()));
        patientEntity.setVisits(visitEntities);
        patientEntity.setIsSmoker(patientTO.getIsSmoker());
        patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        patientEntity.setEmail(patientTO.getEmail());
        patientEntity.setPatientNumber(patientTO.getPatientNumber());
        patientEntity.setDateOfBirth(patientTO.getDateOfBirth());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        return patientEntity;
    }
}
