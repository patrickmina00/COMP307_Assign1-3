
public class Image {
	private String type;
	private int width;
	private int height;
	private boolean[][] image;

	public Image(String type, int width, int height, boolean[][] image){
		this.type = type;
		this.width = width;
		this.height = height;
		this.image = image;
	}

	public String getType() {
		return type;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean[][] getImage(){
		return this.image;
	}
}
