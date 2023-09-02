package org.trainee.hw_hibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.trainee.hw_hibernate.entities.Group;

import java.util.List;

@Repository
public interface GroupRepository  extends JpaRepository<Group, Long> {

    Group getGroupById(Long id);

    Group getGroupByTitle(String title);

    List<Group> getAllGroups();

    void saveGroup(Group group);

    void deleteGroup(Long id);

    void updateGroup(Long id, Group group);
}
