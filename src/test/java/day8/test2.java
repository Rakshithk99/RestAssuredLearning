package day8;

import java.util.HashMap;
import java.util.Map;

public class test2 {

public static void main(String[] args) {
	String a="Rakshith";
	char[] cArr=a.toCharArray();
	Map<Character,Integer> map = new HashMap<>();
	for(char c: cArr) {
		if(map.containsKey(c)) {
			map.put(c, map.get(c)+1);
		} else {
			map.put(c, 1);
		}
	}
	System.out.println(map);
	
}}
