package com.example.cinemamanagement.services;

import com.example.cinemamanagement.dtos.BillDTO;
import com.example.cinemamanagement.responses.BillResponse;

import java.util.List;

public interface BillService {
    BillResponse createBill(BillDTO billDTO);
    BillResponse getBillById(long id);
    List<BillResponse> getBillsByUser(long userId);
    List<BillResponse> getAllBills();
    BillResponse updateBill(long id, BillDTO billDTO);
}
