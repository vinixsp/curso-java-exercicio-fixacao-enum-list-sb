package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf_c = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Enter client data:");
		System.out.print("Name: ");
		String name = sc.nextLine();
		
		System.out.print("e-mail: ");
		String email = sc.nextLine();
		
		System.out.print("Birth date (DD/MM/YYYY): ");
		Date birthDate = sdf_c.parse(sc.nextLine());
		
		Client client = new Client(name, email, birthDate);
		
		System.out.println("Enter order data: ");
		System.out.print("Status: ");
		String status = sc.nextLine();
		
		Order order = new Order(new Date(), OrderStatus.valueOf(status), client);
		
		System.out.print("How many items to this order? ");
		int numItems = sc.nextInt();
		sc.nextLine();
		
		for (int i=1; i<=numItems; i++) {
			System.out.println("Enter #" + i + " item data:");
			System.out.print("Product name: ");
			String pName = sc.nextLine();

			System.out.print("Product price: ");
			Double pPrice = sc.nextDouble();
			
			System.out.print("Quantity: ");
			int pQuantity = sc.nextInt();
			sc.nextLine();
			
			Product p = new Product(pName, pPrice);
			OrderItem o = new OrderItem(pQuantity, p);
			
			order.addItem(o);
		}
		
		System.out.println(order);

		sc.close();
	}

}
