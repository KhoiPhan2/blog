// Khoi Phan
// CSE 123
// Section AC
// 5-17-2023
// Programming Assignment 2
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class Testing {

    @Test
    @DisplayName("STUDENT TEST - Case #1")
    public void firstCaseTest() {
        int budget = 500;
        List<Location> loci = new ArrayList<>();
        Location firstLoc = new Location("Location #1", 100, 400);
        Location secondLoc = new Location("Location #2", 150, 600);
        loci.add(firstLoc);
        loci.add(secondLoc);


        Set<Location> expected = new HashSet<Location>();
        expected.add(firstLoc);

        Set<Location> actual = Client.allocateRelief(500, loci).getLocations();
        assertEquals(expected, actual, "Allocate Relief picked " + actual + " instead of " + expected);
    }

    @Test
    @DisplayName("STUDENT TEST - Case #2")
    public void secondCaseTest() {
        int budget = 500;
        List<Location> loci = new ArrayList<>();
        Location firstLoc = new Location("Location #1", 150, 400);
        Location secondLoc = new Location("Location #2", 100, 450);
        loci.add(firstLoc);
        loci.add(secondLoc);
        Set<Location> expected = new HashSet<Location>();
        expected.add(firstLoc);
        Set<Location> actual = Client.allocateRelief(500, loci).getLocations();
        assertEquals(expected, actual, "Allocate Relief picked " + actual + " instead of " + expected);
    }

    @Test
    @DisplayName("STUDENT TEST - Case #3")
    public void thirdCaseTest() {
        int budget = 500;
        List<Location> loci = new ArrayList<>();
        Location firstLoc = new Location("Location #1", 150, 400);
        Location secondLoc = new Location("Location #2", 150, 450);
        loci.add(firstLoc);
        loci.add(secondLoc);
        Set<Location> expected = new HashSet<Location>();
        expected.add(firstLoc);
        Set<Location> actual = Client.allocateRelief(500, loci).getLocations();
        assertEquals(expected, actual, "Allocate Relief picked " + actual + " instead of " + expected);
    }
}
