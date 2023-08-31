package com.hit.algorithm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import junit.framework.Assert;
import com.hit.dm.AlgoStringMatch;

public class IAlgoStringMatchingTest{
KmpAlgo kmp;
KarpAlgo karp;
NaiveAlgo naive;

IAlgoStringMatchingTest()
{
	kmp=new KmpAlgo();
	karp=new KarpAlgo();
	naive=new NaiveAlgo();
}


//@BeforeEach
//public void setUpKmp()
//{
//	
//	kmp=new KmpAlgo();
//	
//}
//@BeforeEach
//public void setUpKarp()
//{
//	karp=new KarpAlgo();
//}
//
//@BeforeEach
//public void setUpNaive()
//{
//	naive=new NaiveAlgo();
//}


//לא שינינו כאן את לפונקציות שבנינו במחלקה האבסטרקטית האם צריך?
@Test
public void find_empty_pattern()
{
	
	Assert.assertEquals(0, kmp.find(" ","abcdabcdabcd"));
    Assert.assertEquals(0, karp.find(" ","abcdabcdabcd"));	
    Assert.assertEquals(0, naive.find(" ","abcdabcdabcd"));
}

@Test
public void find_with_nonempty()
{
	Assert.assertEquals(1, kmp.find(" ","abcdabcdabcd abcdabcdabcd"));
	Assert.assertEquals(1, karp.find(" ","abcdabcdabcd abcdabcdabcd"));
	Assert.assertEquals(1, naive.find(" ","abcdabcdabcd abcdabcdabcd"));
}

@Test
public void find_with_not_exist()
{
	Assert.assertEquals(0, kmp.find("efg","abcdabcdef"));
	Assert.assertEquals(0, karp.find("efg","abcdabcdef"));
	Assert.assertEquals(0, naive.find("efg","abcdabcdef"));
}

@Test
public void find_with_exist()
{
	Assert.assertEquals(1, kmp.find("dab","abcdabcd"));
	Assert.assertEquals(1, karp.find("dab","abcdabcd"));
	Assert.assertEquals(1, naive.find("dab","abcdabcd"));
}

@Test
public void find_with_non_text()
{
	//הגוניט לא נותן להזין טקסט ריק לגמרי למרות שאלגוריתמים כן מקבלים טקטס ריק ומביאים תוצאה נכונה
	Assert.assertEquals(0, kmp.find("dab","   "));
	Assert.assertEquals(0, karp.find("dab","   "));
	Assert.assertEquals(0, naive.find("dab","   "));
}


	public static void main(String[] args) {
		IAlgoStringMatchingTest check;
		check = new IAlgoStringMatchingTest();
		
		check.find_with_non_text();

	}

}
