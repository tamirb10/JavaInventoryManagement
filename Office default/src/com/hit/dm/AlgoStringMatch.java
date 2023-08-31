package com.hit.dm;

import com.hit.algorithm.KarpAlgo;
import com.hit.algorithm.KmpAlgo;
import com.hit.algorithm.NaiveAlgo;

public abstract class AlgoStringMatch {
	
	 static public int kmpFind(String pat, String txt)
	{
		return (new KmpAlgo().find(pat,txt));
	}
	
	 static public int karpFind(String pat, String txt)
	{
		return (new KarpAlgo().find(pat,txt));
	}
	
	static public int naiveFind(String pat, String txt)
		{
			return (new NaiveAlgo().find(pat,txt));
		}

	
	
}
