package com.cihan.emar.service.base;

import com.cihan.emar.dto.RoomDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface RoomService {

    void save(RoomDTO roomDTO);

    Optional<RoomDTO> findById(long id);

    List<RoomDTO> findAll();

}
