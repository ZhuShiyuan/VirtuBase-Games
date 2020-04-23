import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SearchTest {

	@Test
	void testSearchPart1() {
		try {
			Window window = new Window();
			window.open();
			assertTrue("call of duty: modern warfare cod".contains(window.getFakeSearch("call")));
			assertTrue("call of duty: modern warfare cod".contains(window.getFakeSearch("of")));
			assertTrue("call of duty: modern warfare cod".contains(window.getFakeSearch("duty")));
			assertTrue("call of duty: modern warfare cod".contains(window.getFakeSearch("CoD")));
			assertFalse("call of duty: modern warfare cod".contains(window.getFakeSearch("Overwatch")));
			assertFalse("call of duty: modern warfare cod".contains(window.getFakeSearch("minecraft")));
			assertFalse("call of duty: modern warfare cod".contains(window.getFakeSearch("halo")));
			assertTrue(callofduty.isVisible());
			assertFalse(minecraft.isVisible());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testSearchPart2() {
		try {
			Window window = new Window();
			window.open();
			assertTrue("overwatch".contains(window.getFakeSearch("o")));
			assertTrue("overwatch".contains(window.getFakeSearch("over")));
			assertTrue("overwatch".contains(window.getFakeSearch("watch")));
			assertTrue("overwatch".contains(window.getFakeSearch("OvErWatCH")));
			assertFalse("overwatch".contains(window.getFakeSearch("COD")));
			assertFalse("overwatch".contains(window.getFakeSearch("minecraft")));
			assertFalse("overwatch".contains(window.getFakeSearch("halo")));
			assertTrue(overwatch.isVisible());
			assertFalse(rocketleague.isVisible());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testSearchPart3() {
		try {
			Window window = new Window();
			window.open();
			assertTrue("gta grand theft auto".contains(window.getFakeSearch("gta")));
			assertTrue("gta grand theft auto".contains(window.getFakeSearch("g")));
			assertTrue("gta grand theft auto".contains(window.getFakeSearch("gta grand theft auto")));
			assertTrue("gta grand theft auto".contains(window.getFakeSearch("GTA GRAND")));
			assertFalse("gta grand theft auto".contains(window.getFakeSearch("pong")));
			assertFalse("gta grand theft auto".contains(window.getFakeSearch("minecraft")));
			assertFalse("gta grand theft auto".contains(window.getFakeSearch("halo")));
			assertTrue(gta.isVisible());
			assertFalse(callofduty.isVisible());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
