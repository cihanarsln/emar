package com.cihan.emar.service;

import com.cihan.emar.dto.RoomDTO;
import com.cihan.emar.mapper.RoomMapper;
import com.cihan.emar.repository.RoomRepository;
import com.cihan.emar.service.base.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public RoomDTO save(RoomDTO roomDTO) {
        return roomMapper.toRoomDTO(roomRepository.save(roomMapper.toRoom(roomDTO)));
    }
}
