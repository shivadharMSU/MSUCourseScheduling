package com.msu.controller;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import jakarta.persistence.criteria.CriteriaBuilder.In;

public class ShivaTest {
	
	
	public static void main(String[] args) {
		
		//PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b)->b-a);
		HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
		
		map.put(0, map.getOrDefault(0, 1)+1);
		map.put(0, map.getOrDefault(0, 1)+1);
		
		
		System.out.println(map.get(0));
		
		
	   
	}

}
