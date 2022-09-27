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
//		#####################
//		### ADD CODE HERE ###
//		#####################
    }


    /**
     * This method allows removing a message object from the existing queue.
     * @return
     */
    public synchronized P2PMessage dequeue(){

//TODO:
//		#####################
//		### ADD CODE HERE ###
//		#####################
        return head; //FIXME
    }


    public boolean hasNodes(){

//TODO:
//		#####################
//		### ADD CODE HERE ###
//		#####################
        return false; //FIXME
    }
}

