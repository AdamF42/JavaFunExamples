package functionalinterface;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class _Consumer {

    public static void main(String[] args) {

        // Represents an operation that accepts a single input argument and returns no result.
        Customer maria = new Customer("Maria", "99999");
        greetCustomer(maria);

        // Consumer Functional Interface
        greetCustomerConsumer.accept(maria);

        // Represents an operation that accepts two input arguments and returns no result.
        greetCustomerConsumerV2.accept(maria,false);
    }

    static void greetCustomer(Customer customer) {
        System.out.println(" Hello" + customer.customerName +
                ", thanks for registering phone number "
                + customer.customerPhoneNumber);
    }

    static Consumer<Customer> greetCustomerConsumer = customer ->
            System.out.println(" Hello" + customer.customerName +
                    ", thanks for registering phone number "
                    + customer.customerPhoneNumber);

    static BiConsumer<Customer, Boolean> greetCustomerConsumerV2 = (customer, showPhoneNumber) ->
            System.out.println(" Hello" + customer.customerName +
                    ", thanks for registering phone number "
                    + (showPhoneNumber ? customer.customerPhoneNumber: "*******"));

    static class Customer {
        private final String customerName;
        private final String customerPhoneNumber;

        Customer(String customerName, String customerPhoneNumber) {
            this.customerName = customerName;
            this.customerPhoneNumber = customerPhoneNumber;
        }

    }
}
