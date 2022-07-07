import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;

public abstract class Automaton {
	//Everything in this class from project 1 was worked on with Chris White back in project 1
	private Rule rule;
	private ArrayList<Generation> generations = new ArrayList<>();
	char falseSymbol;
	char trueSymbol;
	Generation gen = new Generation();

	
	protected Automaton(int ruleNum, Generation initial) throws RuleNumException {
		falseSymbol = '0';
		trueSymbol = '1';
		rule = createRule(ruleNum);
		generations.add(initial);
	}
	
	protected Automaton(String fileName) throws NumberFormatException, IOException, RuleNumException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		rule = createRule(Integer.parseInt(br.readLine()));		
		String[] charArray = br.readLine().split(" ");
		falseSymbol = charArray[0].charAt(0);
		trueSymbol = charArray[1].charAt(0);
		Generation generationFileValue = new Generation(br.readLine(), trueSymbol);
		generations.add(generationFileValue);
		br.close();
	}
	
	protected abstract Rule createRule(int ruleNum) throws RuleNumException;
		
	
	
	
	public void evolve(int numSteps) {

		if(numSteps <= 0 ) {
			return;
		}
		else {
			for(int i =0; i < numSteps; ++i) {
				generations.add(rule.evolve(generations.get(generations.size() - 1)));
			}
		}
	}
	
	public Generation getGeneration(int stepNum) {
		evolve(stepNum - getTotalSteps());
		return generations.get(stepNum);
	}
	
	public int getRuleNum() {
		return this.rule.getRuleNum();
	}
	
	public String getRuleTable() {
		return rule.getRuleTable(falseSymbol, trueSymbol);
	}
	
	public int getTotalSteps() {
		return generations.size() -1;
	}
	
	public void saveEvolution(String filename) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		bw.write(toString());
		bw.close();
	}
	
	public String toString() {
		StringJoiner join = new StringJoiner(System.lineSeparator());
		for(int i = 0; i < generations.size(); ++i) {
			for(int j = 1; j < generations.get(i).getStates().length; ++j) {

				if(generations.get(i).getStates(falseSymbol, trueSymbol).charAt(j) == trueSymbol) {

					join.add(generations.get(i).getStates(falseSymbol, trueSymbol).toString());
					break;
				}
				else  {
					join.add(generations.get(i).getStates(falseSymbol, trueSymbol).toString());
					break;

				}
			}




		}
		return join.toString();
	}
	
}
