import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SearchTest {

	@Test
	void testSearchPart1() {
		try {
			Window window = new Window();
			window.open();
			assertTrue(window.search("call"));
			assertTrue(window.search("of"));
			assertTrue(window.search("duty"));
			assertFalse(window.search("overwatch"));
			assertFalse(window.search("minecraft"));
			assertFalse(window.search("gta"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testSearchPart2() {
		try {
			Window window = new Window();
			window.open();
			assertTrue(window.search("overwatch"));
			assertTrue(window.search("OvErWaTcH"));
			assertTrue(window.search("over"));
			assertFalse(window.search("cod"));
			assertFalse(window.search("minecraft"));
			assertFalse(window.search("gta"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testSearchPart3() {
		try {
			Window window = new Window();
			window.open();
			assertTrue(window.search("mineCraft"));
			assertTrue(window.search("Minecraft"));
			assertTrue(window.search("craft"));
			assertFalse(window.search("overwatch"));
			assertFalse(window.search("call of duty"));
			assertFalse(window.search("gta"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
