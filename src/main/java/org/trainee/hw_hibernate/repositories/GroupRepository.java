package org.trainee.hw_hibernate.repositories;

import org.trainee.hw_hibernate.entities.Group;

import java.util.List;

public interface GroupRepository {

    Group getGroupById(Long id);

    Group getGroupByTitle(String title);

    List<Group> getAllGroups();

    void saveGroup(Group group);

    void deleteGroup(Long id);
}
