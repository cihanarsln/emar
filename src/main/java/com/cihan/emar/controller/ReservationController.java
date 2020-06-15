package com.cihan.emar.controller;

import com.cihan.emar.dto.ReservationDTO;
import com.cihan.emar.service.base.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emar/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> save(@RequestBody ReservationDTO reservationDTO){
        reservationService.save(reservationDTO);
        return new ResponseEntity<>("The meeting room has been reserved successfully.", HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Object> getOne(@RequestParam("id") long id){
        return new ResponseEntity<>(reservationService.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<Object> findAll(){
        return new ResponseEntity<>(reservationService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseEntity<Object> deleteById(@RequestParam("id") long id){
        reservationService.deleteById(id);
        return new ResponseEntity<>(String.format("The reservation has been deleted with id %s", id), HttpStatus.OK);
    }

    @GetMapping("/room")
    @ResponseBody
    public ResponseEntity<Object> findByRoom_Id(@RequestParam("id") long id){
        return new ResponseEntity<>(reservationService.findAllByRoom_Id(id), HttpStatus.OK);
    }

    @GetMapping("/company")
    @ResponseBody
    public ResponseEntity<Object> findByCompany_Id(@RequestParam("id") long id){
        return new ResponseEntity<>(reservationService.findAllByCompany_Id(id), HttpStatus.OK);
    }

}
