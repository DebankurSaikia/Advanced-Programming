import java.util.*;


interface PaymentMethod {
    void pay(double amount);
}

class CreditCardPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs. " + amount + " using Credit Card");
    }
}

class UPIPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs. " + amount + " using UPI");
    }
}

class WalletPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs. " + amount + " using Wallet");
    }
}



interface NotificationService {
    void sendNotification(String message);
}

class EmailNotification implements NotificationService {

    @Override
    public void sendNotification(String message) {
        System.out.println("Email sent: " + message);
    }
}

class SMSNotification implements NotificationService {

    @Override
    public void sendNotification(String message) {
        System.out.println("SMS sent: " + message);
    }
}

class PushNotification implements NotificationService {

    @Override
    public void sendNotification(String message) {
        System.out.println("Push Notification: " + message);
    }
}



interface StorageService {
    void save(Order order);
}

class DatabaseStorage implements StorageService {

    @Override
    public void save(Order order) {
        System.out.println("Saved in Database: " + order);
    }
}

class FileStorage implements StorageService {

    @Override
    public void save(Order order) {
        System.out.println("Saved in File: " + order);
    }
}



abstract class Order {

    protected int orderId;
    protected double amount;

    public Order(int orderId, double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Order amount must be positive"
            );
        }

        this.orderId = orderId;
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public abstract double calculateFinalAmount();

    @Override
    public String toString() {
        return "Order ID = " + orderId +
                ", Type = " + getClass().getSimpleName() +
                ", Final Amount = Rs. " + calculateFinalAmount();
    }
}



class RegularOrder extends Order {

    public RegularOrder(int orderId, double amount) {
        super(orderId, amount);
    }

    @Override
    public double calculateFinalAmount() {
        return amount;
    }
}



class DiscountedOrder extends Order {

    public DiscountedOrder(int orderId, double amount) {
        super(orderId, amount);
    }

    @Override
    public double calculateFinalAmount() {
        return amount * 0.9;
    }
}



class PriorityOrder extends Order {

    public PriorityOrder(int orderId, double amount) {
        super(orderId, amount);
    }

    @Override
    public double calculateFinalAmount() {
        return amount + 100; 
    }
}



class OrderService {

    private PaymentMethod paymentMethod;
    private NotificationService notificationService;
    private StorageService storageService;

    
    public OrderService(
            PaymentMethod paymentMethod,
            NotificationService notificationService,
            StorageService storageService
    ) {

        this.paymentMethod = paymentMethod;
        this.notificationService = notificationService;
        this.storageService = storageService;
    }


    public void processOrder(Order order) {

        System.out.println("\nProcessing " + order);

        double finalAmount = order.calculateFinalAmount();

        paymentMethod.pay(finalAmount);

        notificationService.sendNotification(
                "Order " + order.getOrderId() +
                        " processed successfully"
        );

        storageService.save(order);
    }
}


public class EcommerceSystem {

    public static void main(String[] args) {

       
        Order order1 = new RegularOrder(101, 1000);
        Order order2 = new DiscountedOrder(102, 1000);
        Order order3 = new PriorityOrder(103, 1000);


      
        OrderService service1 = new OrderService(
                new CreditCardPayment(),
                new EmailNotification(),
                new DatabaseStorage()
        );

        OrderService service2 = new OrderService(
                new UPIPayment(),
                new SMSNotification(),
                new FileStorage()
        );

        OrderService service3 = new OrderService(
                new WalletPayment(),
                new PushNotification(),
                new DatabaseStorage()
        );


        service1.processOrder(order1);
        service2.processOrder(order2);
        service3.processOrder(order3);
    }
}
