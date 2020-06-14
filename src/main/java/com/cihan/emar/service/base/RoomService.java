package com.cihan.emar.service.base;

import com.cihan.emar.dto.RoomDTO;
import org.springframework.stereotype.Component;

@Component
public interface RoomService {

    void save(RoomDTO roomDTO);

}
