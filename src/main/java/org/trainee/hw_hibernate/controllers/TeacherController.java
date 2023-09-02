package org.trainee.hw_hibernate.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.trainee.hw_hibernate.entities.Teacher;
import org.trainee.hw_hibernate.services.TeacherService;

import javax.validation.Valid;

@Controller
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController{
    private final TeacherService teacherService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public String findAllTeachers(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeachers());
        logger.info("findAllTeachers");
        return "teachers/index";
    }

    @GetMapping("/{id}")
    public String infoTeacher(@PathVariable("id") Long id, Model model) {
        //получим одного учителя по его id из БД и передадим на отображение и представление
        model.addAttribute("teacher", teacherService.getTeacherById(id));
        return "teachers/info";
    }

    @GetMapping("/new")
    public String newTeacher(Model model) {
        model.addAttribute("person", new Teacher());
        return "teachers/new";
    }

    @PostMapping()
    public String createTeacher(@ModelAttribute("teacher") @Valid Teacher teacher, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "teachers/new";
        }
        teacherService.saveTeacher(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/{id}/edit")
    public String editTeacher(Model model, @PathVariable("id") Long id) {
        model.addAttribute("teacher", teacherService.getTeacherById(id));
        return "teachers/update";
    }

    @PatchMapping("/{id}")
    public String updateTeacher(@ModelAttribute("teacher") @Valid Teacher teacher,
                         BindingResult bindingResult, @PathVariable("id") Long id) {
        if(bindingResult.hasErrors())
            return "teachers/update";

        teacherService.updateTeacher(id, teacher);
        return "redirect:/teachers";
    }

    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteTeacher(id);
        return "redirect:/teachers";
    }
}