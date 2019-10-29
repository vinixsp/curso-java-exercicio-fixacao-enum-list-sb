package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.OrderStatus;

public class Order {
	private Date moment;
	private OrderStatus status;
	
	private List<OrderItem> items = new ArrayList<>();
	private Client client;
	
	private static SimpleDateFormat sdf_c = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat sdf_o = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
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
		sb.append(sdf_o.format(this.getMoment()) + "\n");
		sb.append("Order status: ");
		sb.append(this.getStatus() + "\n");
		sb.append("Client: ");
		sb.append(this.getClient().getName());
		sb.append(" (" + sdf_c.format(this.getClient().getBirthDate()) + ") ");
		sb.append("- " + this.getClient().getEmail() + "\n");
		
		for (OrderItem i : items) {
			sb.append(i.getProduct().getName() + ", ");
			sb.append("$" + String.format("%.2f", i.getProduct().getPrice()) + ", ");
			sb.append("Quantity: " + i.getQuantity() + ", ");
			sb.append("Subtotal: " + String.format("%.2f", i.subTotal()) + "\n");
		}
		
		sb.append("Total price: " + String.format("%.2f", this.total()));
		return sb.toString();
	}
}
