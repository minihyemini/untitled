package jisungsoft.com.mes.sales.rod.validation;

import jisungsoft.com.persistence.dto.mes.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class OrderFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Order.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Order order = (Order) target;

        if (order.getCltId() == null) {
            errors.rejectValue("cltId", "required", "required");
        }
    }

}
