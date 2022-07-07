
public abstract class Rule {
	private  int ruleNum;
	

	protected Rule(int ruleNum) {
		this.ruleNum = ruleNum;
	}

	public Generation evolve(Generation gen) {
		//Cole Hoffman helped me with this back in project 1
		boolean[] boolGen = gen.getStates();

		boolean[] newGen = new boolean[boolGen.length];

		for (int i = 0; i < boolGen.length; i++) {
			newGen[i] = evolve(getNeighborhood(i, gen));
		}

		return new Generation(newGen);	
	}

	public abstract boolean evolve(boolean[] neighborhood);

	public abstract boolean[] getNeighborhood(int idx, Generation gen);

	public int getRuleNum() {
		return this.ruleNum;
	}


	public abstract String getRuleTable(char falseSymbol, char trueSymbol);

}
