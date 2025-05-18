package com.jpacourse.service;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistance.entity.VisitEntity;

import java.util.List;

public interface AddressService
{
    AddressTO findById(final Long id);
}
