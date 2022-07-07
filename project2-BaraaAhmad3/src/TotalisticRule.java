import java.util.Arrays;
import java.util.StringJoiner;

public class TotalisticRule extends Rule{
private static int BINARYNUMBER = 6;
	private  int ruleNum;
	private char[] charArray;
	public final boolean[] ruleStates = new boolean[BINARYNUMBER];
	public TotalisticRule(int ruleNum) throws RuleNumException {
		super(ruleNum);
		if(ruleNum > 63)
		{
			throw new RuleNumException(0, 63);
		} 
		else if(ruleNum < 0) 
		{
			throw new RuleNumException(0, 63);

		}
		this.ruleNum = ruleNum;
		padBinary();
		statesArray();

	}

	private void padBinary() {

		char[] binaryCharArray = Integer.toBinaryString(ruleNum).toCharArray();

		charArray = new char[BINARYNUMBER];

		int paddingAmount = BINARYNUMBER - binaryCharArray.length;


		for(int i = paddingAmount; i < charArray.length; ++i) {
			for(int j = 0; j < paddingAmount; ++j) {
				charArray[j] = '0';
			}
			charArray[i] = (binaryCharArray[i - paddingAmount]);
		}

	}


	private void statesArray() {

		for (int i = 0; i < charArray.length; i++) {

			if(Integer.parseInt(String.valueOf(charArray[i])) == 1) 
			{
				ruleStates[i] = true;
			}
			else 
			{
				ruleStates[i] = false;
			}
		}
	}

	@Override
	public boolean evolve(boolean[] neighborhood) {
		//Cole Hoffman helped me with this method
		int index  = 5;
		int counter = 0;

		for (int i = 0; i < neighborhood.length; ++i) {

			if(neighborhood[i]) {
				counter++;	

			}

		}

		return ruleStates[index- counter] ;
	}

	@Override
	public boolean[] getNeighborhood(int idx, Generation gen) {
		//Maria Doan assisted me with the conditional if booleanGen length is 2.
		boolean[] booleanGen = gen.getStates();
		boolean[] neighborhood = new boolean[5];

		
		
		if(booleanGen.length == 1) {
			
			Arrays.fill(neighborhood, booleanGen[0]);
			
		}
		else {
		
		for(int i =0; i < neighborhood.length; ++i) {

			int index = (idx - 2 + i);
			int length = booleanGen.length;

			

			//This code checks specifically if the length of gen.getStates is 2
			
			 if(booleanGen.length == 2) {
				if(idx == 0) {
				neighborhood[0] = booleanGen[idx];
				neighborhood[1] = booleanGen[idx + 1];
				neighborhood[2] = booleanGen[idx];
				neighborhood[3] = booleanGen[idx + 1];
				neighborhood[4] = booleanGen[idx];
				}
			else if(idx == 1) {
					neighborhood[0] = booleanGen[idx];
					neighborhood[1] = booleanGen[idx -1];
					neighborhood[2] = booleanGen[idx];
					neighborhood[3] = booleanGen[idx - 1];
					neighborhood[4] = booleanGen[idx];
				}
				
			}
			
			//Boundary conditions
			else if(index == -2) {
				neighborhood[i] = booleanGen[booleanGen.length - 2];
			}
			else if(index == - 1) {
				neighborhood[i] = booleanGen[booleanGen.length - 1];
			}


			else if(index == length) {
				neighborhood[i] = booleanGen[0];
			}
			else if(index == length + 1) {
				neighborhood[i] = booleanGen[1];
			}

			else if(index == length + 2) {
				neighborhood[i] = booleanGen[2];
			}

			else if(index < booleanGen.length && index >= 0) {
				neighborhood[i] = booleanGen[index];
			}


		}

		
		}
		return neighborhood;	}


	public String getRuleTable(char falseSymbol, char trueSymbol) {

		StringJoiner join = new StringJoiner("");

		String firstLine = tableValues(falseSymbol, trueSymbol);


		switch(charArray[0]) {
		case '0':
			charArray[0] = falseSymbol;
			break;
		case '1':
			charArray[1] = trueSymbol;
		}

		join.add(firstLine).add(System.lineSeparator()).add( charArray[0] + "");
		join.add(" ");
		for(int i =1; i < charArray.length; ++i) {
			if(charArray[i] == '0') {
				charArray[i] = falseSymbol;
			}
			else if(charArray[i] == '1') {
				charArray[i] = trueSymbol;
			}
			if( i == charArray.length -1 ) {
				join.add(charArray[i] + "");
			}
			else {
				join.add(charArray[i] + " ");
			}
		}





		return join.toString();
	}

	private String tableValues(char f, char t) {



		String message = "5 4 3 2 1 0";

		return message;
	}


}