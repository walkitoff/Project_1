/**
 * This Queue maintains the queue of messages coming from connected clients.
 */
public class P2PMessageQueue {

    private P2PMessage head = null;
    private P2PMessage tail = null;


    /**
     * This method allows adding a message object to the existing queue.
     * @param oMessage
     */
    public synchronized void enqueue(P2PMessage oMessage){
//TODO:
//      if
//      head is null
//      set head & tail == new message
//		else
//      tail.next = new message
//      tail = new message
//      tail.next = null???

    }


    /**
     * This method allows removing a message object from the existing queue.
     * @return
     */
    public synchronized P2PMessage dequeue(){

//TODO:
//		if
//		head is null
//      return null;
//		else
//      create: P2PMessage tempP2P = head;
//      set this.head = next
//      return tempP2P;
        if(head == null){
            return null;
        }
        P2PMessage tempP2P = this.head;
        this.head = head.next;
        return tempP2P;
    }


    public boolean hasNodes(){

//TODO:
//		#####################
//		### ADD CODE HERE ###
//		#####################
        return false; //FIXME
    }
}

