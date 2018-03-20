package com.cafe24.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.guestbook.dao.GuestbookDAO;
import com.cafe24.guestbook.vo.GuestbookVO;

@Controller
public class GuestbookController {
    @Autowired
    private GuestbookDAO guestbookDao;
    
    @RequestMapping(value="/board", method=RequestMethod.POST)
    public String create(@ModelAttribute GuestbookVO vo){
	guestbookDao.create( vo );
	return "redirect:/board";
    }
    
    @RequestMapping(value="/board", method=RequestMethod.GET)
    public String readAll(Model model){
	List<GuestbookVO> list = guestbookDao.readAll();
	model.addAttribute( "list", list );
	return "/WEB-INF/views/board.jsp";
    }
    
    @RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
    public String deleteform(@PathVariable("no") Long no, Model model){
	model.addAttribute("no", no);
	return "/WEB-INF/views/delete.jsp";
    }
    
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public String delete(@ModelAttribute GuestbookVO vo){
	guestbookDao.delete( vo );
	return "redirect:/board";
    }
    
}
