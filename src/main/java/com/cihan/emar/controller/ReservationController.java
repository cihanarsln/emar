package com.cihan.emar.controller;

import com.cihan.emar.dto.ReservationDTO;
import com.cihan.emar.service.base.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emar/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ReservationDTO save(@RequestBody ReservationDTO reservationDTO){
        return reservationService.save(reservationDTO);
    }

    @GetMapping("/room")
    public List<ReservationDTO> findByRoom_Id(@RequestParam long id){
        return reservationService.findByRoom_Id(id);
    }

    @GetMapping("/company")
    public List<ReservationDTO> findByCompany_Id(@RequestParam long id){
        return reservationService.findByCompany_Id(id);
    }

}
