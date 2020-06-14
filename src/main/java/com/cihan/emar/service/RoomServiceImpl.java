package com.cihan.emar.service;

import com.cihan.emar.dto.RoomDTO;
import com.cihan.emar.mapper.RoomMapper;
import com.cihan.emar.repository.RoomRepository;
import com.cihan.emar.service.base.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public void save(RoomDTO roomDTO) {
        roomMapper.toRoomDTO(roomRepository.save(roomMapper.toRoom(roomDTO)));
    }

    @Override
    public Optional<RoomDTO> findById(long id) {
        RoomDTO roomDTO = roomMapper.toRoomDTO(roomRepository.findById(id).get());
        Optional<RoomDTO> optionalRoomDTO = Optional.of(roomDTO);
        return optionalRoomDTO;
    }
}
