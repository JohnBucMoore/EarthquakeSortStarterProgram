
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int deepestIndex = from;
        for (int i = from+1; i < quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() < quakeData.get(deepestIndex).getDepth()) {
                deepestIndex = i;
            }
        }
        return deepestIndex;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size(); i++) {
            int maxIndex = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(maxIndex);
            in.set(i,qmin);
            in.set(maxIndex,qi);
        }
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i = 1; i < (quakeData.size() - numSorted); i++) {
            if (quakeData.get(i-1).getMagnitude() > quakeData.get(i).getMagnitude()) {
                QuakeEntry qi = quakeData.get(i);
                QuakeEntry qprev = quakeData.get(i-1);
                quakeData.set(i,qprev);
                quakeData.set(i-1,qi);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size()-1; i++) {
            /*
            System.out.println("Bubble sort pass number "+i);
            for (QuakeEntry q : in) {
                System.out.println(q);
            }
            System.out.println("\n");

             */
            onePassBubbleSort(in, i);
            /*
            for (QuakeEntry q : in) {
                System.out.println(q);
            }
            System.out.println("\n");

            System.out.println("Printing Quakes after pass " + i);
            for (QuakeEntry q : in) {
                System.out.println(q);
            }
            System.out.println("\n");

             */
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        boolean sorted = true;
        for (int i = 1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(i-1).getMagnitude()) {
                sorted = false;
            }
        }
        return sorted;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int numberOfPasses = 0;
        for (int i = 0; i < in.size()-1; i++) {
            if (!checkInSortedOrder(in)) {
                onePassBubbleSort(in, i);
                numberOfPasses = i+1;
            }
        }
        System.out.println("It took "+numberOfPasses+" passes to sort this array");
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int numberOfPasses = 0;
        for (int i=0; i< in.size(); i++) {
            if (!checkInSortedOrder(in)) {
                int minIdx = getSmallestMagnitude(in, i);
                QuakeEntry qi = in.get(i);
                QuakeEntry qmin = in.get(minIdx);
                in.set(i, qmin);
                in.set(minIdx, qi);
                numberOfPasses = i+1;
            }
        }
        System.out.println("It took "+numberOfPasses+" passes to sort this array");
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/earthquakeDataSampleSix1.atom";

        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");
        /*
        sortByMagnitude(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }



        sortByLargestDepth(list);
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }
        System.out.println("\n");
        onePassBubbleSort(list, 0);


        sortByMagnitudeWithBubbleSort(list);
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }

        sortByMagnitudeWithBubbleSortWithCheck(list);
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }

         */
        sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}

    public static void main(String[] args) {
        QuakeSortInPlace sort = new QuakeSortInPlace();
        sort.testSort();
    }
}
