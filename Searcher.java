import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Searcher {
	
	ArrayDeque<Character> q = new ArrayDeque<Character>();
	ArrayDeque<Character> qc = new ArrayDeque<Character>();
	char[] c;
	String[] ch;
	int[] next1;
	int[] next2;
	
	public Searcher(File FSM, File patterns) throws FileNotFoundException {
		
		Scanner sc = new Scanner(FSM);
		ch = sc.nextLine().split(",");
		String[] split1 = sc.nextLine().split(",");
		String[] split2 = sc.nextLine().split(",");
		sc.close();
		
		sc = new Scanner(patterns);
		
		String example = "aa";
		c = example.toCharArray();
		
		next1 = new int[split1.length];
		next2 = new int[split2.length];
		
		for (int i = 0; i < next1.length; i++) {
			next1[i] = Integer.parseInt(split1[i]);
			next2[i] = Integer.parseInt(split2[i]);
		}
		
		for (char ct : c) {
			q.add(ct);
		}
		
		System.out.println("Test Pattern = " + example);
		if (testValid(0)) System.out.println("Match");
		else System.out.println("No Match");
	}	

	private boolean automaton() {
		
	}




	
	private boolean testValid(int state) {
									
		if (goUp(state, q.peek()) && q.isEmpty()) return true;
		else if (goDown(state, q.peek()) && q.isEmpty()) return true;
						
		return false;
	}
	
	private boolean goUp(int state, char c) {
		
		if (ch[state].equals(String.valueOf(c))) {
			qc.add(q.remove());
			return testValid(next1[state]);
		}		
		if (!ch[state].equals(" ") && !ch[state].equals(c)) {
			refill();
			return false;
		}
		if (ch[state].equals(" ") && next1[state] == 0 && next2[state] == 0 && !q.isEmpty()) {
			refill();
			return false;
		}
		if (ch[state].equals(" ")) return testValid(next1[state]); 	
		
		return false;
		
	}
	
	private boolean goDown(int state, char c) {
		
		if (ch[state].equals(String.valueOf(c))) {
			qc.add(q.remove());
			return testValid(next2[state]);
		}
		if (!ch[state].equals(" ") && !ch[state].equals(c)) { 
			refill();
			return false;
		}
		if (ch[state].equals(" ") && next1[state] == 0 && next2[state] == 0 && !q.isEmpty()) {
			refill();
			return false;
		}
		if (ch[state].equals(" ")) return testValid(next2[state]); 		
		
		return false;
		
	}	
	
	private void refill() {
		for ( Character c : qc ) {
			q.addFirst(qc.remove());
		}
	}
}


