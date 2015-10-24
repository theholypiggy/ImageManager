package domain;
import java.util.HashMap;
import java.awt.Graphics;
import java.util.ArrayList;

public class ImageManager<E> {
	private HashMap<E, HPBufferedImage> imageMap; //Stores the images for easy look up
	private ArrayList<HPBufferedImageList> imageMatrix;      //Stores the images for easy drawing
	
	public ImageManager
		()
	{
		imageMap = new HashMap<E, HPBufferedImage>();
		imageMatrix = new ArrayList<HPBufferedImageList>();
	}
	
	public void addImage
	    (E identifier,
	     int x,
	     int y,
	     int width,
	     int height)
	{
	    HPBufferedImage newImage = new HPBufferedImage(x, y, width, height, HPBufferedImage.TYPE_INT_RGB);
	    
		imageMap.put(identifier, newImage);
		imageMatrix.get(0).getList().add(newImage);
	}
	
	public void removeImage
	    (E identifier)
	{
	    HPBufferedImage removedImage = imageMap.remove(identifier);
	    for(HPBufferedImageList bufferedImageList : imageMatrix)
	    {
	        if(bufferedImageList.getList().remove(removedImage))
	        {
	            break;
	        }
	    }
	}
	
	public void setImageZ
	    (E identifier,
	     int z)
	{
	    HPBufferedImage image = imageMap.get(identifier);
	    int oldZ = image.getZ();
	    image.setZ(z);
	    imageMatrix.get(oldZ - 1).getList().remove(image);
	    imageMatrix.get(z - 1).getList().add(image);
	}
	
	public Graphics getImageGraphics
	    (E identifier)
	{
	    return imageMap.get(identifier).createGraphics();
	}
	
	public void drawImages
	    (Graphics g)
	{
	    for(int k = 0; k < imageMatrix.size(); k++)
	    {
	        HPBufferedImageList imageList = imageMatrix.get(k);
	        for(HPBufferedImage image : imageList.getList())
	        {
	            g.drawImage(image, image.getX(), image.getY(), null);
	        }
	    }
	}
}
