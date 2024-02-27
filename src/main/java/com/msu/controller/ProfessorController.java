package com.msu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msu.entities.ProfessorDetails;
import com.msu.services.ProfessorService;

@Controller
public class ProfessorController {
	
	 @Autowired
	 public ProfessorService professorService;
	
	 // This is just a mock list of products, you'd fetch them from a database or another service in a real application
//    private List<Products> productList = new ArrayList<>();
//
//   
//
//    @GetMapping("/products")
//    public String showProducts(Model model) {
//    	
//        model.addAttribute("products", productList);
//        return "products";
//    }
    
    @GetMapping("/getProfessors")
    @ResponseBody
    public List<ProfessorDetails> getProfessorList(Model model) {
        
    	return professorService.findAll();
    	
    	
            
    }
    
   /* @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("professor", new Professor());
        return "createProfessor";
    }

    @PostMapping("/save")
    public String saveProfessor(Professor professor) {
    	professorService.save(professor);
        return "/create";
    }
*/
}
