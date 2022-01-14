package com.skillbox.javapro21.repository;

import com.skillbox.javapro21.domain.PersonToDialog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonToDialogRepository extends JpaRepository<PersonToDialog, Integer> {
    @Query("select p2d from PersonToDialog p2d " +
            "left join Dialog d on p2d.dialog.id = d.id " +
            "left join Person p on p2d.person.id = p.id " +
            "left join Message m on m.dialog.id = d.id " +
            "where p.id = :id and d.title like %:query% " +
            "group by p2d.id " +
            "order by p2d.lastCheck desc")
    Page<PersonToDialog> findDialogsByPersonIdAndQuery(Long id, String query, Pageable pageable);

    @Query("select p2d from PersonToDialog p2d " +
            "left join Dialog d on p2d.dialog.id = d.id " +
            "left join Person p on p2d.person.id = p.id " +
            "left join Message m on m.dialog.id = d.id " +
            "where p.id = :id " +
            "group by p2d.id " +
            "order by p2d.lastCheck desc")
    Page<PersonToDialog> findDialogsByPerson(Long id, Pageable pageable);

    @Query("select p2d from PersonToDialog p2d " +
            "where p2d.person.id = :id and p2d.dialog.isBlocked = 0 and p2d.person.isBlocked = 0 ")
    List<PersonToDialog> findDialogsByPersonId(Long id);

    @Query("select p2d from PersonToDialog p2d " +
            "left join Person p on p2d.person.id = p.id " +
            "left join Dialog d on p2d.dialog.id = d.id " +
            "where p.id = :personId " +
            "and d.id = :dialogId ")
    PersonToDialog findDialogByPersonIdAndDialogId(Long personId, int dialogId);

    @Query("select p2d from PersonToDialog p2d " +
            "left join Dialog d on p2d.dialog.id = d.id " +
            "where d.id = :dialogId and p2d.person.id = :id")
    PersonToDialog findP2DByDialogAndMessage(int dialogId, Long id);
}
