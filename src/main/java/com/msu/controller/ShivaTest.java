package com.msu.controller;

import java.util.HashMap;

public class ShivaTest {

	public static void main(String[] args) {

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		map.put(0, map.getOrDefault(0, 1) + 1);
		map.put(0, map.getOrDefault(0, 1) + 1);

		System.out.println(map.get(0));

	}

}
