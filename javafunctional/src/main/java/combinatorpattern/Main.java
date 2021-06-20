package combinatorpattern;

import java.time.LocalDate;

import static combinatorpattern.CustomerRegistrationValidator.*;


public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer(
                "Mario",
                "mario@rossi.it",
                "+393334447869",
                LocalDate.of(2000, 1,1)
        );

//        CustomerValidatorService validatorService = new CustomerValidatorService();
//
//        System.out.println(validatorService.isValid(customer));

        ValidationResult result = isEmailValid()
                .and(isPhoneNumberValid())
                .and(isAnAdult())
                .apply(customer);

        System.out.println(result);
    }
}
