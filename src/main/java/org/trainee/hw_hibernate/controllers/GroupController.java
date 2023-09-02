package org.trainee.hw_hibernate.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.trainee.hw_hibernate.entities.Group;
import org.trainee.hw_hibernate.entities.Teacher;
import org.trainee.hw_hibernate.services.GroupService;

import javax.validation.Valid;

@Controller
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController{

    private final GroupService groupService;

    @GetMapping
    public String findAllGroups(Model model) {
        model.addAttribute("groups", groupService.getAllGroups());

        return "group/index";
    }

    @GetMapping("/{id}")
    public String infoGroup(@PathVariable("id") Long id, Model model) {
        //получим одну группу по id из БД и передадим на отображение и представление
        model.addAttribute("group", groupService.getGroupById(id));
        return "group/info";
    }

    @GetMapping("/new")
    public String newGroup(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "group/new";
    }

    @PostMapping()
    public String createGroup(@ModelAttribute("group") @Valid Group group, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "group/new";
        }
        groupService.saveGroup(group);
        return "redirect:/groups";
    }

    @GetMapping("/{id}/edit")
    public String editGroup(Model model, @PathVariable("id") Long id) {
        model.addAttribute("group", groupService.getGroupById(id));
        return "group/update";
    }

    @PatchMapping("/{id}")
    public String updateGroup(@ModelAttribute("teacher") @Valid Group group,
                         BindingResult bindingResult, @PathVariable("id") Long id) {
        if(bindingResult.hasErrors())
            return "group/update";

        groupService.updateGroup(id, group);
        return "redirect:/groups";
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable("id") Long id) {
        groupService.deleteGroup(id);
        return "redirect:/groups";
    }
}