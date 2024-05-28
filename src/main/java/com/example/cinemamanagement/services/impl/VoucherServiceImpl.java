package com.example.cinemamanagement.services.impl;

import com.example.cinemamanagement.dtos.VoucherDTO;
import com.example.cinemamanagement.exceptions.DuplicateValueException;
import com.example.cinemamanagement.exceptions.InvalidParamException;
import com.example.cinemamanagement.exceptions.ResourceNotFoundException;
import com.example.cinemamanagement.models.Voucher;
import com.example.cinemamanagement.models.VoucherRelease;
import com.example.cinemamanagement.repositories.VoucherRepository;
import com.example.cinemamanagement.responses.VoucherResponse;
import com.example.cinemamanagement.services.VoucherReleaseService;
import com.example.cinemamanagement.services.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {
    private final VoucherRepository voucherRepository;
    private final VoucherReleaseService voucherReleaseService;
    @Override
    public VoucherResponse createVoucher(VoucherDTO voucherDTO) {
        if(voucherRepository.existsByCode(voucherDTO.getCode()))
            throw new DuplicateValueException("Code already exists");
        VoucherRelease voucherRelease = voucherReleaseService.getVoucherReleaseById(voucherDTO.getVoucherReleaseId());
        Voucher voucher = voucherRepository.save(Voucher.builder()
                .code(voucherDTO.getCode())
                .status(false)
                .usedAt(null)
                .releasedAt(null)
                .voucherRelease(voucherRelease)
                .build());
        return VoucherResponse.fromVoucher(voucher);
    }

    @Override
    public VoucherResponse getVoucherById(long id) {
        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voucher not found"));
        return VoucherResponse.fromVoucher(voucher);
    }


    @Override
    public List<VoucherResponse> getVouchersByVoucherRelease(Long vrId) {
        VoucherRelease voucherRelease = voucherReleaseService.getVoucherReleaseById(vrId);
        return voucherRepository.findByVoucherRelease(voucherRelease)
                .stream()
                .map(VoucherResponse::fromVoucher)
                .toList();
    }

    @Override
    public VoucherResponse updateVoucher(long id, VoucherDTO voucherDTO) {
        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voucher not found"));
        VoucherRelease voucherRelease = voucherReleaseService.getVoucherReleaseById(voucherDTO.getVoucherReleaseId());
        if(!voucher.getCode().equals(voucherDTO.getCode())
                && voucherRepository.existsByCode(voucherDTO.getCode())){
            throw new DuplicateValueException("Code already exists");
        }
        if(voucherDTO.getUsedAt() != null && voucherDTO.getUsedAt().isBefore(LocalDateTime.now()))
            throw new InvalidParamException("UsedAt must be before current date");
        if(voucherDTO.getReleasedAt() != null && voucherDTO.getReleasedAt().isBefore(LocalDateTime.now()))
            throw new InvalidParamException("ReleasedAt must be before current date");
        if(voucherDTO.getUsedAt() != null && voucherDTO.getReleasedAt() != null
        && voucherDTO.getUsedAt().isBefore(voucherDTO.getReleasedAt()))
            throw new InvalidParamException("ReleasedAt must be before UsedAt");
        voucher.setStatus(voucherDTO.isStatus());
        voucher.setUsedAt(voucherDTO.getUsedAt());
        voucher.setReleasedAt(voucherDTO.getReleasedAt());
        voucher.setVoucherRelease(voucherRelease);
        return VoucherResponse.fromVoucher(voucherRepository.save(voucher));
    }

    @Override
    public void deleteVoucher(long id) {
        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voucher not found"));
        if(voucher.isStatus() || voucher.getUsedAt() != null || voucher.getReleasedAt() != null)
            throw new RuntimeException("The voucher cannot be deleted because the voucher has already been issued to the customer");
        voucherRepository.delete(voucher);
    }
}
