package com.cybage.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dao.FoodMenusDao;
import com.cybage.dao.RestaurantDao;
import com.cybage.dao.UserDao;
import com.cybage.model.FoodMenus;
import com.cybage.model.Restaurant;
import com.cybage.model.User;

@Service
public class ExportService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private FoodMenusDao foodMenusDao;

	@Autowired
	private RestaurantDao restaurantDao;

	static Logger logger = LogManager.getLogger(ExportService.class);

	public void printUser() {
		HSSFWorkbook workbook = new HSSFWorkbook();

		// Create a blank sheet
		HSSFSheet sheet = workbook.createSheet("User Data");

		List<User> userData = userDao.findAll();
		System.out.println("users: " + userData);

		Map<Integer, User> data = new TreeMap<>();

		// userData.stream().map((user)->data.put(user.getUserId(),user));
		for (User user : userData) {
			data.put(user.getUserId(), user);
		}
		System.out.println("user data: " + data);

		Set<Integer> keyset = data.keySet();

		int rownum = 0;
		for (Integer key : keyset) {
			Row row = sheet.createRow(rownum++);
			User user = data.get(key);

			Cell cell = row.createCell(1);
			cell.setCellValue(user.getUserId());
			Cell cell1 = row.createCell(2);
			cell1.setCellValue(user.getUserName());
			Cell cell2 = row.createCell(3);
			cell2.setCellValue(user.getUserEmail());
			Cell cell3 = row.createCell(4);
			cell3.setCellValue(user.getUserMobileNo());
			Cell cell4 = row.createCell(5);
			cell4.setCellValue(user.getAttemptsCount());
		}
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("User_Details.xls"));
			workbook.write(out);
			out.close();
			logger.info("User_Details.xls written successfully on disk.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printFoodItemData() {
		HSSFWorkbook workbook = new HSSFWorkbook();

		// Create a blank sheet
		HSSFSheet sheet = workbook.createSheet("Food Item Data");

		List<FoodMenus> foodItemList = foodMenusDao.findAll();

		Map<Integer, FoodMenus> data = new TreeMap<>();

		// userData.stream().map((user)->data.put(user.getUserId(),user));
		for (FoodMenus foodMenu : foodItemList) {
			data.put(foodMenu.getFoodId(), foodMenu);
		}

		Set<Integer> keyset = data.keySet();

		int rownum = 0;
		for (Integer key : keyset) {
			Row row = sheet.createRow(rownum++);
			FoodMenus foodMenu = data.get(key);

			Cell cell = row.createCell(1);
			cell.setCellValue(foodMenu.getFoodId());
			Cell cell1 = row.createCell(2);
			cell1.setCellValue(foodMenu.getFoodName());
			Cell cell2 = row.createCell(3);
			cell2.setCellValue(foodMenu.getFoodCategory());
			Cell cell3 = row.createCell(4);
			cell3.setCellValue(foodMenu.getPrice());
			Cell cell4 = row.createCell(5);
			cell4.setCellValue(foodMenu.getOffer());
			Cell cell5 = row.createCell(6);
			cell5.setCellValue(foodMenu.getRestaurant().getRestaurantName());
		}
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("Food_Item_Details.xls"));
			workbook.write(out);
			out.close();
			logger.info("Food_Item_Details.xls written successfully on disk.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printRestaurantData() {
		HSSFWorkbook workbook = new HSSFWorkbook();

		// Create a blank sheet
		HSSFSheet sheet = workbook.createSheet("Restaurant Data");

		List<Restaurant> restaurantList = restaurantDao.findAll();

		Map<Integer, Restaurant> data = new TreeMap<>();

		// userData.stream().map((user)->data.put(user.getUserId(),user));
		for (Restaurant restaurant : restaurantList) {
			data.put(restaurant.getRestId(), restaurant);
		}

		Set<Integer> keyset = data.keySet();

		int rownum = 0;
		for (Integer key : keyset) {
			Row row = sheet.createRow(rownum++);
			Restaurant restaurant = data.get(key);

			Cell cell = row.createCell(1);
			cell.setCellValue(restaurant.getRestId());
			Cell cell1 = row.createCell(2);
			cell1.setCellValue(restaurant.getRestaurantName());
			Cell cell2 = row.createCell(3);
			cell2.setCellValue(restaurant.getRestaurantEmail());
			Cell cell3 = row.createCell(4);
			cell3.setCellValue(restaurant.getRestaurantUserName());
			// Cell cell4 = row.createCell(5);
			// cell4.setCellValue(restaurant.getAddress().toString());
		}
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("Restaurant_Details.xls"));
			workbook.write(out);
			out.close();
			logger.info("Restaurant_Details.xls written successfully on disk.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
