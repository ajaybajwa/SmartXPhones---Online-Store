package com.smartxphones.service;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import com.smartxphones.model.ChargeRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Service
public class StripeService {
	
	String STRIPE_SK= "sk_test_51Lfut9FWtWq7CKjzfmIL3WH7vM4TK8plCAMzurKo875q59yUTDUwSP1RoZIeRwnhxjdPdVjyBeM7qKQWZYspagPs0008hbOqQq";
	String STRIPE_PK= "pk_test_51Lfut9FWtWq7CKjzYCANqmZVQDQhrQMvC5aev5wFFzEP6CS0PaBPenTrCgPmA6aUISFbaJ9vVnD7p67rlQislDoY00p8ggrGv4";

    @PostConstruct
    public void init() {
        Stripe.apiKey = STRIPE_SK;
    }

	public Charge charge(ChargeRequest cr) throws StripeException {
		Map<String, Object> chargeParams = new HashMap<>();
		chargeParams.put("amount", cr.getAmount() * 100);
		chargeParams.put("currency", cr.getCurrency());
		chargeParams.put("description", cr.getDescription());
		chargeParams.put("source", cr.getSource());
		
		return Charge.create(chargeParams);
	}

}