package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.exception.CustomerAlreadyExistsException;
import org.jayhenri.ecommerce.exception.InvalidPostalCodeException;
import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerRegistrationService {


    private final CustomerRepository customerRepository;

    private static final String REGEX_POSTAL_CODE = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";

    @Autowired
    public CustomerRegistrationService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void add(Customer customer) throws CustomerAlreadyExistsException, InvalidPostalCodeException {

        if (existsByPhoneNumber(customer.getPhoneNumber()) || existsByEmail(customer.getEmail()))
            throw new CustomerAlreadyExistsException();

        else if (!isValidPostalCode(customer.getAddress().getPostalCode()))
            throw new InvalidPostalCodeException();
        customerRepository.save(customer);
    }

    public boolean existsByPhoneNumber(String phoneNumber) {
        return customerRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean isValidPostalCode(String postalCode) {
        Pattern pattern = Pattern.compile(REGEX_POSTAL_CODE);
        Matcher matcher = pattern.matcher(postalCode);
        return matcher.matches();
    }

    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }
}