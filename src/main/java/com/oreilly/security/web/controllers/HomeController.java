package com.oreilly.security.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oreilly.security.domain.entities.AutoUser;
import com.oreilly.security.domain.repositories.AutoUserRepository;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private AutoUserRepository rep;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String goRegister() {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute AutoUser user) {
		user.setRole("ROLE_USER");
		rep.save(user);
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getRole()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String goHome() {
		return "home";
	}

	@RequestMapping("/services")
	public String goServices() {
		return "services";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String goLogin() {
		return "login";
	}

	@RequestMapping("/schedule")
	public String goSchedule() {
		return "schedule";
	}
}
