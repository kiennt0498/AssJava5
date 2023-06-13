package fpoly.service;

import java.util.Collection;

import fpoly.entity.CartItem;

public interface ShoppingCartService {

	double getAmount();

	int getCount();

	Collection<CartItem> getAllItem();

	void clear();

	CartItem update(int proID, int qty);

	void remove(int id);

	void add(CartItem item);

	
	
	
}
