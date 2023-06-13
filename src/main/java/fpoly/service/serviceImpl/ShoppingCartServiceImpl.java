package fpoly.service.serviceImpl;



import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import fpoly.entity.CartItem;
import fpoly.service.ShoppingCartService;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	Map<Integer, CartItem> maps = new HashMap<>();


	@Override
	public void add(CartItem item) {
		CartItem cartItem = maps.get(item.getProductId());
		if(cartItem == null) {
			maps.put(item.getProductId(), item);
		}else {
			cartItem.setQty(cartItem.getQty() +1);
		}
		
	}

	
	@Override
	public void remove(int id) {
		maps.remove(id);
		
	}

	
	@Override
	public CartItem update(int proID, int qty) {
		CartItem item = maps.get(proID);
		item.setQty(qty);
		return item;
	}

	
	@Override
	public void clear() {
		maps.clear();
		
	}

	
	@Override
	public Collection<CartItem> getAllItem() {
		
		return maps.values();
	}

	
	@Override
	public int getCount() {
		
		return maps.values().size();
	}

	
	@Override
	public double getAmount() {
		
		return maps.values().stream().mapToDouble(item -> item.getQty()* item.getPrice()).sum();
	}
	
	
}
