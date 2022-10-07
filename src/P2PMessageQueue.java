/**
 * This Queue maintains the queue of messages coming from connected clients.
 */
public class P2PMessageQueue {

    public P2PMessage head = null;
    private P2PMessage tail = null;


    /**
     * This method allows adding a message object to the existing queue.
     * @param oMessage
     */
    public synchronized void enqueue(P2PMessage oMessage){
//DONE
        if(this.head == null){
            //head & tail point to same address if only 1 message is the queue
            this.head = oMessage;
            this.tail = oMessage;
        }else{
            this.tail.next = oMessage; //message address gets stored in tail.next, which is also the deepest .next of head
            this.tail = oMessage;   //now tail points to the last address of head ie head.next.next.next ... depending how long the queue is
            this.tail.next = null;
        }

    }


    /**
     * This method allows removing a message object from the existing queue.
     * @return
     */
    public synchronized P2PMessage dequeue(){
//DONE
        if(this.head == null){
            return null;
        }

        P2PMessage oTempMsg = this.head; //store the obj at the front of line
        this.head = head.next; //move next in line to front of line
        return oTempMsg; //return obj removed from queue aka from the linked list.
    }


    public boolean hasNodes(){
//DONE:   if QUEUE is empty, return false else return true
        return (this.head != null);
    }

}

