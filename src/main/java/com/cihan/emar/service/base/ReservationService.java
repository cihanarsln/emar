package com.cihan.emar.service.base;

import com.cihan.emar.dto.ReservationDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReservationService {

    void save(ReservationDTO reservationDTO);

    ReservationDTO getOne(long id);

    List<ReservationDTO> findAll();

    void deleteById(long id);

    List<ReservationDTO> findAllByRoom_Id(long id);

    List<ReservationDTO> findAllByCompany_Id(long id);

}