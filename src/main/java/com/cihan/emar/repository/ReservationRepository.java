package com.cihan.emar.repository;

import com.cihan.emar.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByRoom_Id(long id);

    List<Reservation> findByCompany_Id(long id);

}
