import java.util.Arrays;
import java.util.StringJoiner;

public class ElementaryRule extends Rule {
	private  int ruleNum;
	private final int BINARY = 8;
	private char[] charArray;
	private final boolean[] ruleStates = new boolean[BINARY];
	

	public ElementaryRule(int ruleNum) throws RuleNumException {
		super(ruleNum);
		if(ruleNum > 255)
		{
			throw new RuleNumException(0, 255);
		} 

		else if(ruleNum < 0) 
		{
			throw new RuleNumException(0, 255);

		}
		this.ruleNum = ruleNum;

		padBinary();
		statesArray();
	}

	public boolean evolve(boolean[] neighborhood) {
		String neighborhoodVal = "";

		for (int i = 0; i < neighborhood.length; ++i) {

			if(neighborhood[i]) {
				neighborhoodVal += "1";
			}
			else {
				neighborhoodVal += "0";
			}
		}

		return switchHelper(neighborhoodVal);

	}

	private void padBinary() {

		char[] binaryCharArray = Integer.toBinaryString(ruleNum).toCharArray();

		charArray = new char[BINARY];

		int paddingAmount = BINARY - binaryCharArray.length;


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
	public boolean[] getNeighborhood(int idx, Generation gen) {
		boolean[] booleanGen = gen.getStates();
		boolean[] neighborhood = new boolean[3];

		if(booleanGen.length == 1) {
			Arrays.fill(neighborhood, neighborhood[0]);
		}

		for(int i =0; i < neighborhood.length; ++i) {

			int index = (idx - 1 + i);

			if(index < 0) {
				neighborhood[i] = booleanGen[booleanGen.length - 1];
			}

			else if( index > booleanGen.length - 1) {
				neighborhood[i] = booleanGen[0];
			}

			else {
				neighborhood[i] = booleanGen[index];
			}

		}

		return neighborhood;

	}
	
	private boolean switchHelper(String parameter) {

		switch(parameter) 
		{
		case "111":

			return ruleStates[0];

		case "110":

			return ruleStates[1];

		case "101":

			return ruleStates[2];

		case "100":

			return ruleStates[3];

		case "011":

			return ruleStates[4];

		case "010":


			return ruleStates[5];

		case "001":


			return ruleStates[6];

		case "000":

			return ruleStates[7];

		default:
			return false;
		}


	}

	//"111 110 101 100 011 010 001 000"

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
		
		join.add(firstLine).add(System.lineSeparator()).add(" " + charArray[0] + " " );
		for(int i =1; i < charArray.length; ++i) {
			if(charArray[i] == '0') {
				charArray[i] = falseSymbol;
			}
			else if(charArray[i] == '1') {
				charArray[i] = trueSymbol;
			}
			join.add("  "  +charArray[i] + " ");
		}

		return join.toString();
	}

	private String tableValues(char f, char t) {
			
			String value1 = "" + t + "" + t + "" + t+ " ";
			String value2 = "" + t + "" + t + "" + f + " ";
			String value3 = "" + t + "" + f + "" + t + " ";
			String value4 = "" + t + "" + f + "" + f + " ";
			String value5= "" + f + "" + t + "" + t + " ";
			String value6 = "" + f + "" + t + "" + f + " ";
			String value7 = "" + f + "" + f + "" + t + " ";
			String value8 = "" + f + "" + f + "" + f + "";
			
			String message = value1 + value2 + value3 + value4 + value5 + value6 + value7 + value8;
			
			return message;
	}
	

    
    
}  



