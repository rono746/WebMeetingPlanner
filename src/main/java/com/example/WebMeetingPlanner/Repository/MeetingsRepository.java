package com.example.WebMeetingPlanner.Repository;

import com.example.WebMeetingPlanner.Model.Scheduling;
import com.example.WebMeetingPlanner.Model.TracomRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingsRepository extends JpaRepository<Scheduling, Long> {
    @Query("SELECT m FROM Scheduling m WHERE m.meeting_id = ?1")
    Scheduling findByMeeting_id(long meeting_id);

    @Query("SELECT m FROM Scheduling m WHERE  m.organization.Organisation_id = ?1 AND m.startDate >= CURRENT_TIMESTAMP ORDER BY m.startDate ASC")
    List<Scheduling> findMeetingByTime(long Organisation_id);

    @Query("SELECT m FROM Scheduling m WHERE  m.organization.Organisation_id = ?1 AND m.startDate <= CURRENT_TIMESTAMP ORDER BY m.startDate ASC")
    List<Scheduling> findPastMeetingsByTime(long Organisation_id);

//    Find a conflicting meeting
    @Query("FROM  Scheduling m WHERE m.tracomRooms = ?1 AND m.startDate <= ?2 AND m.EndDate > ?2")
    Scheduling findConflictingMeeting(TracomRooms tracomRooms, LocalDateTime start);


    @Query("SELECT m FROM Scheduling m WHERE  m.organization.Organisation_id = ?1 AND m.startDate = CURRENT_DATE")
    List<Scheduling> findMeetingByDateTime(long Organisation_id);

//    @Query("SELECT m FROM Scheduling m WHERE  m.organization.Organisation_id = ?1 AND m.startDate = CURRENT_DATE")
//    List<Scheduling> findMeetingByDate();

    @Query("SELECT m FROM Scheduling m WHERE  m.organization.Organisation_id = ?1")
    List<Scheduling>findMeetingTime(long Organisation_id);

//    @Query("SELECT m FROM Scheduling m WHERE  m.organization.Organisation_id = ?1 AND (m.startDate <> m.startTime)=?2")
//    List<Scheduling>findMeetingsByDate(long Organisation_id, LocalDateTime DateTime);

//  @Query("SELECT User FROM Scheduling m WHERE  m.organization.Organisation_id = ?1")
//    List<Scheduling>findAllMeetingsByDate(long Organisation_id);


//  @Query("SELECT User FROM Scheduling m WHERE  m.organization.Organisation_id = ?1")
//    List<Scheduling>findAllMeetingsByDateAndTime(long Organisation_id);

//    @Query("SELECT u1 FROM Scheduling u1 INNER JOIN meeting_users u2 ON u1.meeting_id = u2.meeting_id WHERE u2.user_id=?1")
//    List<User> findByUserId(long userId);


    @Query("SELECT COUNT (m.meeting_id) FROM Scheduling m WHERE m.organization.Organisation_id = ?1 AND m.startDate >= CURRENT_TIMESTAMP")
    long numberOfMeetingsInOrganization(long organizationId);

    @Query("SELECT COUNT (m.meeting_id) FROM Scheduling m WHERE m.organization.Organisation_id = ?1 AND m.startDate <= CURRENT_TIMESTAMP")
    long numberOfPastMeetingsInOrganization(long organizationId);
//    AND m.startDate >= CURRENT_DATE ORDER BY m.startDate ASC, m.startTime ASC
//    m.meeting_id = ?1 AND

//    AND m.organization.Organisation_id = ?1 AND m.startDate = CURRENT_DATE ORDER BY m.startDate DESC
//    @Query("SELECT m FROM Scheduling m WHERE m.organization.Organisation_id = ?1 ORDER BY DATE")

//    @Query("SELECT m FROM Scheduling m WHERE m.organization.Organisation_id = ?1 AND m.startTime >= CURRENT_TIME")
//    List<Scheduling> findMeetingByTime(long organizationId);


//    @Query("SELECT * FROM foo ORDER BY date ASC")
//    List<Foo> findAllOrderByDateAsc();

//    select foo from Foo foo order by foo.date desc
}