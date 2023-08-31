package com.hit.algorithm;

public class NaiveAlgo implements IAlgoStringMatching {
	  @Override
	  public int find(String pat, String txt)
	    {
	        int l1 = txt.length();
	        int l2 = pat.length();
	        int i = 0, j = l2 - 1;
	 
	        for (i = 0, j = l2 - 1; j < l1;) {
	 
	            if (pat.equals(txt.substring(i, j + 1))) {
//	                System.out.println("Pattern found at index "
//	                                   + i);
	                return 1;
	            }
	            i++;
	            j++;
	        }
	        return 0;
	    }
	     
	      // Driver's code
	    public static void main(String args[])
	    {
	        String pat = "sdasdasd";
	        String txt = "AABAACAADAABAAABAA";
	        
	          // Function call
	        NaiveAlgo temp=new NaiveAlgo(); 
	        System.out.println(temp.find(pat, txt));
	    }
}
