import java.io.IOException;

public class TotalisticAutomaton extends Automaton{
		
	public TotalisticAutomaton(int ruleNum, Generation initial) throws RuleNumException {
		super(ruleNum, initial);
	}
	
	public TotalisticAutomaton(String filename) throws NumberFormatException, IOException, RuleNumException {
		super(filename);
	}
	
	@Override
	protected Rule createRule(int ruleNum) throws RuleNumException {
		Rule rule;
		rule = new TotalisticRule(ruleNum);
		return rule;
	}
}
