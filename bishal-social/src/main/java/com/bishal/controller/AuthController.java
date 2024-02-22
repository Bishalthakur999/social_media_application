package com.bishal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bishal.config.JwtProvider;
import com.bishal.models.User;
import com.bishal.repository.UserRepository;
import com.bishal.request.LoginRequest;
import com.bishal.response.AuthResponse;
import com.bishal.service.CustomUserDetailsService;
import com.bishal.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {
		
		User isExist=userRepository.findByEmail(user.getEmail());
		
		if(isExist!=null) {
			throw new Exception("email already user");
		}
		
		User newUser=new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		//newUser.setId(user.getId());
		newUser.setGender(user.getGender());
		//newUser.setFollowings(user.getFollowings());
		//newUser.setFollowers(user.getFollowers());
		
		User savedUser=userRepository.save(newUser);
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		
		
		String token=JwtProvider.generateToken(authentication);
		
		AuthResponse res =new AuthResponse(token,"register success");
		return res;
	}
	@PostMapping("/signin")
	public AuthResponse signIn(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication=authenticate(loginRequest.getEmail(),loginRequest.getPassword());
		
        String token=JwtProvider.generateToken(authentication);
		
		AuthResponse res =new AuthResponse(token,"login success");
		return res;
	}

	private Authentication authenticate(String email, String password) {
		UserDetails userDetails=customUserDetailsService.loadUserByUsername(email);
		
		if(userDetails==null) {
			throw new BadCredentialsException("username invalid");
		}
		if(!passwordEncoder.matches(password,userDetails.getPassword())) {
			throw new BadCredentialsException(" invalid password ");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
	}
}
