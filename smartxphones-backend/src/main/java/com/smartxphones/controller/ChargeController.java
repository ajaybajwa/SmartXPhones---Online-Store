package com.smartxphones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartxphones.model.ChargeRequest;
import com.smartxphones.model.ChargeRequest.Currency;
import com.smartxphones.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@CrossOrigin(origins = {"http://localhost:4201","smartxstore.azurewebsites.net" })
@RestController
@RequestMapping("/stripe")
public class ChargeController {

	String STRIPE_SK= "sk_test_51LflSODncq9KJa81wkyR9lv5ThzXjDzFgDBXqKpMpNnioBJreCd4VbnTrZQHjRBCIA5u953LQl8kg2pbwFnUvnR6001FXYongm";
	String STRIPE_PK= "pk_test_51LflSODncq9KJa816vSKNjdic3V39Y1HZ0ZvX4TmkpoqUdMGFmb9xBklhywlxvwcFPqyiKn0xOlxou0NJkaORrnw00SACxUFmk";
	
    @Autowired
    private StripeService paymentsService;

    @PostMapping("/charge")
    public void charge(@RequestBody ChargeRequest cr, Model model)
      throws StripeException {
        cr.setDescription("Order");
        cr.setCurrency(Currency.USD);
        Charge charge1 = paymentsService.charge(cr);
        model.addAttribute("id", charge1.getId());
        model.addAttribute("status", charge1.getStatus());
        model.addAttribute("chargeId", charge1.getId());
    }

    @ExceptionHandler(StripeException.class)
    public void handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
    }
}