import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class ElementaryAutomaton extends Automaton {
	

	public ElementaryAutomaton(int ruleNum, Generation initial) throws RuleNumException {
		super(ruleNum, initial);
		

	}

	public ElementaryAutomaton(String filename) throws NumberFormatException, IOException, RuleNumException {

		super(filename);




	}

	@Override
	protected Rule createRule(int ruleNum)  {
		Rule elementaryRule;
		try {
			elementaryRule = new ElementaryRule(ruleNum);
			return elementaryRule;
		} catch (RuleNumException e) {
			e.printStackTrace();
		}
		return createRule(ruleNum);
	}
}