package com.cihan.emar.repository;

import com.cihan.emar.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByRoom_Id(long id);

    List<Reservation> findAllByCompany_Id(long id);

}
