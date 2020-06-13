package com.cihan.emar.mapper;

import com.cihan.emar.dto.ReservationDTO;
import com.cihan.emar.model.Reservation;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Named("toReservation")
    Reservation toReservation(ReservationDTO reservationDTO);

    @Named("toReservationDTO")
    ReservationDTO toReservationDTO(Reservation reservation);

    @IterableMapping(qualifiedByName = "toReservation")
    List<Reservation> toReservationList(List<ReservationDTO> reservationDTOList);

    @IterableMapping(qualifiedByName = "toReservationDTO")
    List<ReservationDTO> toReservationDTOList(List<Reservation> reservationList);

}
