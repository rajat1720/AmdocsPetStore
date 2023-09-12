package com.amdocs.petsearch.main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.amdocs.petsearch.model.*;
import com.amdocs.petsearch.doa.*;
import com.amdocs.petsearch.exception.*;
import java.sql.*;

public class Main {

	public static void main(String[] args) throws PetException {
		// TODO Auto-generated method stub
		
//		sc.close();
		DatabaseManager databaseManager = new DatabaseManager();
		PetDOA petDOA = new PetDOA();
		while(true) {
			System.out.println("Enter Your Choice \n");
	        System.out.println("1 Add new pet details");
	        System.out.println("2 Delete pet details");
	        System.out.println("3 Update pet price and Vaccination Status");
	        System.out.println("4 View all pets");
	        System.out.println("5 Count pets by category");
	        System.out.println("6 Find by price");
	        System.out.println("7 Find by vaccination status for pet type");
	        System.out.println("8 Exit");
	        
	        Scanner sc = new Scanner(System.in);
			int option = sc.nextInt();
		switch(option) {
		case 1:
			System.out.println("Add new pet details");
	        	PetInput test = new PetInput();
	            PetClass pet =test.takeInput();
				// Insert pet data into the database using the PetDataInserter class
			try {
				petDOA.addPetDetails(pet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				try {
				throw new PetException("Cannot add details");
				}
				catch(PetException e1) {break;}
			}
			break;
		case 2:
			System.out.println("Delete pet details");
			Scanner sc1 = new Scanner(System.in);
	    	System.out.println("Enter Pet Id");
	    	int id = sc1.nextInt();
//	    	sc1.close();
			try {
				petDOA.deletePetDetails(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				try {throw new PetException("Cannot delete pet details");}
				catch(PetException e1) {break;}
				
			}
			break;
		case 3:
			System.out.println("Update pet price and Vaccination Status");
//	        	PetInput PetObj = new PetInput();
//	            PetClass pet2 =PetObj.UpdateInput();
	            Scanner sc4 = new Scanner(System.in);
	        	System.out.println("Enter Pet Id");
	        	int petid = sc4.nextInt();
	        	System.out.println("Enter pet Price");
	    		double PetPrice = sc4.nextDouble();
	    		System.out.println("Enter if pet is vaccinated or not");
	    		boolean PetisVaccinated = sc4.nextBoolean();
			try {
				petDOA.updatePetPriceAndVaccinationStatus(petid, PetPrice,PetisVaccinated);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				try {throw new PetException("Cannot update price and vaccination details");}
				catch(PetException e1) {break;}
			}
			break;
		case 4:
			System.out.println("View all pets");
			List<PetClass> pets = new ArrayList<>();
			try {
				pets = petDOA.showAllPets();
				System.out.println(pets.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				try {throw new PetException("Cannot view all pet details");}
				catch(PetException e1) {break;}
			}
			break;
		case 5:
			System.out.println("Count pets by category");
	        Scanner sc2 = new Scanner(System.in);
	        System.out.println("Enter Pet category");
	        String petCategory = sc2.next();
//	        sc2.close();
			try {
				int count = petDOA.countPetsByCategory(petCategory);
				System.out.println("Count of pets in category '" + petCategory + "': " + count);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				try{ throw new PetException("Cannot count pet by category");}
				catch(PetException e1) {break;}
			}
			break;
		case 6:
			System.out.println("Find by price");
			System.out.println("Enter Max Price");
			Scanner sc3 = new Scanner(System.in);
	    	double max = sc3.nextDouble();
	    	System.out.println("Enter Min Price");
	    	double min = sc3.nextDouble();
//	    	sc3.close();
	    	List<PetClass> pets2 = new ArrayList<>();
			try {
				pets2 = petDOA.searchByPrice(min,max);
				System.out.println(pets2.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				try{throw new PetException("Cannot find pet by price");}
				catch(PetException e1) {break;}
			}
	        
			break;
		case 7:
			System.out.println("Find by vaccination status for pet type");  
			Scanner sc5 = new Scanner(System.in);
			String petType = sc5.next();
//			List<PetClass> pets3 = new ArrayList<>();
			Map<String, Integer> countsByStatus = new HashMap<>();
			try {
				countsByStatus = petDOA.countByVaccinationStatusForPetType(petType);
				System.out.println(countsByStatus.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				try {throw new PetException("Cannot find pet type by Vaccination status");}
				catch(PetException e1) {break;}
			}
			break;
		case 8:
			System.out.println("Exit");
			System.exit(0);
			break;
		default:
			System.out.println("Please choose correct option");
		}
		}
	}

}
