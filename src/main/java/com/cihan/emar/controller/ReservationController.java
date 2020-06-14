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
    @ResponseBody
    public ReservationDTO save(@RequestBody ReservationDTO reservationDTO){
        return reservationService.save(reservationDTO);
    }

    @GetMapping
    @ResponseBody
    public List<ReservationDTO> findAll(){
        return reservationService.findAll();
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestParam long id){
        reservationService.deleteById(id);
    }

    @GetMapping("/room")
    @ResponseBody
    public List<ReservationDTO> findByRoom_Id(@RequestParam long id){
        return reservationService.findAllByRoom_Id(id);
    }

    @GetMapping("/company")
    @ResponseBody
    public List<ReservationDTO> findByCompany_Id(@RequestParam long id){
        return reservationService.findAllByCompany_Id(id);
    }

}
