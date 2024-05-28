package com.example.cinemamanagement.services;
import com.example.cinemamanagement.dtos.VoucherReleaseDTO;
import com.example.cinemamanagement.models.VoucherRelease;

import java.util.List;

public interface VoucherReleaseService {
    VoucherRelease createVoucherRelease(VoucherReleaseDTO voucherReleaseDTO);
    VoucherRelease getVoucherReleaseById(long id);
    List<VoucherRelease> getAllVoucherReleases();
    VoucherRelease updateVoucherRelease(long id, VoucherReleaseDTO voucherReleaseDTO);
}
