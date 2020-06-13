package com.cihan.emar.service.base;

import com.cihan.emar.dto.ReservationDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReservationService {

    ReservationDTO save(ReservationDTO reservationDTO);

    List<ReservationDTO> findByRoom_Id(long id);

    List<ReservationDTO> findByCompany_Id(long id);

}