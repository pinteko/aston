package org.trainee.hw_hibernate.services;

import org.hibernate.SessionFactory;
import org.trainee.hw_hibernate.entities.Group;
import org.trainee.hw_hibernate.repositories.GroupRepository;
import org.trainee.hw_hibernate.repositories.GroupRepositoryImpl;

import java.util.List;

public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(SessionFactory sessionFactory) {
        groupRepository = new GroupRepositoryImpl(sessionFactory);
    }

    public Group getGroupById(Long id) {
        return groupRepository.getGroupById(id);
    }

    public Group getGroupByName(String title) {
       return groupRepository.getGroupByTitle(title);
    }

    public List<Group> getAllGroups() {
        return groupRepository.getAllGroups();
    }

    public void saveGroup(Group group) {
        groupRepository.saveGroup(group);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteGroup(id);
    }
}