package com.example.cinemamanagement.services.impl;

import com.example.cinemamanagement.dtos.VoucherReleaseDTO;
import com.example.cinemamanagement.exceptions.DuplicateValueException;
import com.example.cinemamanagement.exceptions.InvalidParamException;
import com.example.cinemamanagement.exceptions.ResourceNotFoundException;
import com.example.cinemamanagement.models.VoucherRelease;
import com.example.cinemamanagement.repositories.VoucherReleaseRepository;
import com.example.cinemamanagement.services.VoucherReleaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoucherReleaseServiceImpl implements VoucherReleaseService {
    private final VoucherReleaseRepository voucherReleaseRepository;

    @Override
    @Transactional
    public VoucherRelease createVoucherRelease(VoucherReleaseDTO voucherReleaseDTO) {
        if(voucherReleaseRepository.existsByRealeaseName(voucherReleaseDTO.getRealeaseName()))
            throw new DuplicateValueException("VoucherRelease name already exists");
        if(voucherReleaseDTO.getStartDate().isBefore(LocalDate.now()))
            throw new InvalidParamException("StartDate must be before current date");
        if(voucherReleaseDTO.getFinishDate().isBefore(LocalDate.now()))
            throw new InvalidParamException("FinishDate must be before current date");
        if(voucherReleaseDTO.getStartDate().isAfter(voucherReleaseDTO.getFinishDate()))
            throw new InvalidParamException("StartDate must be before FinishDate");
        VoucherRelease voucherRelease = VoucherRelease.builder()
                .realeaseName(voucherReleaseDTO.getRealeaseName())
                .startDate(voucherReleaseDTO.getStartDate())
                .finishDate(voucherReleaseDTO.getFinishDate())
                .price(voucherReleaseDTO.getPrice())
                .minPrice(voucherReleaseDTO.getMinPrice())
                .objectType(voucherReleaseDTO.getObjectType())
                .build();
        return voucherReleaseRepository.save(voucherRelease);
    }

    @Override
    public VoucherRelease getVoucherReleaseById(long id) {
        return voucherReleaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VoucherRelease not found"));
    }

    @Override
    public List<VoucherRelease> getAllVoucherReleases() {
        return voucherReleaseRepository.findAll();
    }

    @Override
    @Transactional
    public VoucherRelease updateVoucherRelease(long id, VoucherReleaseDTO voucherReleaseDTO) {
        VoucherRelease voucherRelease = getVoucherReleaseById(id);
        if(!voucherRelease.getRealeaseName().equals(voucherReleaseDTO.getRealeaseName())
                && voucherReleaseRepository.existsByRealeaseName(voucherReleaseDTO.getRealeaseName())){
            throw new DuplicateValueException("VoucherRelease name already exists");
        }
        voucherRelease.setRealeaseName(voucherReleaseDTO.getRealeaseName());
        voucherRelease.setStartDate(voucherReleaseDTO.getStartDate());
        voucherRelease.setFinishDate(voucherReleaseDTO.getFinishDate());
        voucherRelease.setPrice(voucherReleaseDTO.getPrice());
        voucherRelease.setMinPrice(voucherReleaseDTO.getMinPrice());
        voucherRelease.setObjectType(voucherReleaseDTO.getObjectType());
        return voucherReleaseRepository.save(voucherRelease);
    }
}
