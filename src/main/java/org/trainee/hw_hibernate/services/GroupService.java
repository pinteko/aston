package org.trainee.hw_hibernate.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.trainee.hw_hibernate.entities.Group;
import org.trainee.hw_hibernate.entities.Teacher;
import org.trainee.hw_hibernate.repositories.GroupRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

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

    public void updateGroup(Long id, Group group) {
        groupRepository.updateGroup(id, group);
    }
}