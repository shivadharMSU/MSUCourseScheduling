package com.msu.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.entities.User;
import com.msu.repositories.UserRepository;
import com.msu.services.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{

	 @Autowired
	private UserRepository userRepository;
	 
	 
	@Override
	public List<User> findAll() {
		try {
			return userRepository.findAll();
		}catch(Exception ex) {
			System.out.println("exception while fetching user "+ex);
		}
		return null;
	}

	@Override
	public void saveUser(User user) {
    try {
    	userRepository.save(user);
		}catch(Exception ex) {
			System.out.println("exception while saving user "+ex);
		}		
	}

}
