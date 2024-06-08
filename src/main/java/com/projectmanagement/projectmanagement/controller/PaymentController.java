package com.projectmanagement.projectmanagement.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagement.projectmanagement.model.PlanType;
import com.projectmanagement.projectmanagement.model.User;
import com.projectmanagement.projectmanagement.responce.PaymentLinkResponse;
import com.projectmanagement.projectmanagement.service.UserService;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Value("${rezerpay.api.key}")
    private String apiKey;

    @Value("${rezerpay.api.secret}")
    private String apiSecret;

    @Autowired
    private UserService userService;

    @PostMapping("/{planType}")
    public ResponseEntity<PaymentLinkResponse> createPaymetLink(
            @PathVariable PlanType planType,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);

        int amount = 799 * 100;
        if (planType == PlanType.ANNUALLY) {
            amount = amount * 12;
            amount = (int) (amount * 0.7);
        }

        RazorpayClient razorpayClient = new RazorpayClient(apiKey, apiSecret);

        JSONObject paymentLinkRequest = new JSONObject();

        paymentLinkRequest.put("amount", amount);
        paymentLinkRequest.put("currency", "INR");

        JSONObject customer = new JSONObject();

        customer.put("name", user.getFullName());
        customer.put("email", user.getEmail());

        paymentLinkRequest.put("customer", customer);

        JSONObject notify = new JSONObject();

        notify.put("email", true);
        notify.put("sms", true);

        paymentLinkRequest.put("notify", notify);

        paymentLinkRequest.put("callback_url", "http://localhost:5173/upgrade_plan/success?planType" + planType);

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);

        String paymentLinkId = payment.get("id");

        String paymentLinkUrl = payment.get("short_url");

        PaymentLinkResponse response = new PaymentLinkResponse();

        response.setPayment_link_url(paymentLinkUrl);
        response.setPayment_link_id(paymentLinkId);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

}
