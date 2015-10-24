package domain;
import java.awt.image.BufferedImage;

public class HPBufferedImage extends BufferedImage {
	
    private int x;
    private int y;
	private int z;
	
	public HPBufferedImage
		(int x,
		 int y,
		 int width,
		 int height,
		 int imageType)
	{
		super(width, height, imageType);
		this.x = x;
		this.y = y;
		this.z = 1;
	}
	
	public int getZ
		()
	{
		return z;
	}
	
	public void setZ
	    (int z)
	{
	    this.z = z;
	}
	
	public int getX
	    ()
	{
	    return x;
	}
	
	public int getY
    ()
	{
	    return y;
	}
}
