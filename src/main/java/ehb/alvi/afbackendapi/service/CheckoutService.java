package ehb.alvi.afbackendapi.service;


import ehb.alvi.afbackendapi.dto.Purchase;
import ehb.alvi.afbackendapi.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
