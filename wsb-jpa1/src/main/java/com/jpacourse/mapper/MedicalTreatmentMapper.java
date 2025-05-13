package com.jpacourse.mapper;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.MedicalTreatmentTO;
import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.persistance.entity.MedicalTreatmentEntity;

public class MedicalTreatmentMapper {
    public static MedicalTreatmentTO mapToTO(final MedicalTreatmentEntity medicalTreatmentEntity)
    {
        if (medicalTreatmentEntity == null)
        {
            return null;
        }
        final MedicalTreatmentTO medicalTreatmentTO = new MedicalTreatmentTO();
        medicalTreatmentTO.setId(medicalTreatmentEntity.getId());
        medicalTreatmentTO.setDescription(medicalTreatmentEntity.getDescription());
        medicalTreatmentTO.setType(medicalTreatmentEntity.getType());
        medicalTreatmentTO.setVisit(VisitMapper.mapToTO(medicalTreatmentEntity.getVisit()));
        return medicalTreatmentTO;
    }

    public static MedicalTreatmentEntity mapToEntity(final MedicalTreatmentTO medicalTreatmentTO)
    {
        if(medicalTreatmentTO == null)
        {
            return null;
        }
        MedicalTreatmentEntity medicalTreatmentEntity = new MedicalTreatmentEntity();
        medicalTreatmentEntity.setId(medicalTreatmentTO.getId());
        medicalTreatmentEntity.setDescription(medicalTreatmentTO.getDescription());
        medicalTreatmentEntity.setType(medicalTreatmentTO.getType());
        medicalTreatmentEntity.setVisit(VisitMapper.mapToEntity(medicalTreatmentTO.getVisit()));

        return medicalTreatmentEntity;
    }
}
