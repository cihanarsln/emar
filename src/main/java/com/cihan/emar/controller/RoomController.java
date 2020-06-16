package com.cihan.emar.controller;

import com.cihan.emar.dto.RoomDTO;
import com.cihan.emar.service.base.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/emar/room")
@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> save(@RequestBody RoomDTO roomDTO){
        roomService.save(roomDTO);
        return new ResponseEntity<>("Meeting Room is created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<Object> findAll(){
        return new ResponseEntity<>(roomService.findAll(), HttpStatus.OK);
    }

}
