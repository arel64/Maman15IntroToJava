/**
 * This class represents a single node in a linked list
 * @author Arel Sharon
 * @version 1.0
 * @since 10/01/2022
 *
 */
public class RectNode {

    private RectangleA _rect;
    private RectNode _next;

    /**
     * Construct single rect node which has a next value of null
     * @param r new node rect
     */
    public RectNode (RectangleA r){
        this(r,null);
    }

    /**
     * Construct single rect node with rect data and a next value
     * @param r new node rect
     * @param n next node
     */
    public RectNode (RectangleA r, RectNode n){
        this._next = n;
        this._rect = null;
        if(r!=null){
            this._rect = new RectangleA(r);
        }
    }

    /**
     * Copy constructor
     * @param r node to copy
     */
    public RectNode (RectNode r){
        this(r.getRect(),r.getNext());
    }

    /**
     * Get copy of rect represented in node O(1)
     * @return copy of rect of this node
     */
    public RectangleA getRect(){
        return new RectangleA(this._rect);
    }

    /**
     * Get pointer to next node in the sequence O(1)
     * @return pointer to next node
     */
    public RectNode getNext(){
        return this._next;
    }

    /**
     * Set rect of current node O(1)
     * @param r rect to copy
     */
    public void setRect(RectangleA r){
        this._rect = new RectangleA(r);
    }

    /**
     * Set pointer to next node in sequence O(1)
     * @param next pointer to next node
     */
    public void setNext( RectNode next){
        this._next = next;
    }
}
