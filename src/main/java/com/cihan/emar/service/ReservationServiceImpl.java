package com.cihan.emar.service;

import com.cihan.emar.dto.ReservationDTO;
import com.cihan.emar.error.CapacityExceedException;
import com.cihan.emar.error.CompanyAlreadyHasMeetingException;
import com.cihan.emar.error.RoomNotAvailableException;
import com.cihan.emar.mapper.ReservationMapper;
import com.cihan.emar.model.Company;
import com.cihan.emar.model.Room;
import com.cihan.emar.repository.CompanyRepository;
import com.cihan.emar.repository.ReservationRepository;
import com.cihan.emar.repository.RoomRepository;
import com.cihan.emar.service.base.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public ReservationDTO save(ReservationDTO reservationDTO) {
        validateReservation(reservationDTO);
        reservationDTO.setCost(calculateCost(reservationDTO));
        return reservationMapper.toReservationDTO(reservationRepository.save(reservationMapper.toReservation(reservationDTO)));
    }

    // Checks the capacity of meeting room and availability of the company and the meeting room
    private void validateReservation(ReservationDTO reservationDTO){
        List<ReservationDTO> companyReservations = reservationMapper.toReservationDTOList(reservationRepository.findByCompany_Id(reservationDTO.getCompany().getId()));
        List<ReservationDTO> roomReservations = reservationMapper.toReservationDTOList(reservationRepository.findByRoom_Id(reservationDTO.getRoom().getId()));
        Room room = roomRepository.getOne(reservationDTO.getRoom().getId());
        Company company = companyRepository.getOne(reservationDTO.getCompany().getId());
        if (room.getCapacity()<company.getMemberCount()) {
            throw new CapacityExceedException(room.getCapacity());
        }
        // Replace this part of code with native query
        if (checkDate(companyReservations, reservationDTO)) throw new CompanyAlreadyHasMeetingException();
        if (checkDate(roomReservations, reservationDTO)) throw new RoomNotAvailableException();
    }


    private boolean checkDate(List<ReservationDTO> reservations, ReservationDTO reservationDTO){
        for (ReservationDTO reservation: reservations){
            if (reservation.getStartDate().compareTo(reservationDTO.getStartDate()) * reservationDTO.getStartDate().compareTo(reservation.getEndDate()) > 0 ||
                    reservation.getStartDate().compareTo(reservationDTO.getEndDate()) * reservationDTO.getEndDate().compareTo(reservation.getEndDate()) > 0 ||
                    reservationDTO.getStartDate().compareTo(reservation.getStartDate()) * reservation.getEndDate().compareTo(reservationDTO.getEndDate()) > 0 ||
                    (reservationDTO.getStartDate().compareTo(reservation.getStartDate())==0 && reservationDTO.getEndDate().compareTo(reservation.getEndDate())==0)){
                return true;
            }
        }
        return false;
    }

    // meeting hours * room price + company population * 20
    private float calculateCost(ReservationDTO reservationDTO){
        long diff = reservationDTO.getEndDate().getTime() - reservationDTO.getStartDate().getTime();
        int hours = Math.round(diff / (1000 * 60 *60));
        float roomPrice = roomRepository.getOne(reservationDTO.getRoom().getId()).getPrice();
        float extra = 0;
        if (reservationDTO.getHasExtra()) {
            int memberCount = companyRepository.getOne(reservationDTO.getCompany().getId()).getMemberCount();
            extra += memberCount * 20;
        }
        return roomPrice * hours + extra;
    }

    @Override
    public List<ReservationDTO> findByRoom_Id(long id) {
        return reservationMapper.toReservationDTOList(reservationRepository.findByRoom_Id(id));
    }

    @Override
    public List<ReservationDTO> findByCompany_Id(long id) {
        return reservationMapper.toReservationDTOList(reservationRepository.findByCompany_Id(id));
    }
}
