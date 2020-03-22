/* the approach i followed is to group the strings into a list of k values by checking the initial characters
 * then the grouped list is searched for prefix. a prefix will be searched for each group generated and its length is added in one common variable
 *  it is clear that if prefix exist it means the strings share some common characters in starting. 
 *  so i opted to check for initial character of each word of the input list to group them. 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Prefix {

	// Function to find the longest common prefix between two Strings
	// simply compare character at same position of both the strings and return a substring from 0 till first unmatched character's index -1 
	public static String LCP(String X, String Y) {
		int i = 0, j = 0;
		while (i < X.length() && j < Y.length()) {
			if (X.charAt(i) != Y.charAt(j)) {
				break;
			}
			i++;
			j++;
		}

		return X.substring(0, i);
	}

	// Function to find the longest common prefix (LCP) between given set of Strings
	// Loop throgh the list of words and return common prefix for entire list
	public static String findLCP(List<String> words) {
		String prefix = words.get(0);
		for (String s : words) {
			prefix = LCP(prefix, s);
		}
		return prefix;
	}

	public static void main(String[] args) {
		/* variable to store the initial input list of words */
		List<String> words = new ArrayList<String>();
		/* a master list to hold one entry for each test cases */ 
		ArrayList<ArrayList<ArrayList<String>>> cases  = new  ArrayList<ArrayList<ArrayList<String>>>();
 		
		int n, k, cnt, c;
		String w = "";

		/* a variable to store first character of previous words */
		char prevChar = ' ';

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();  /* get the number of test cases */

		if (t >= 1 && t <= 100) {
			for (int j = 0; j < t; j++) {
				/* a variable to hold the groups generated of length k */
				ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();

				n = sc.nextInt(); /* get total number of string*/
				if (n >= 2 && n <= 100000) {
					k = sc.nextInt(); /* get max length of a possible group*/
					if (k >= 2 && k <= n && n % k == 0) {
						for (int i = 0; i < n; i++) {
							w = sc.next();  /*  get one word */
							words.add(w.trim());
						}
						// words.sort(Comparator.comparingInt(String::length));
						
						/* sort the list of words in alphabetical order so it becomes easier for us to group them similar initials */
						Collections.sort(words);
						// System.out.println("words : " + words.toString());
						c = 0;
						cnt = 0;
						
						/* a variable to store a single group */
						ArrayList<String> temp = new ArrayList<String>();
						
						for (String s : words) {
							/* if it is the very first word of input, add it to the group and continue */ 
							if (c == 0) {
								prevChar = s.charAt(0);
								temp.add(s);
								cnt++;
							} else {								
								char ch = s.charAt(0);
								// System.out.println("current word : "+s+", char : "+ch+", prevchar : "+prevChar+" ");
								
								if (prevChar == ch) { /* if match found for characters of previous and current word then check for the length of group, k */
									
									// System.out.println("==> match found for : "+s+", char : "+ch+", prevchar : "+prevChar+" ");
									
									if (cnt < k) { /* if current number of words in a group is less then k, then add the current word to the group and increment the size */
										
										// System.out.println("adding in exisitng list.... cnt = "+cnt+", k = "+k);
										temp.add(s);
										cnt++;
										prevChar = ch;
									} else { /* if current number of words in a group is greater than or equal to k, then add the current group to the group list and clear the current group variable */
										ch = s.charAt(0);
										// System.out.println("==> match NOTTT found for : "+s+", char : "+ch+", prevchar : "+prevChar+" ");
										groups.add(temp);
										temp = new ArrayList<String>();
										cnt = 0;
										temp.add(s);
										cnt++;
										prevChar = ch;
									}
								} else { /* if no match found for characters of previous and current word, then add the current group to the group list and clear the current group variable */
									ch = s.charAt(0);
									// System.out.println("not matching previous character of["+s+"] ("+prevChar+" == "+ch+")");
									groups.add(temp);
									temp = new ArrayList<String>();
									cnt = 0;
									temp.add(s);
									cnt++;
									prevChar = ch;
								}
							}
							c++;
							/* if it is the end of the input list, add the temp list to group */
							if (s.equals(words.get(words.size() - 1))) {
								groups.add(temp);
							}
						}
						/* add generated groups  to a master list */
						cases.add(groups);
					}
				}
			}
		}
		
		/* iterate over master list where, each entry of the master list represents a test case; and print sum of the sizes of the prefix found for each group of a test case */
		for(int i=0; i<cases.size(); i++) {
			/* get an entry of a list of groups */
			ArrayList<ArrayList<String>> groups = cases.get(i);
			int sum = 0;
			/* iterate through each group and find prefix and add the length of the found prefix to the sum variable */
			for (int j = 0; j < groups.size(); j++) {
				ArrayList<String> a = groups.get(j);
				
				/* here....... i haven't wrote the following condition when submitting and it gave a run time error */
				if(a.size() == 1) {
					sum += 0;
					continue;
				}
				String p = findLCP(a);
				sum += p.length();

				//System.out.println("group : "+a.toString()+" , prefix : "+p+"("+p.length()+")"); , sum : " + sum );
			}
			/* print the sum for ith test case */
			System.out.println("Case #"+(i+1)+": " + sum);
		}

	} /* END OF MAIN() */
} /* END OF CLASS */