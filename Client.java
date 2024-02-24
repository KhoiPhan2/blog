// Khoi Phan
// CSE 123
// Section AC
// 5-17-2023
// Programming Assignment 2
import java.util.*;

// This class sorts through a scenario, that sorts through locations, and allocates relief,
// to the locations that have the lowest cost and largest population.
public class Client {
    private static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        // List<Location> scenario = createRandomScenario(10, 10, 100, 1000, 100000);
        List<Location> scenario = createSimpleScenario();
        System.out.println(scenario);
        
        double budget = 2000;
        Allocation allocation = allocateRelief(budget, scenario);
        printResult(allocation, budget);
    }

// This method sorts through all of our allocations and sorts to find the one that saves the most,
// people and is the lowestTotalCost. 
// Parameters: double budget, List<Location> sites.
//Returns: Max allocation, (the best combination of cost and population). 
    public static Allocation allocateRelief(double budget, List<Location> sites) {
        Set<Allocation> set = generateOptions(budget, sites);
        int maxPop = 0;
        Allocation max = null;
        List<Allocation> maxSet = new ArrayList<>();

        for (Allocation allocation : set) {
            if (maxPop < allocation.totalPeople()) {
                maxPop = allocation.totalPeople();
            }
        }
        for (Allocation allocation : set) {
            if (maxPop == allocation.totalPeople()) {
                maxSet.add(allocation);
            }
        }
        double lowestTotalCost = Double.MAX_VALUE;
        for (Allocation allocation : maxSet) {
            if (lowestTotalCost > allocation.totalCost()) {
                lowestTotalCost = allocation.totalCost();
                max = allocation;
            }
        }
        if (max == null) {
            return new Allocation();
        }
        return max;
    }

// This helper method creates finds all of the possible combinations of allocation.
// Parameters: double budget, List<Location> sites.
// returns allocations that contains all possible combination.
    private static Set<Allocation> generateOptions(double budget, List<Location> sites) {
        List<Location> allocatedLocations = new ArrayList<>();
        Set<Allocation> allocations = new HashSet<>();
        generateOptions(budget, sites, allocatedLocations, allocations, 0);
        return allocations;
    }

// This helper method creates finds all of the possible combinations of allocation.
// Parameters: double budget, List<Location> sites, List<Location> allocatedLocations,
//               Set<Allocation> allocations, int index).
    private static void generateOptions(double budget, List<Location> sites, List<Location> allocatedLocations,
                                        Set<Allocation> allocations, int index) {
        if (index >= sites.size()) {
            Allocation allocation = new Allocation();
            for (Location location : allocatedLocations) {
                allocation = allocation.withLoc(location);
            }
            allocations.add(allocation);
            return;
        }
        Location location = sites.get(index);
        if (budget >= location.getCost()) {
            allocatedLocations.add(location);
            List<Location> remainingSites = new ArrayList<>(sites);
            remainingSites.remove(location);
            
            generateOptions(budget - location.getCost(), remainingSites, allocatedLocations, allocations, index);
            allocatedLocations.remove(location);
        }
        
        generateOptions(budget, sites, allocatedLocations, allocations, index + 1);
    }

// Prints out our best allocation.
// Parameters: Allocation alloc, double budget
    public static void printResult(Allocation alloc, double budget) {
        System.out.println("Result: ");
        System.out.println("  " + alloc);
        System.out.println("  People helped: " + alloc.totalPeople());
        System.out.printf("  Cost: $%.2f\n", alloc.totalCost());
        System.out.printf("  Unused budget: $%.2f\n", (budget - alloc.totalCost()));
    }

//Creates a random Scenario that we will sort through.
//Parameters: int numLocs, int minPop, int maxPop, double minCostPer, double maxCostPer
//Returns: List<Location> (list of locations)
    public static List<Location> createRandomScenario(int numLocs, int minPop, int maxPop, double minCostPer, double maxCostPer) {
        List<Location> result = new ArrayList<>();

        for (int i = 0; i < numLocs; i++) {
            int pop = rand.nextInt(minPop, maxPop + 1);
            double cost = rand.nextDouble(minCostPer, maxCostPer) * pop;
            result.add(new Location("Location #" + i, pop, round2(cost)));
        }

        return result;
    }
//Creates a Simple Scenario
//Returns: List<Location>
    public static List<Location> createSimpleScenario() {
        List<Location> result = new ArrayList<>();

        result.add(new Location("Location #1", 50, 500));
        result.add(new Location("Location #2", 100, 700));
        result.add(new Location("Location #3", 60, 1000));
        result.add(new Location("Location #4", 20, 1000));
        result.add(new Location("Location #5", 200, 900));

        return result;
    }    

    //Rounder method.
    // Parameters: double num.
    private static double round2(double num) {
        return Math.round(num * 100) / 100.0;
    }
}
