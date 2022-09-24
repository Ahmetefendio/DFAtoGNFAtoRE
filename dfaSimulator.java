package toc;

import java.io.*;
import java.util.*;
	
public class dfaSimulator {
	
	public static void main (String [] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a file name: ");
		String file = sc.nextLine();
		
		GNFA gnfa = createDFAFromFile(file);
		System.out.println(gnfa.createRegex());
		System.out.println();
				
	}

	public static GNFA createDFAFromFile(String fileName) {
		try {
			Scanner sc = new Scanner(new File(fileName));
			
			
			String startState = sc.nextLine().replace("S=", "");
			String[] acceptStates = sc.nextLine().replace("A=", "").split(",");
			String[] alphabet = sc.nextLine().replace("E=", "").split(",");
			String[] states = sc.nextLine().replace("Q=", "").split(",");
			
			String[][] transitionFunction = new String[states.length][alphabet.length];
			String currentState;
			String symbol;
			String transitionState;
						
			int counter = 0;
			int numTransitions = alphabet.length*states.length;

			while (sc.hasNextLine() && counter < numTransitions) {
				
				String[] line = sc.nextLine().replace("=", ",").split(",");
								
				currentState = line[0];
				symbol = line[1];
				transitionState = line[2];
				
				int posCurrentState = Arrays.asList(states).indexOf(currentState);
				int posSymbol = Arrays.asList(alphabet).indexOf(symbol);
				transitionFunction[posCurrentState][posSymbol] = transitionState;
			   				
				counter++;
			}

			GNFA dfa = new GNFA(states, alphabet, transitionFunction, startState, acceptStates);

			return dfa;
							
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
			
			return null;
		}
	}
}
