package com.unishoes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
    public String index() {
        return "index"; // carrega o index.html
    }
	//
    //@GetMapping("/")
    //public String home() {
    //    return "redirect:/app/produtos";
    //}
}
