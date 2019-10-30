package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.OrderStatus;

public class Order {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	private Date moment;
	private OrderStatus status;
	
	private List<OrderItem> items = new ArrayList<>();
	private Client client;
	
	public Order() {
	}

	public Order(Date moment, OrderStatus status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getMoment() {
		return moment;
	}

	public List<OrderItem> getItems() {
		return items;
	}
	
	public void addItem(OrderItem orderItem) {
		this.items.add(orderItem);
	}
	
	public void removeItem(OrderItem orderItem) {
		this.items.remove(orderItem);
	}
	
	public Double total() {
		Double total = 0d;
		for (OrderItem item : items) {
			total += item.subTotal();
		}
		return total;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("ORDER SUMMARY:\n");
		sb.append("Order moment: ");
		sb.append(sdf.format(moment) + "\n");
		sb.append("Order status: ");
		sb.append(status + "\n");
		sb.append("Client: ");
		sb.append(client + "\n");
		
		for (OrderItem orderItem : items) {
			sb.append(orderItem);
		}
		
		sb.append("Total price: " + String.format("%.2f", total()));
		return sb.toString();
	}
}
