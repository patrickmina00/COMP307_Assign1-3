import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Perceptron {
	private int iteration = 0;
	private double threshold = 0;
	private ArrayList<Image> myImages = new ArrayList<Image>();
	private ArrayList<Feature> myFeatures = new ArrayList<Feature>();
	//	private ArrayList<Double> featureValues = null;



	/**
	 * Reads image.java file and stores the read data into myImages arraylist.
	 * @param fname
	 */
	public void readFile(String fname){
		System.out.println("Reading from file: "+fname);
		boolean[][] newimage = null;
		int rows;
		int cols;
		String category;
		String categoryName = fname;

		try {

			java.util.regex.Pattern bit = java.util.regex.Pattern.compile("[01]");
			Scanner f = new Scanner(new File(fname));

			while(f.hasNextLine()){
				if (!f.next().equals("P1")) {
					System.out.println("Not a P1 PBM file" );
				}

				category = f.next().substring(1);

				if (!category.equals("other")){
					categoryName=category;
				}

				rows = f.nextInt();
				cols = f.nextInt();

				newimage = new boolean[rows][cols];

				for (int r=0; r<rows; r++){
					for (int c=0; c<cols; c++){
						newimage[r][c] = (f.findWithinHorizon(bit,0).equals("1"));
					}
				}

				myImages.add(new Image(category, rows, cols, newimage));

			}

			f.close();
			System.out.println("Reading done!");
		}
		catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		}
	}



	/**
	 * Generate 50 random features for our perceptron.
	 * Stores generated values in myFeatures arraylist.
	 */
	public void generateFeatures(){
		System.out.println("Generating Features!");
		myFeatures.add(new DummyFeature());

		for(int i = 0; i < 50; i++){
			myFeatures.add(new Feature());
		}
	}




	public void classify(){


		while(iteration < 500){
			int hits = 0;
			iteration++;

			for(Image i: myImages){
				//get the dummy feature first
				double featureValue = 0;

				for(Feature f: myFeatures){
					featureValue += (double)f.value(i.getImage()) * f.getWeight();
				}

				//if images instance is of type #yes based on featureValue
				if(featureValue >= threshold){
					//if perceptron prediction (featureValue >0) is right
//					System.out.println(i.getType().equalsIgnoreCase("yes"));
					if(i.getType().equalsIgnoreCase("yes")){
//						System.out.println("i enter yes!");
						hits ++;
					}

					else{
						for(Feature f:myFeatures){
							double currentWeight = f.getWeight();
							f.setWeight(currentWeight + featureValue);
						}
					}
				}
				//image instance of type #other based on featureValue
				else{
				//if(currentImage.getType().equalsIgnoreCase("other")){
					if(i.getType().equalsIgnoreCase("other")){
//						System.out.println("i enter other!");
						hits++;
					}
					else{
						for(Feature f:myFeatures){
							double currentWeight = f.getWeight();
							f.setWeight(currentWeight - featureValue);
						}
					}
				}
			}
			System.out.println("Iteration: "+ this.iteration+ " Accuracy: "+(double)hits/(double)myImages.size());
		}
	}


	public void start(String fname){
		this.readFile(fname);
		this.generateFeatures();
		//		calculateFeatureValue();
		this.classify();
	}


	public static void main(String[] args){
		Perceptron p = new Perceptron();
		p.start(args[0]);
		System.exit(0);
	}
}
