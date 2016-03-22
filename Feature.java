

public class Feature {

	private	int[] row;
	private	int[] col;
	private	boolean[] sgn;
	private double weight;

	public Feature(){

		//initialize variables
		this.row = new int[4];
		this.col = new int[4];
		this.sgn = new boolean[4];
		weight = new java.util.Random().nextDouble();


		//generate values;
		for(int k = 0; k < 4; k++){
			boolean h = new java.util.Random().nextBoolean();
			int i = new java.util.Random().nextInt(10);
			int j = new java.util.Random().nextInt(10);


			sgn[k] = h;
			row[k] = i;
			col[k] = j;
		}

	}

	/**
	 * Returns
	 * @param image
	 * @return
	 */
	public int value(boolean[][] image){
		int sum=0;
		for(int i=0; i < 4; i++)
			if (image[row[i]] [col[i]]==sgn[i]) sum++;
		return (sum>=3)?1:0;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight){
		this.weight = weight;
	}
}
