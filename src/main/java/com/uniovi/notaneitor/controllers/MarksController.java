package com.uniovi.notaneitor.controllers;

import com.uniovi.notaneitor.services.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.uniovi.notaneitor.entities.Mark;

@RestController
public class MarksController {

    @Autowired  //Inyectar el servicio
    private MarksService marksService;

    @RequestMapping("/mark/list")
    public String getList() {
        return marksService.getMarks().toString();
    }

    @RequestMapping(value = "/mark/add", method = RequestMethod.POST)
    public String setMark(@ModelAttribute Mark mark) {
        marksService.addMark(mark);
        return "OK";
    }

    @RequestMapping("/mark/details/{id}")   // This is one way of doing it (/mark/details/5)
    public String getDetail(@PathVariable Long id) {
        return marksService.getMark(id).toString();
    }

//    @RequestMapping("/mark/details")  // This is another way of doing it too (/mark/details?id=4)
//    public String getDetail(@RequestParam Long id) {
//        return "Getting Details => " + id;
//    }

    @RequestMapping("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id) {
        marksService.deleteMark(id);
        return "OK";
    }

}
