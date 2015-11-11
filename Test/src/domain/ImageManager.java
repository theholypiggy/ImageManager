package domain;
import java.util.HashMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.util.ArrayList;

//TODO: Make an image that this is drawn based off of

public class ImageManager<E> {
	private HashMap<E, HPBufferedImage> imageMap; //Stores the images for easy look up
	private ArrayList<HPBufferedImageList> imageMatrix;      //Stores the images for easy drawing
	
	public ImageManager
		()
	{
		imageMap = new HashMap<E, HPBufferedImage>();
		imageMatrix = new ArrayList<HPBufferedImageList>();
		imageMatrix.add(new HPBufferedImageList());
	}
	
	public int numImages
	    ()
	{
	    return imageMap.size();
	}
	
	public boolean hasImage
	    (E identifier)
	{
	    return imageMap.containsKey(identifier);
	}
	
	public void addImage
	    (E identifier,
	     HPBufferedImage image)
	{	    
		imageMap.put(identifier, image);
		imageMatrix.get(0).getList().add(image);
	}
	
	public void addImage
    (E identifier,
     int x,
     int y,
     int width,
     int height,
     int type)
{       
	HPBufferedImage image = new HPBufferedImage(x, y, width, height, BufferedImage.TYPE_INT_ARGB);
    imageMap.put(identifier, image);
    imageMatrix.get(0).getList().add(image);
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
	
	public void moveImage
	    (E identifier,
	     int dx,
	     int dy)
	{
	    HPBufferedImage image = imageMap.get(identifier);
	    image.setX(image.getX() + dx);
	    image.setY(image.getY() + dy);
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
	
	public HPBufferedImage getImage
	    (E identifier)
    {
	    return imageMap.get(identifier);
	}
	
	public Graphics2D getImageGraphics
	    (E identifier)
	{
	    return imageMap.get(identifier).createGraphics();
	}
	
	public void drawImages
	    (Graphics2D g)
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
	
	public AlphaComposite makeComposite(float alpha) {
	    int type = AlphaComposite.SRC_OVER;
	    return(AlphaComposite.getInstance(type, alpha));
	   }
}
