package delivery.service.main.service;

import java.util.HashMap;
import java.util.Map;

public class DiscountInAction {
	private static final Map<String, IOfferRule> offers = new HashMap<>();

    static {
        register(new OFR001());
        register(new OFR002());
        register(new OFR003());
    }

    public static void register(IOfferRule offer) {
        offers.put(offer.getCode(), offer);
    }

    public static IOfferRule getOffer(String code) {
        return offers.get(code);
    }
}
