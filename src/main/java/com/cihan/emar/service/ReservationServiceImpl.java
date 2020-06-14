package com.cihan.emar.service;

import com.cihan.emar.dto.CompanyDTO;
import com.cihan.emar.dto.ReservationDTO;
import com.cihan.emar.dto.RoomDTO;
import com.cihan.emar.error.*;
import com.cihan.emar.mapper.ReservationMapper;
import com.cihan.emar.repository.ReservationRepository;
import com.cihan.emar.service.base.CompanyService;
import com.cihan.emar.service.base.ReservationService;
import com.cihan.emar.service.base.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public void save(ReservationDTO reservationDTO) {
        validateReservation(reservationDTO);
        validateCost(reservationDTO);
        reservationMapper.toReservationDTO(reservationRepository.save(reservationMapper.toReservation(reservationDTO)));
    }

    // Gets room with using roomService
    private RoomDTO findRoom(long id){
        return roomService.findById(id).orElseThrow(() -> new NotFoundRecordException(String.format("Can't find a meeting room with id %s", id)));
    }

    // Gets company with using companyService
    private CompanyDTO findCompany(long id){
        return companyService.findById(id).orElseThrow(() -> new NotFoundRecordException(String.format("Can't find a company with id %s", id)));
    }

    // Checks the capacity of meeting room and availability of the company and the meeting room
    private void validateReservation(ReservationDTO reservationDTO){
        final RoomDTO room = findRoom(reservationDTO.getRoom().getId());
        final CompanyDTO company = findCompany(reservationDTO.getCompany().getId());
        List<ReservationDTO> companyReservations = reservationMapper.toReservationDTOList(reservationRepository.findAllByCompany_Id(company.getId()));
        List<ReservationDTO> roomReservations = reservationMapper.toReservationDTOList(reservationRepository.findAllByRoom_Id(room.getId()));
        if (room.getCapacity()<company.getMemberCount()) {
            throw new CapacityExceedException(room.getCapacity());
        }
        // Replace this part of code with native query
        if (checkDate(companyReservations, reservationDTO)) throw new CompanyAlreadyHasMeetingException();
        if (checkDate(roomReservations, reservationDTO)) throw new RoomNotAvailableException();
    }

    // Checks availability of room or companies between start end end date
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
        final RoomDTO room = findRoom(reservationDTO.getRoom().getId());
        final CompanyDTO company = findCompany(reservationDTO.getCompany().getId());
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
    public List<ReservationDTO> findAll() {
        return reservationMapper.toReservationDTOList(reservationRepository.findAll());
    }

    @Override
    public void deleteById(long id) {
        reservationRepository.findById(id).orElseThrow(() -> new NotFoundRecordException(String.format("Can't find a reservation with id %s", id)));
        reservationRepository.deleteById(id);
    }

    @Override
    public List<ReservationDTO> findAllByRoom_Id(long id) {
        return reservationMapper.toReservationDTOList(reservationRepository.findAllByRoom_Id(id));
    }

    @Override
    public List<ReservationDTO> findAllByCompany_Id(long id) {
        return reservationMapper.toReservationDTOList(reservationRepository.findAllByCompany_Id(id));
    }
}
