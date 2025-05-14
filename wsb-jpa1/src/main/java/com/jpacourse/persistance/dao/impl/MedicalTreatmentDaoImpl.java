package com.jpacourse.persistance.dao.impl;

import com.jpacourse.persistance.dao.AddressDao;
import com.jpacourse.persistance.dao.MedicalTreatmentDao;
import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.persistance.entity.MedicalTreatmentEntity;
import org.springframework.stereotype.Repository;

@Repository
public class MedicalTreatmentDaoImpl extends AbstractDao<MedicalTreatmentEntity, Long> implements MedicalTreatmentDao
{

}
