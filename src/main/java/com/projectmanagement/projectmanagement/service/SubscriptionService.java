package com.projectmanagement.projectmanagement.service;

import com.projectmanagement.projectmanagement.model.PlanType;
import com.projectmanagement.projectmanagement.model.Subscription;
import com.projectmanagement.projectmanagement.model.User;

public interface SubscriptionService {

    Subscription createSubscription(User user);

    Subscription getUsersSubscription(Long userId) throws Exception;

    Subscription upgradeSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);
}
