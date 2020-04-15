import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KeywordIndexTest {

	@Test
	void testGetKeywordList() {
		KeywordIndex tester = new KeywordIndex();

		tester.readList(new String[] { "TypeOne", "TypeTwo", "TypeThree" });

		assertTrue(tester.getKeywordList().get(0).equals("TypeOne"));
		assertTrue(tester.getKeywordList().get(1).equals("TypeTwo"));
		assertTrue(tester.getKeywordList().get(2).equals("TypeThree"));
	}

	@Test
	void testReadList() {
		KeywordIndex tester = new KeywordIndex();

		tester.readList(new String[] { "TypeOne", "TypeTwo", "TypeThree" });

		assertTrue(tester.getKeywordList().get(0).equals("TypeOne"));
		assertTrue(tester.getKeywordList().get(1).equals("TypeTwo"));
		assertTrue(tester.getKeywordList().get(2).equals("TypeThree"));

	}

	@Test
	void testWriteList() {
		KeywordIndex tester = new KeywordIndex();

		tester.readList(new String[] { "TypeOne", "TypeTwo", "TypeThree" });
		
		String[] testerString = tester.writeList();
		assertTrue(testerString[0].equals("TypeOne"));
		assertTrue(testerString[1].equals("TypeTwo"));
		assertTrue(testerString[2].equals("TypeThree"));
	}

	@Test
	void testAddKeyword() {
		KeywordIndex tester = new KeywordIndex();

		tester.readList(new String[] { "TypeOne", "TypeTwo", "TypeThree" });
		assertFalse(tester.addKeyword("TypeOne"));
		assertTrue(tester.addKeyword("TypeFour"));
	}

	@Test
	void testFindKeyword() {
		KeywordIndex tester = new KeywordIndex();

		tester.readList(new String[] { "TypeOne", "TypeTwo", "TypeThree" });
		
		assertTrue(tester.findKeyword(2).equals("TypeThree"));
		assertTrue(tester.findKeyword(-1).equals(""));
	}
	
	@Test
	void testFindIndex() {
		KeywordIndex tester = new KeywordIndex();

		tester.readList(new String[] { "TypeOne", "TypeTwo", "TypeThree" });
		
		assertEquals(tester.findIndex("TypeOne"), 0);
		assertEquals(tester.findIndex("TypeTwo"), 1);
		assertEquals(tester.findIndex("TypeThree"), 2);
		assertEquals(tester.findIndex(""), -1);
	}

}
