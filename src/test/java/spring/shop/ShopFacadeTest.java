package spring.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import spring.shop.entity.Product;
import spring.shop.facede.ShopFacade;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ShopFacadeTest {


	@Autowired
	private ShopFacade shopFacade;

	@Test
	public void contextLoads() {
		Product product = shopFacade.getProductById(1);
		assertEquals(product.getId(), new Integer(1));
	}

}
