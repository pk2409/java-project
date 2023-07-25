package com.pluralsight.javaproject.cli;

public class CourseRetriever {

   public static void main( String args[]){
       System.out.println("providing the course info over here");
       if (args.length==0){
           System.out.println("Please provide an author name as first argument");
           return;
       }
       try{
           retrieveCourses(args[0]);
       } catch(Exception e){
           System.out.println("an unexpected error occurs over here");
           e.printStackTrace();
       }
   }

    private static void retrieveCourses(String authorId) {
       System.out.println("Retrieving courses for author "+ authorId);
    }
}
