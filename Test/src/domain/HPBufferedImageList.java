package domain;
import java.util.ArrayList;

public class HPBufferedImageList
{
    private ArrayList<HPBufferedImage> list;
    
    public HPBufferedImageList
        ()
    {
        list = new ArrayList<HPBufferedImage>();
    }
    
    public ArrayList<HPBufferedImage> getList
        ()
    {
        return list;
    }
}
