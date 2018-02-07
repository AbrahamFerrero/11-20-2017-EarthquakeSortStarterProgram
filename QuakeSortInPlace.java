
/**
 * This is the class where we will work. The rest of them have been given as starter 
 * 
 * @author (Abraham Ferrero) 
 * @version (20/NOV/2017)
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

    public void testSortMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        sortByMagnitude(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    //These are assignment 1 methods: 
    
    public int getLargestDepth(ArrayList<QuakeEntry>quakeData, int from){
        //This way we get the index of the largest depth in a quakeData, returned, from the index defined as parameter
        int maxIdx = from;
        for (int i=from+1; i<quakeData.size(); i++){
            if(quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()){
                maxIdx = i;
            }
        }
        return maxIdx; 
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry>in){
        int count = 0;
        for(int i=0; i<70; i++){
            /*This method calculates the largest depth, and swaps the value with the original first value in the
             * quake list. Then it takes the second, calculates the second biggest value and swaps it index with the 
             * second index value in the list, and so on, and so on, until we've got our list sorted from deepest to
             * least deep. 
             */
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
            count++;
        }
        System.out.println("total passes = " +count);
    }
    
    public void testSortLargestDepth(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/earthQuakeDataDec6sample2.atom";
        ArrayList<QuakeEntry>quakeData = parser.read(source);
        sortByLargestDepth(quakeData);
        for( QuakeEntry qe: quakeData){
            System.out.println(qe);
        }
    }
    
    //Assignment 2 methods:
    
    public void onePassBubbleSort(ArrayList<QuakeEntry>quakeData, int numSorted){
        /*We start our loop from numSorted and compare the first value with the second one. If the second
         * is bigger, we swap them over.
         */
        for(int i=0; i<quakeData.size()-1; i++) {
            double magIdx = quakeData.get(i).getMagnitude();
            double magCurr = quakeData.get(i+1).getMagnitude();
            if(magIdx > magCurr){
                QuakeEntry memo = quakeData.get(i);
                quakeData.set(i,quakeData.get(i+1));
                quakeData.set(i+1,memo);
            }
            //System.out.println("now: " + quakeData.get(i).getMagnitude() + " "+  quakeData.get(i+1).getMagnitude());
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry>in){
        int numSorted = 0;
        for(int i=0;i<in.size()-1; i++){
            System.out.println("Printing quakes after pass " + numSorted);
            onePassBubbleSort(in,numSorted);
            numSorted++;
            for(QuakeEntry qe : in){
              System.out.println(qe);  
            }
        }
    }
    //Instead of modifying our last tester method, I think it is more efficient to add a new one:
    public void testSortByMagnitudeBubble(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/earthquakeDataSampleSix2.atom";
        ArrayList<QuakeEntry>quakeData = parser.read(source);
        System.out.println("Read data for " + quakeData.size() + " quakes");
        for(QuakeEntry qe: quakeData){
            System.out.println(qe);
        }
        System.out.println("\n");
        sortByMagnitudeWithBubbleSort(quakeData);
        System.out.println("Earthquakes in sorted order:");
        for(QuakeEntry qe: quakeData){
            System.out.println(qe);
        }
    }
    
    //Assignment 3 methods:
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry>quakes){
        /*This method checks if we needed to make changes(to apply the bubbe method) and if
         * we did, returns true, nad if not, it returns false. 
         */
        for (int i=0; i<quakes.size()-1;i++){
            double magIdx = quakes.get(i).getMagnitude();
            double magCurr = quakes.get(i+1).getMagnitude();
            if(magIdx > magCurr){
                System.out.println("true here");
                return true;
            }
        }
        System.out.println("false there");
        return false;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        /*This method sorts with bubble method just as the previous one for assignment 2,
         * but adds the checkInSortedOrder(in), counting how many passes we needed.
         */
        int numSorted = 0;
        int totalpass =1;
        for(int i=0;i<in.size()-1; i++){
            System.out.println("Printing quakes after pass " + numSorted);
            onePassBubbleSort(in,numSorted);
            numSorted++;
            for(QuakeEntry qe: in){
                System.out.println(qe);
            }
            checkInSortedOrder(in);
            if(checkInSortedOrder(in)){
                totalpass++;
            }
        }
        System.out.println("Total passes needed: " +totalpass);
    }
    
    //We added a new testSort method, much more efficient than commenting out everytime we want to run methods.
    
    public void testSort3(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry>quakeData = parser.read(source);
        System.out.println("Read data for " + quakeData.size() + " quakes");
        for(QuakeEntry qe: quakeData){
            System.out.println(qe);
        }
        System.out.println("\n");
        sortByMagnitudeWithBubbleSortWithCheck(quakeData);
    }
    
    public void SortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        /*As requested, we used the method sortByMagnitude and added the method checkInSortedOrder, so we can
         * count the passes, and also stop the method once everything is sorted, making the program much more
         * efficient. If it is already in order, it stops even though the loop was not finished, using "break".
         */
        int totalpass =1;
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            checkInSortedOrder(in);
            if(!checkInSortedOrder(in)){
                break;
            }
            else{
                totalpass++;
            }
        }
        System.out.println("Total passes needed: " +totalpass);
    }
    //Again, instead of modiying testsort method, I added a new one. 
    public void testSort4(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry>quakeData = parser.read(source);
        System.out.println("Read data for " + quakeData.size() + " quakes");
        for(QuakeEntry qe: quakeData){
            System.out.println(qe);
        }
        System.out.println("\n");
        SortByMagnitudeWithCheck(quakeData);
        System.out.println("Ordered:");
        for(QuakeEntry qe: quakeData){
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
}
