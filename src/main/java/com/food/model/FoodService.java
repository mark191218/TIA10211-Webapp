package com.food.model;

import java.util.List;

public class FoodService {
	
	private FoodDAO_interface dao;
	
	public FoodService() {
		dao =  new FoodJDBCDAO();
	}
	
	public FoodVO addFood(Integer foodNumber, Integer foodTypeNumber, 
			String foodName, Integer foodCalories) {
		FoodVO foodVO = new FoodVO();
		foodVO.setFoodNumber(foodNumber);
		foodVO.setFoodTypeNumber(foodTypeNumber);
		foodVO.setFoodName(foodName);
		foodVO.setFoodCalories(foodCalories);
		dao.insert(foodVO);
		
		return foodVO;
	}
	
	public FoodVO updateFood(Integer foodNumber, Integer foodTypeNumber, 
			String foodName, Integer foodCalories) {
		FoodVO foodVO = new FoodVO();
		foodVO.setFoodNumber(foodNumber);
		foodVO.setFoodTypeNumber(foodTypeNumber);
		foodVO.setFoodName(foodName);
		foodVO.setFoodCalories(foodCalories);
		dao.update(foodVO);
		
		return foodVO;
	}
	
	public FoodVO getOneFood(Integer foodNumber) {
		return dao.getOne(foodNumber);
	}
	
	public void deleteFood(Integer foodNumber) {
		dao.delete(foodNumber);
	}
	
	public List<FoodVO> getAll(){
		return dao.getAll();
	}
	
	
}
