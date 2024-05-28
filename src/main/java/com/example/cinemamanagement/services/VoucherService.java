package com.example.cinemamanagement.services;



import com.example.cinemamanagement.dtos.VoucherDTO;
import com.example.cinemamanagement.responses.VoucherResponse;

import java.util.List;

public interface VoucherService {
    VoucherResponse createVoucher(VoucherDTO voucherDTO);
    VoucherResponse getVoucherById(long id);
    List<VoucherResponse> getVouchersByVoucherRelease(Long vrId);
    VoucherResponse updateVoucher(long id, VoucherDTO voucherDTO);
    void deleteVoucher(long id);
}
