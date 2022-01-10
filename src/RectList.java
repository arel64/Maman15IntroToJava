/**
 * This class represents
 */
public class RectList {

    private RectNode _head;

    /**
     * Construct empty list O(1)
     */
    public RectList(){
        this._head = null;
    }

    /**
     * Add node to list with rect as its data O(n)
     * @param r node rect
     */
    public void addRect(RectangleA r){
        if(r==null) return; //If rect is null no need to add it.

        //if head is null first rect is _head
        if(_head == null){
            this._head = new RectNode(r);
            return;
        }

        //List is not empty
        RectNode iter = _head;
        RectNode insert = new RectNode(r);

        //Retrieving pointer to end of list, while checking each element rect in list for equality to r
        while(iter.getNext() != null){
            if(iter.getRect().equals(r)) return; // Rect already exists, break;
            iter = iter.getNext();
        }
        //Iter now points to the last element of list,add it.
        iter.setNext(insert);
    }

    /**
     * Count the number of rect in the list with sw point that is equal to p O(n)
     * @param p point to compare to
     * @return number of rect in list with sw point p
     */
    public int howManyWithPoint(Point p){
        if(_head == null || p==null) return 0; //If either p is null or list is empty naturally there are 0 points

        int count=0;

        //Iterate over list and compare each point
        RectNode iter = _head;
        while(iter!=null){
            if(iter.getRect().getPointSW().equals(p)){
                count++;
            }
            iter = iter.getNext();
        }

        return count;
    }

    /**
     * Longest diagonal of rect in list O(n)
     * @return length of  the longest diagonal in list
     */
    public double longestDiagonal(){
        if(_head == null) return 0; //List is empty

        double maxDiag = 0;
        double currentDiag;

        //Iterate over list
        RectNode iter = _head;
        while(iter!=null){
            currentDiag = iter.getRect().getDiagonalLength();
            //If diagonal of iter rectangle is greater than max diagonal found thus far replace value of max diag
            if(currentDiag > maxDiag){
                maxDiag = currentDiag;
            }
            iter = iter.getNext();
        }

        return maxDiag;
    }

    /**
     * Return a copy of the first most left rectangle SW point in the list, O(n)
     * @return copy of most left rectangle sw point in list
     */
    public Point mostLeftRect(){
        Point temp = _mostRadicalPoint("Left");
        if(temp!=null){
            return new Point(temp);
        }
        return null;
    }

    /**
     * Return a copy of the first most left rectangle SW point in the list, O(n)
     * @return copy of most left rectangle sw point in list
     */
    public Point highestRect(){
        Point temp = _mostRadicalPoint("Top");
        if(temp!=null){
            return new Point(temp);
        }
        return null;
    }

    /**
     * Calculates the minimal rect to contain all other rect in the list
     * O(n), Creating a point is O(1) and finding most radical point is O(n) times a constant(4)
     * @return a copy of the minimal rect to contain all other rect in the list
     */
    public RectangleA minimalContainer(){
        if(_head == null) return null; //List is empty return null

        /*
         * We find out the most radical points in each direction and build our rect so that
         * its sides pass through all of them creating the minimal rect
         * Note that neither function will return null as there is always a radical point when the list isn't empty
         * which we check above
         */
        return new RectangleA(
                    new Point(mostLeftRect().getX(),_lowestRect().getY()),
                    new Point(_mostRightRect().getX(),highestRect().getY())
        );
    }

    /**
     * Stringify Object O(n)
     * @return String representation of list
     */
    public String toString(){
        //List is empty
        if(_head==null){
            return "The list has 0 rectangles.";
        }

        int count=0;
        String data=""; // This will contain only data of rect without the prefix.

        //Iterate over list and add rect toString into varriable
        RectNode iter = _head;
        while(iter!=null){
            count++;
            data += count + "." + iter.getRect().toString();
            if(iter.getNext()!=null){
                //Only break a new line if there is a next element
                data+="\n";
            }

            iter = iter.getNext();
        }
        return "The list has "+count+" rectangles.\n"+data;

    }

    /**
     * Calculates the most radical point in the list for each of the 4 direction O(n)
     * @param direction either one of Top,Bottom,Right,Left
     * @return most radical point in direction given, null if direction is invalid or list is null
     */
    private Point _mostRadicalPoint(String direction){
        if(_head == null) return null;

        Point mostRadicalPoint;
        Point currentPoint;

        RectNode iter = _head;
        /*
            Give inital value to current and mostRadical Point in order to avoid nullPointer
            A copy of the relevant point will be saved according to direction
         */
        switch (direction){
            case "Top":
            case "Right":
                //NE point is the most right and highest point of any rect
                currentPoint = iter.getRect().getPointNE();
                break;
            case "Left":
            case "Bottom":
                //SW point is the most left and lowest point of any rect
                currentPoint = iter.getRect().getPointSW();
            break;
            default:
                return null;
        }
        //As it is the only point found up until now, it is the mostRadicalPoint until another is found.
        mostRadicalPoint = new Point(currentPoint);
        iter = iter.getNext();

        //Iterate over list
        while(iter!=null){
            /*
                Compare each rect relevant point to direction with mostRadicalPoint found until now
                For each direction check if the relevant point of the current iteration is more radical than the one stored.
             */
            switch (direction){
                case "Top":
                    currentPoint = iter.getRect().getPointNE();
                    if(currentPoint.isAbove(mostRadicalPoint)){
                        mostRadicalPoint = currentPoint;
                    }
                    break;
                case "Right":
                    mostRadicalPoint = iter.getRect().getPointNE();
                    if(currentPoint.isRight(mostRadicalPoint)){
                        mostRadicalPoint = currentPoint;
                    }
                    break;
                case "Left":
                    mostRadicalPoint = iter.getRect().getPointSW();
                    if(currentPoint.isLeft(mostRadicalPoint)){
                        mostRadicalPoint = currentPoint;
                    }
                    break;
                case "Bottom":
                    mostRadicalPoint = iter.getRect().getPointSW();
                    if(currentPoint.isUnder(mostRadicalPoint)){
                        mostRadicalPoint = currentPoint;
                    }
                    break;
            }

            iter = iter.getNext();
        }
        return  mostRadicalPoint;
    }

    /**
     * Return a copy of the first most left rectangle SW point in the list, O(n)
     * @return copy of most left rectangle sw point in list
     */
    private Point _lowestRect(){
        Point temp = _mostRadicalPoint("Bottom");
        if(temp!=null){
            return new Point(temp);
        }
        return null;
    }

    /**
     * Return a copy of the first most left rectangle SW point in the list, O(n)
     * @return copy of most left rectangle sw point in list
     */
    private Point _mostRightRect(){
        Point temp = _mostRadicalPoint("Right");
        if(temp!=null){
            return new Point(temp);
        }
        return null;
    }
}
