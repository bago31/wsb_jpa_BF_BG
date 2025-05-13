package com.jpacourse.mapper;

import com.jpacourse.dto.DoctorTO;
import com.jpacourse.mapper.AddressMapper;
import com.jpacourse.persistance.entity.DoctorEntity;

public final class DoctorMapper
{

    public static DoctorTO mapToTO(final DoctorEntity doctorEntity)
    {
        if (doctorEntity == null)
        {
            return null;
        }
        final DoctorTO doctorTO = new DoctorTO();
        doctorTO.setId(doctorEntity.getId());
        doctorTO.setAddress(AddressMapper.mapToTO(doctorEntity.getAddress()));
        doctorTO.setDoctorNumber(doctorEntity.getDoctorNumber());
        doctorTO.setFirstName(doctorEntity.getFirstName());
        doctorTO.setLastName(doctorEntity.getLastName());
        doctorTO.setEmail(doctorEntity.getEmail());
        doctorTO.setSpecialization(doctorEntity.getSpecialization());
        doctorTO.setTelephoneNumber(doctorEntity.getTelephoneNumber());
        return doctorTO;
    }

    public static DoctorEntity mapToEntity(final DoctorTO doctorTO)
    {
        if(doctorTO == null)
        {
            return null;
        }
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setId(doctorTO.getId());
        doctorEntity.setFirstName(doctorTO.getFirstName());
        doctorEntity.setLastName(doctorTO.getLastName());
        doctorEntity.setAddress(AddressMapper.mapToEntity(doctorTO.getAddress()));
        doctorEntity.setEmail(doctorTO.getEmail());
        doctorEntity.setSpecialization(doctorTO.getSpecialization());
        doctorEntity.setTelephoneNumber(doctorTO.getTelephoneNumber());
        doctorEntity.setDoctorNumber(doctorTO.getDoctorNumber());

        return doctorEntity;
    }
}
