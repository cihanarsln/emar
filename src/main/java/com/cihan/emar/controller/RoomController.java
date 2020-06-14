package com.cihan.emar.controller;

import com.cihan.emar.dto.RoomDTO;
import com.cihan.emar.service.base.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/emar/room")
@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    @ResponseBody
    public RoomDTO save(@RequestBody RoomDTO roomDTO){
        return roomService.save(roomDTO);
    }

}
