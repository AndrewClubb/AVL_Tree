package avlTreeTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import avlTreePD.AVLTree;


public class AVLTreeTest {
	public void testTree() {
		AVLTree<Integer, String> tree = new AVLTree<Integer, String>();
		
		String fileName ="testdata.csv";
		String line = null;
	    try {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = new FileReader(fileName);

	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        
	        while((line = bufferedReader.readLine()) != null) 
	        {
	        	String[] token = line.split(",");
	        	tree.insert(Integer.parseInt(token[0]), token[1]);
	        }
	        
	        // Always close files.
	        bufferedReader.close();            
	    }
	    catch(FileNotFoundException ex) 
	    {
	      System.out.println("Unable to open file '" +  fileName + "'");                
	    }
	    catch(IOException ex) 
	    {
	       System.out.println ("Error reading file '" + fileName + "'");   	
	    }
	    
	    String searchResult;
	    
	    System.out.println("Test Binary Tree Test");
	    System.out.println("Binary Tree Height = " + tree.height());
	    
	    System.out.println("Search for 782209");
	    searchResult = tree.search(782209);
	    if(searchResult == null)
	    	System.out.println("Search result:782209 not found");
	    else
	    	System.out.println("Search result:782209:" + searchResult);
	    
	    tree.remove(782209);
	    
	    searchResult = tree.search(782209);
	    if(searchResult == null)
	    	System.out.println("Search result:782209 not found");
	    else
	    	System.out.println("Search result:782209:" + searchResult);
	}
}