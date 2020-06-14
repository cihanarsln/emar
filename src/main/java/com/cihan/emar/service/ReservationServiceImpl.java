package com.cihan.emar.service;

import com.cihan.emar.dto.ReservationDTO;
import com.cihan.emar.error.*;
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
        validateCost(reservationDTO);
        return reservationMapper.toReservationDTO(reservationRepository.save(reservationMapper.toReservation(reservationDTO)));
    }

    // Checks the capacity of meeting room and availability of the company and the meeting room
    private void validateReservation(ReservationDTO reservationDTO){
        final Room room = roomRepository.findById(reservationDTO.getRoom().getId()).orElseThrow(() -> new NotFoundRecordException(String.format("Can't find a meeting room with id %s", reservationDTO.getRoom().getId())));
        final Company company = companyRepository.findById(reservationDTO.getCompany().getId()).orElseThrow(() -> new NotFoundRecordException(String.format("Can't find a company with id %s", reservationDTO.getCompany().getId())));
        List<ReservationDTO> companyReservations = reservationMapper.toReservationDTOList(reservationRepository.findByCompany_Id(reservationDTO.getCompany().getId()));
        List<ReservationDTO> roomReservations = reservationMapper.toReservationDTOList(reservationRepository.findByRoom_Id(reservationDTO.getRoom().getId()));
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

    // Validate room cost expected and actual(sent)
    private void validateCost(ReservationDTO reservationDTO){
        if (reservationDTO.getCost() != calculateCost(reservationDTO)) throw new CostMismatchException();
    }

    // meeting hours * room price + company population * 10
    private float calculateCost(ReservationDTO reservationDTO){
        final Room room = roomRepository.findById(reservationDTO.getRoom().getId()).orElseThrow(() -> new NotFoundRecordException(String.format("Can't find a meeting room with id %s", reservationDTO.getRoom().getId())));
        final Company company = companyRepository.findById(reservationDTO.getCompany().getId()).orElseThrow(() -> new NotFoundRecordException(String.format("Can't find a company with id %s", reservationDTO.getCompany().getId())));
        long diff = reservationDTO.getEndDate().getTime() - reservationDTO.getStartDate().getTime();
        int hours = Math.round(diff / (1000 * 60 *60));
        float extra = 0;
        if (reservationDTO.getHasExtra()) {
            int memberCount = company.getMemberCount();
            extra += memberCount * 10;
        }
        return room.getPrice() * hours + extra;
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
