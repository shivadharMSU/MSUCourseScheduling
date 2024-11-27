package com.msu.services;

import java.util.List;

import com.msu.entities.User;

public interface UserService {

	public List<User> findAll();

	public void saveUser(User user);

}
