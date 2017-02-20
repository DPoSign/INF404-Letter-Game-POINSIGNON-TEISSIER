package Game;

import java.util.Random;

import java.util.ArrayList;

import java.util.Collections;

import java.util.List;

import java.util.ListIterator;

public class PotCommun {
	
	public static void main(String[] args) {
  //Generate a random letter
        
		  final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		  final int N = alphabet.length();

		    Random r = new Random();

		    final char potcommun = alphabet.charAt(r.nextInt(N));
		    for (int i = 0; i < 1; i++) {
		        System.out.print(alphabet.charAt(r.nextInt(N)));
		        System.out.print("\n");
		        System.out.print(potcommun);
		    }    
		    
		    List<String> list = new ArrayList<String>();

		     list.add(alphabet);
		     //list.add(alphabet.charAt(r.nextInt(N)));
		     /*list.add("c");
		      list.add("d");
		      list.add("e");
		      list.add("f");*/


/*
		      //On récupère une sous-liste

		      List<String> sub = list.subList(2, 5);

		      System.out.println(sub);

		      Collections.reverse(sub);

		      System.out.println(sub);

		*/      

		      //On récupère un ListIterator

		      ListIterator<String> it = list.listIterator();

		      while(it.hasNext()){

		         String str = it.next();

		         if(str.equals("d"))

		            it.set("z");

		      }

		      while(it.hasPrevious())

		         System.out.print(it.previous());

		      

		   }
		    
		    
	}




	

