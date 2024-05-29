package com.example.cinemamanagement.services.impl;

import com.example.cinemamanagement.dtos.BillDTO;
import com.example.cinemamanagement.dtos.ProductBillDTO;
import com.example.cinemamanagement.dtos.TicketDTO;
import com.example.cinemamanagement.exceptions.ResourceNotFoundException;
import com.example.cinemamanagement.models.*;
import com.example.cinemamanagement.repositories.*;
import com.example.cinemamanagement.responses.BillResponse;
import com.example.cinemamanagement.services.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final VoucherRepository voucherRepository;
    private final ShowTimeRepository showTimeRepository;
    private final SeatRepository seatRepository;
    @Override
    public BillResponse createBill(BillDTO billDTO) {
        User user = userRepository.findById(billDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Voucher voucher = billDTO.getVoucherId() == null ? null
                : voucherRepository.findById(billDTO.getVoucherId())
                .orElseThrow(() -> new ResourceNotFoundException("Voucher not found"));
        Bill bill = new Bill();
        bill.setCreatedAt(LocalDate.now());
        bill.setTotalPrice(billDTO.getTotalPrice());
        bill.setUser(user);
        bill.setVoucher(voucher);

        for(ProductBillDTO p : billDTO.getProductBills()){
            Product product = productRepository.findById(p.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cannot find " +
                            "Product with id = " + p.getProductId()));
            ProductBill productBill = ProductBill.builder()
                    .quantity(p.getQuantity())
                    .product(product)
                    .build();
            bill.addProductBill(productBill);
        }

        for(TicketDTO t : billDTO.getTickets()){
            ShowTime showTime = showTimeRepository.findById(t.getShowTimeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cannot find " +
                            "ShowTime with id = " + t.getShowTimeId()));
            Seat seat = seatRepository.findById(t.getSeatId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cannot find " +
                            "Seat with id = " + t.getSeatId()));
            Ticket ticket = Ticket.builder()
                    .showTime(showTime)
                    .seat(seat)
                    .build();
            bill.addTicket(ticket);
        }
        billRepository.save(bill);
        return BillResponse.fromBill(bill);
    }

    @Override
    public BillResponse getBillById(long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found"));
        return BillResponse.fromBill(bill);
    }

    @Override
    public List<BillResponse> getBillsByUser(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return billRepository.findAllByUser(user)
                .stream()
                .map(BillResponse::fromBill)
                .toList();
    }

    @Override
    public List<BillResponse> getAllBills() {
        return billRepository.findAll()
                .stream()
                .map(BillResponse::fromBill)
                .toList();
    }

    @Override
    public BillResponse updateBill(long id, BillDTO billDTO) {
        return null;
    }
}
