package com.jpacourse.mapper;

import com.jpacourse.dto.*;
import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.persistance.entity.MedicalTreatmentEntity;
import com.jpacourse.persistance.entity.VisitEntity;

import java.util.List;

public final class VisitMapper
{

    public static VisitTO mapToTO(final VisitEntity visitEntity)
    {
        if (visitEntity == null)
        {
            return null;
        }

        List<MedicalTreatmentTO> medicalTreatmentTOs = visitEntity.getMedicalTreatments()
                .stream()
                .map(MedicalTreatmentMapper::mapToTO)
                .toList();

        final VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setTime(visitEntity.getTime());
        visitTO.setDoctor(DoctorMapper.mapToTO(visitEntity.getDoctor()));
        visitTO.setPatient(PatientMapper.mapToTO(visitEntity.getPatient()));
        visitTO.setMedicalTreatments(medicalTreatmentTOs);
        return visitTO;
    }

    public static SimpleVisitTO mapToSimpleTo(final VisitEntity visitEntity) {
        if (visitEntity == null)
        {
            return null;
        }

        List<MedicalTreatmentTO> medicalTreatmentTOs = visitEntity.getMedicalTreatments()
                .stream()
                .map(MedicalTreatmentMapper::mapToTO)
                .toList();

        final SimpleVisitTO visitTO = new SimpleVisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setTime(visitEntity.getTime());
        visitTO.setDoctor(visitEntity.getDoctor().getId());
        visitTO.setPatient(visitEntity.getPatient().getId());
        visitTO.setMedicalTreatments(medicalTreatmentTOs);
        return visitTO;
    }

    public static VisitEntity mapToEntity(final VisitTO visitTO)
    {
        if(visitTO == null)
        {
            return null;
        }
        List<MedicalTreatmentEntity> medicalTreatmentEntities = visitTO.getMedicalTreatments()
                .stream()
                .map(MedicalTreatmentMapper::mapToEntity)
                .toList();

        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(visitTO.getId());
        visitEntity.setDescription(visitTO.getDescription());
        visitEntity.setTime(visitTO.getTime());
        visitEntity.setPatient(PatientMapper.mapToEntity(visitTO.getPatient()));
        visitEntity.setMedicalTreatments(medicalTreatmentEntities);
        visitEntity.setDoctor(DoctorMapper.mapToEntity(visitTO.getDoctor()));
        return visitEntity;
    }
}
