package com.cihan.emar.controller;

import com.cihan.emar.dto.RoomDTO;
import com.cihan.emar.service.base.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/emar/room")
@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public RoomDTO save(@RequestBody RoomDTO roomDTO){
        return roomService.save(roomDTO);
    }

}
