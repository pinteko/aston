import org.junit.jupiter.api.*;
import org.trainee.CustomArrayList;
import org.trainee.CustomArrayListImpl;

public class CustomArrayListImplTest {

    private CustomArrayList<String> customArrayList;

    @BeforeEach
    void setUp() {
        customArrayList = new CustomArrayListImpl<>();
    }

    @Test
    @DisplayName("Test add method")
    void testAdd() {
        customArrayList.add("Apple");
        customArrayList.add("Banana");
        customArrayList.add("Orange");

        Assertions.assertEquals(3, customArrayList.size());
    }

    @Test
    @DisplayName("Test remove method")
    void testRemove() {
        customArrayList.add("Apple");
        customArrayList.add("Banana");
        customArrayList.add("Orange");

        customArrayList.remove("Banana");

        Assertions.assertEquals(2, customArrayList.size());
        Assertions.assertFalse(customArrayList.contains("Banana"));
    }

    @Test
    @DisplayName("Test clear method")
    void testClear() {
        customArrayList.add("Apple");
        customArrayList.add("Banana");
        customArrayList.add("Orange");

        customArrayList.clear();

        Assertions.assertTrue(customArrayList.isEmpty());
    }

    @Test
    @DisplayName("Test get method")
    void testGet() {
        customArrayList.add("Apple");
        customArrayList.add("Banana");
        customArrayList.add("Orange");

        String element = customArrayList.get(1);

        Assertions.assertEquals("Banana", element);
    }

    @Test
    @DisplayName("Test sort size less 7 method")
    void testSortSizeLessSeven() {
        customArrayList.add("Papaya");
        customArrayList.add("Orange");
        customArrayList.add("Banana");
        customArrayList.add("Apple");
        customArrayList.add("Kiwi");
        customArrayList.add("Melon");

        customArrayList.sort(String::compareTo);

        CustomArrayList<String> expectedSorted = new CustomArrayListImpl<>();
        expectedSorted.add("Apple");
        expectedSorted.add("Banana");
        expectedSorted.add("Kiwi");
        expectedSorted.add("Melon");
        expectedSorted.add("Orange");
        expectedSorted.add("Papaya");

        Assertions.assertEquals(expectedSorted, customArrayList);
    }

}
