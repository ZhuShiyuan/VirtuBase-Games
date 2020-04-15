import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.jupiter.api.Test;

class StorefrontTest {

	@Test
	void testGetName() throws MalformedURLException {
		Storefront tester = new Storefront("Minecraft", 19.99, new URL("https://www.minecraft.net/en-us/"));

		assertFalse(tester.getName().equals("Call of Duty"));
		assertTrue(tester.getName().equals("Minecraft"));
	}

	@Test
	void testGetPrice() throws MalformedURLException {
		Storefront tester = new Storefront("Minecraft", 19.99, new URL("https://www.minecraft.net/en-us/"));

		assertTrue(tester.getPrice() == 19.99);
		assertFalse(tester.getPrice() == 20.00);
	}

	@Test
	void testGetLink() throws MalformedURLException {
		Storefront tester = new Storefront("Minecraft", 19.99, new URL("https://www.minecraft.net/en-us/"));

		assertEquals(new URL("https://www.minecraft.net/en-us/"), tester.getLink());
	}

	@Test
	void testSetName() throws MalformedURLException {
		Storefront tester = new Storefront("Minecraft", 19.99, new URL("https://www.minecraft.net/en-us/"));

		assertTrue(tester.getName().equals("Minecraft"));
		tester.setName("NotMinecraft");
		assertFalse(tester.getName().equals("Minecraft"));
		assertTrue(tester.getName().equals("NotMinecraft"));
	}

	@Test
	void testSetPrice() throws MalformedURLException {
		Storefront tester = new Storefront("Minecraft", 19.99, new URL("https://www.minecraft.net/en-us/"));

		assertTrue(tester.getPrice() == 19.99);
		tester.setPrice(20.00);
		assertFalse(tester.getPrice() == 19.99);
		assertTrue(tester.getPrice() == 20.00);
	}

	@Test
	void testSetLink() throws MalformedURLException {
		Storefront tester = new Storefront("Minecraft", 19.99, new URL("https://www.minecraft.net/en-us/"));

		assertEquals(new URL("https://www.minecraft.net/en-us/"), tester.getLink());
		tester.setLink(new URL("https://www.google.com/"));
		assertEquals(new URL("https://www.google.com/"), tester.getLink());
	}

	@Test
	void testPrepForFile() throws MalformedURLException {
		Storefront tester = new Storefront("Minecraft", 19.99, new URL("https://www.minecraft.net/en-us/"));

		assertTrue(tester.prepForFile().equals("Minecraft,19.99,https://www.minecraft.net/en-us/"));
	}

}
