/*
#1
@author: Hannah Kennedy @mindlessroman
@due_date: Spr 2017
*/
import java.util.*;
public class Customer {
	private static Scanner input = new Scanner(System.in);

	/**
	 * Instance variables
	 */
	private String customerId;
	private String firstName;
	private String lastName;
	private int age;
	private double income;
	private int creditScore;
	private static ArrayList customerList = new ArrayList();

	
	public static void main (String[] args) {
		int response = 0;
		do {
			System.out.println("Choose an option!");
			System.out.println("1. Add a customer");
			System.out.println("2. Remove a customer");
			System.out.println("3. Update age");
			System.out.println("4. Update income");
			System.out.println("5. Update credit score");
			System.out.println("6. Display all customers");
			System.out.println("7. Exit");
			System.out.print("Please enter option > ");
			String response_string = input.nextLine();
			response = Integer.parseInt(response_string);
			System.out.print("You selected " + response);

			switch (response) {
				case 1:
					option1();
					break;
				case 2:
					option2();
					break;
				case 3:
					option3(); 
					break;
				case 4:
					option4();
					break;
				case 5:
					option5();
					break;
				case 6:
					option6();
					break;
			}
			System.out.println("========================================================");
		} while (response != 7);
	}

	/**
	 * Customer()
	 *
	 * Constructor class.
	 *
	 * @param String in_customerId
	 * @param String in_firstName
	 * @param String in_lastName
	 * @param int in_age
	 * @param double in_income
	 * @param int in_creditScore
	 */
 	public Customer (String in_customerId, String in_firstName, String in_lastName, int in_age, double in_income, int in_creditScore) {
 		customerId = in_customerId;
 		firstName = in_firstName;
 		lastName = in_lastName;
 		age = in_age;
 		income = in_income;
 		creditScore = in_creditScore;
 	}


	/**
	 * toString()
	 *
	 * @return String formatted with customer's ID, name, age, income, and credit score
	 */
 	public String toString () {
		return "\nCustomer ID: " + getCustomerId() + "\nFirst Name: " + getFirstName() + "\nLast Name: " + getLastName() + "\nAge: " + getAge() + "\nIncome: " + getIncome() + "\nCredit Score: " + getCreditScore() + "\n";
	}


	/**
	 * findCustomer()
	 *
	 * Find a customer based their customer ID. Returns the index if the customer is found,
	 *   returns -1 if not found.
	 *
	 * @param String in_customerId
	 * @return int cuCount or notFound
	 */
	public static int findCustomer(String in_customerId) {
		int cuCount = 0;
		int notFound = -1;
		while(cuCount < customerList.size() && !customerList.isEmpty()) {
			Customer tester = (Customer) customerList.get(cuCount);
			String testCustomer = tester.getCustomerId();
			if (testCustomer.equals(in_customerId)) {
				return cuCount;
			}
			cuCount++;
		}
		return notFound;
	}


	/**
	 * option1()
	 *
	 * Helper class fow when use selects option 1 - Add a customer
	 */
	public static void option1() {
		//Prompts the user to enter customer id, and reads it. 
		System.out.print("Enter customer ID > ");
		String in_customerId = input.nextLine();
		//Prompts the user to enter first name, and reads it.
		System.out.print("Enter customer's first name > ");
		String in_firstName = input.nextLine();
		//Prompts the user to enter last name, and reads it. 
		System.out.print("Enter customer's last name > ");
		String in_lastName = input.nextLine();
		//Prompts the user to enter age, and reads it. 
		System.out.print("Enter customer's age > ");
		String in_age_string = input.nextLine();
		int in_age = Integer.parseInt(in_age_string);
		//Prompts the user to enter income, and reads it.
		System.out.print("Enter customer's income > ");
		String in_income_string = input.nextLine();
		double in_income = Double.parseDouble(in_income_string);
		//Prompts the user to enter credit score, and reads it. 
		System.out.print("Enter customer's credit score > ");
		String in_creditScore_string = input.nextLine();
		int in_creditScore = Integer.parseInt(in_creditScore_string);
		//Creates a customer object and adds it to your data structure. 
		Customer insert_cust = new Customer(in_customerId, in_firstName, in_lastName, in_age, in_income, in_creditScore);
		//if the Customer ID doesn't exist, add it
		//if it does... discard 
		if(findCustomer(insert_cust.getCustomerId()) == -1) {
			customerList.add(insert_cust);
		} else {
			System.out.println("This customer ID already exists. Discarding attempted insert.");
		}
		//Displays the main menu.
 	}


	/**
	 * option2()
	 *
	 * Helper class for when the user selects option 2 - Remove a customer
	 *
	 * If there exists a customer with that Customer ID, removes the Customer Object from the ArrayList.
	 *   Otherwise, prints a message about non-existent customer.
	 *
	 *   Returns to menu.
	 */
 	public static void option2() {
 		//Prompts the user to enter a customer id, and reads it. 
		System.out.print("Enter a customer ID to remove > ");
		String in_customerId = input.nextLine();
		//Removes the customer with the given customer id from your data structure. 
		System.out.println("........................................");
		int test_index = findCustomer(in_customerId);
		if(test_index >= 0){
			customerList.remove(test_index);
			System.out.println("Removed customer ID " + in_customerId);
		} else {
			System.out.println("The enter customer ID " + in_customerId + " does not exist");
		}
		System.out.println("........................................");
 		//Displays the main menu. 
 	}


	/**
	 * option3()
	 *
	 * Helper class for when the user selects option 3 - update customer's age
	 *
	 * If there exists a customer with that Customer ID, swaps in the inputted age to the Customer Object
	 *   from the ArrayList. Otherwise, prints a message about non-existent customer.
	 *
	 *   Returns to menu.
	 */
 	public static void option3() {
 		//Prompts the user to enter a customer id, and reads it. 
		System.out.print("Enter a customer ID to update age > ");
		String in_customerId = input.nextLine();
		//Prompts a new age, and reads it
		System.out.print("Enter a new age for customer ID " + in_customerId + " > ");
		String in_age = input.nextLine();
		//Updates the age of the customer in your data structure.
		int age_index = findCustomer(in_customerId);
		if (age_index >= 0){
			Customer insertAge = (Customer) customerList.get(age_index);
			int newAge = Integer.parseInt(in_age);
			insertAge.setAge(newAge);
			customerList.set(age_index, insertAge);
		} else {
			System.out.println("Cannot update age on non-existent customer.");
		}
		//Displays the main menu.  
 	}


	/**
	 * option4()
	 *
	 * Helper class for when the user selects option 4 - update income
	 *
	 * If there exists a customer with that Customer ID, swaps in the inputted income to the
	 *   Customer Object from the ArrayList. Otherwise, prints a message about non-existent
	 *   customer.
	 *
	 *   Returns to menu.
	 */
 	public static void option4() {
		//Prompts the user to enter a customer id, and reads it. 
		System.out.print("Enter a customer ID to update income > ");
		String in_customerId = input.nextLine();
		//Prompts a new income, and reads it 
		System.out.print("Enter a new income for customer ID " + in_customerId + " > ");
		String in_income = input.nextLine();
		//Updates the income of the customer in your data structure. 
		int income_index = findCustomer(in_customerId);
		if (income_index >= 0){
			Customer insertIncome = (Customer) customerList.get(income_index);
			double newIncome = Double.parseDouble(in_income);
			insertIncome.setIncome(newIncome);
			customerList.set(income_index, insertIncome);
		} else {
			System.out.println("Cannot update income on non-existant customer.");
		}
		//Displays the main menu.  
 	}


	/**
	 * option5()
	 *
	 * Helper class for when the user selects option 5 - update credit score
	 *
	 * If there exists a customer with that Customer ID, swaps in the inputted credit score to
	 *   the Customer Object from the ArrayList. Otherwise, prints a message about non-existent
	 *   customer.
	 *
	 *   Returns to menu.
	 */
 	public static void option5() {
		//Prompts the user to enter a customer id, and reads it.
		System.out.print("Enter a customer ID to update credit score > ");
		String in_customerId = input.nextLine();
		//Prompts a new credit score, and reads it
		System.out.print("Enter a new credit score for customer ID " + in_customerId + " > ");
		String in_creditScore = input.nextLine();
		//Updates the credit score of the customer in your data structure.
		int credit_index = findCustomer(in_customerId);
		if (credit_index >= 0){
			Customer insertCreditScore = (Customer) customerList.get(credit_index);
			int newCredit = Integer.parseInt(in_creditScore);
			insertCreditScore.setCreditScore(newCredit);
			customerList.set(credit_index, insertCreditScore);
		} else {
			System.out.println("Cannot update credit score on non-existant customer.");
		}
		//Displays the main menu
 	}

 	/*
	option6()
	Breaks out the code from main() for clarity. Runs through the request of changing a specified customer’s credit score. 

	Postcondition: If there exists a customer with that Customer ID, swaps in the inputted credit score to the Customer Object from the ArrayList. Otherwise, prints a message about non-existent customer. Returns to menu.
 	*/

	/**
	 * option6()
	 *
	 * Helper class for when the user selects option 6 - display customers
	 *
	 */
 	public static void option6() {
		//prints all the things
 		System.out.println(customerList);
 	}


	/**
	 * Get and Set methods for customer instance variables
	 *
	 */
	//get customer id
	public String getCustomerId() {
		return customerId;
	}
	//set customer id
	public void setCustomerId(String in_customerId) {
		customerId = in_customerId;
	}

	//get firstname
	public String getFirstName() {
		return firstName;
	}
	//set firstname
	public void setFirstName(String in_firstName) {
		firstName = in_firstName;
	}

	//get lastname
	public String getLastName() {
		return lastName;
	}
	//set lastname
	public void setLastName(String in_lastName) {
		lastName = in_lastName;
	}

	//get age
	public int getAge() {
		return age;
	}
	//set age -- throws an exception if the age is negative
	public void setAge(int in_age) {
		if(in_age < 0){
			throw new IllegalArgumentException("Age < 0: " + in_age);
		} else {
			age = in_age;
		}
	}

	//get income
	public double getIncome() {
		return income;
	}
	//set income -- throws an exception if the income is negative
	public void setIncome(double in_income) {
		if(in_income < 0){
			throw new IllegalArgumentException("Income < 0: " + in_income);
		} else {
			income = in_income;
		}
	}

	//get credit score
	public int getCreditScore() {
		return creditScore;
	}
	//set credit score -- throws an exception if the score is less than 0 or more than 1000
	public void setCreditScore(int in_creditScore) {
		if(in_creditScore < 0 && in_creditScore > 1000){
			throw new IllegalArgumentException("Credit score outside of acceptable range: " + in_creditScore);
		} else {
			creditScore = in_creditScore;
		}
	}
}