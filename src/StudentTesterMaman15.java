
/**
 * This is a basic tester for Maman 15 2022A.
 * 
 * IMPORTANT NOTE: This tester DOES NOT check the correctness of your code. It is
 * only intended to check the correctness of youe methods names and parameters. (to make sure
 * that compilation of your work during its checking will run smoothly). 
 * 
 * @author Course Staff
 * 
 */
public class StudentTesterMaman15
{
    public static void main(String [] args) {
     
        System.out.println("\n****Testing RectNode****"); 
        RectangleA r1 = new RectangleA(1,2); 
        RectangleA r2 = new RectangleA(3,4); 
        RectangleA r3 = new RectangleA(5,6); 
        // check constructors. 
        RectNode rectNode1 = new RectNode(r1); 
        RectNode rectNode2 = new RectNode(r2, rectNode1); 
        RectNode rectNode3 = new RectNode(rectNode2); 

        // check getRect/setRect
        rectNode1.setRect(r3); 
        RectangleA r4 = rectNode1.getRect();

        // check getNext/setNext
        RectNode rectNode4 = new RectNode(r4); 
        rectNode1.setNext(rectNode4);
        RectNode rectNode5 = rectNode1.getNext(); 
        System.out.println("finished"); 
  
        System.out.println("\n****Testing RectList****"); 
        //check constructor
        RectList list = new RectList(); 

        //add rectangles
        list.addRect(new RectangleA(1,2)); 
        list.addRect(new RectangleA(3,4)); 
        list.addRect(new RectangleA(new Point (1,1),5,6)); 

        //toString
        System.out.println("Check carefully your output. Make sure it is the same as the required output.");
        System.out.println("Your output:\n"+list); 
        System.out.println("Required ouput:\n"+"The list has 3 rectangles.\n1. Width=1 Height=2 PointSW=(0,0)\n"+
                         "2. Width=3 Height=4 PointSW=(0,0)\n3. Width=5 Height=6 PointSW=(1,1)\n"); 
                   
        //howManyWithPoint
        int n = list.howManyWithPoint(new Point(1,1)); 
        System.out.println("Method howManyWithPoint returns " + n + " for Point(1,1)"); 
        
        //longestDiagonal
        double d = list.longestDiagonal(); 
        System.out.println("Method longestDiagonal returns " + d); 
        
        //mostLeftRect
        Point p = list.mostLeftRect(); 
        System.out.println("Method mostLeftRect returns " + p); 
        
        //highestRect
        p = list.highestRect(); 
        System.out.println("Method highestRect returns " + p); 
        
        //minimalContainer
        RectangleA r = list.minimalContainer(); 
        System.out.println("Method minimalContainer returns " + r);
        System.out.println("finished"); 
    }

}
