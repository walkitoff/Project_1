import java.util.ArrayList;


/**
 * This holds the values for a Block in the Blockchain, and it has methods to compute the Merkle Root and generate a hash.
 */
public class Block {


    private String sMerkleRoot;
    private int iDifficulty = 5; // Mining seconds in testing 5: 6,10,15,17,20,32 | testing 6: 12,289,218
    private String sNonce;
    private String sMinerUsername;
    private String sHash;


    /**
     * This computes the Merkle Root. It either accepts 2 or 4 items, or if made to be dynamic, then accepts any
     * multiple of 2 (2,4,8.16.32,etc.) items.
     * @param lstItems
     * @return
     */
    public synchronized String computeMerkleRoot(ArrayList<String> lstItems) {
//DONE:
        // check for valid input
        if(lstItems.size() % 2 != 0){
            System.out.println("Block.computeMerkleRoot(lstItems) FAILED: \nLeafs not Multiple of 2!");
            return "";
        }

        ArrayList<MerkleNode> lstParent = new ArrayList<>();
        ArrayList<MerkleNode> lstChild = new ArrayList<>();
        MerkleNode temp;
        int treeHeight = lstItems.size();
        int count = 0;

        //get tree Height
        while(true){
            if(treeHeight % 2 == 1){
                treeHeight = count;
                break;
            }
            count++;
            treeHeight = treeHeight / 2;
        }

        //generate hash from lstItems and store in lstParent as the Leafs
        for(int i = 0; i < lstItems.size(); i++) {
            temp = new MerkleNode();
            temp.sHash = BlockchainUtil.generateHash(lstItems.get(i));
            temp.oLeft = null;  //leafs have no children
            temp.oRight = null; //leafs have no children
            lstParent.add(temp);
        }

        //TODO: remove print before submit
        System.out.println("\nb4 loop starts: #of leafs = lstParent size: " + lstParent.size());

        // goes lstItems -> parent, then parent -> child until root found.
        for(int i = 0; i < treeHeight; i++) {
            for(int j = 0, k = 0; j < lstParent.size() / 2; j++, k += 2) {
                temp = new MerkleNode();
                populateMerkleNode(temp, lstParent.get(k), lstParent.get(k + 1));
                lstChild.add(temp);
            }
            if(lstChild.size() == 1) {
              //  this.sMerkleRoot = lstChild.get(0).sHash; //todo: possibly needs to be removed and use method Block.setMerkleRoot() instead/outside class
                return lstChild.get(0).sHash;
            }
            lstParent = new ArrayList<>(lstChild);  //child becomes the parent for next iteration
            lstChild = new ArrayList<>(); // child is reset to empty list
        }

        //below code should never get ran.
        System.out.println("SOMETHING FAILED INSIDE:  Block.computeMerkleRoot()");
        return "SOMETHING FAILED";
    }



    /**
     * This method populates a Merkle node's left, right, and hash variables.
     * @param oNode
     * @param oLeftNode
     * @param oRightNode
     */
    private void populateMerkleNode(MerkleNode oNode, MerkleNode oLeftNode, MerkleNode oRightNode){

//TODO:check code
        oNode.oLeft = oLeftNode;
        oNode.oRight = oRightNode;
        oNode.sHash = BlockchainUtil.generateHash(oNode.oLeft.sHash + oNode.oRight.sHash);
    }


    // Hash this block, and hash will also be next block's previous hash.

    /**
     * This generates the hash for this block by combining the properties and hashing them.
     * @return
     */
    public String computeHash() {

        return new BlockchainUtil().generateHash(sMerkleRoot + iDifficulty + sMinerUsername + sNonce);
    }



    public int getDifficulty() {
        return iDifficulty;
    }


    public String getNonce() {
        return sNonce;
    }
    public void setNonce(String nonce) {
        this.sNonce = nonce;
    }

    public void setMinerUsername(String sMinerUsername) {
        this.sMinerUsername = sMinerUsername;
    }

    public String getHash() { return sHash; }

    public void setHash(String h) {this.sHash = h; }

    public synchronized void setMerkleRoot(String merkleRoot) { this.sMerkleRoot = merkleRoot; }




    /**
     * Run this to test your merkle tree logic.
     * @param args
     */
    public static void main(String[] args){

        ArrayList<String> lstItems = new ArrayList<>();
        Block oBlock = new Block();
        String sMerkleRoot;

        // These merkle root hashes based on "t1","t2" for two items, and then "t3","t4" added for four items.
        String sExpectedMerkleRoot_2Items = "3269f5f93615478d3d2b4a32023126ff1bf47ebc54c2c96651d2ac72e1c5e235";
        String sExpectedMerkleRoot_4Items = "e08f7b0331197112ff8aa7acdb4ecab1cfb9497cbfb84fb6d54f1cfdb0579d69";
        String sExpectedMerkleRoot_8Items = "36941ee71cbbb9196c4691026e68c8be5605332842a766bf069d631d2c7c8d47";

        lstItems.add("t1");
        lstItems.add("t2");


        // *** BEGIN TEST 2 ITEMS ***

        sMerkleRoot = oBlock.computeMerkleRoot(lstItems);

        if(sMerkleRoot.equals(sExpectedMerkleRoot_2Items)){

            System.out.println("Merkle root method for 2 items worked!");
        }

        else{
            System.out.println("Merkle root method for 2 items failed!");
            System.out.println("Expected: " + sExpectedMerkleRoot_2Items);
            System.out.println("Received: " + sMerkleRoot);

        }


        // *** BEGIN TEST 4 ITEMS ***

        lstItems.add("t3");
        lstItems.add("t4");
        sMerkleRoot = oBlock.computeMerkleRoot(lstItems);

        if(sMerkleRoot.equals(sExpectedMerkleRoot_4Items)){

            System.out.println("Merkle root method for 4 items worked!");
        }

        else{
            System.out.println("Merkle root method for 4 items failed!");
            System.out.println("Expected: " + sExpectedMerkleRoot_4Items);
            System.out.println("Received: " + sMerkleRoot);

        }

        // *** BEGIN TEST 8 ITEMS ***

        lstItems.add("t5");
        lstItems.add("t6");
        lstItems.add("t7");
        lstItems.add("t8");
        sMerkleRoot = oBlock.computeMerkleRoot(lstItems);

        if(sMerkleRoot.equals(sExpectedMerkleRoot_8Items)){

            System.out.println("Merkle root method for 8 items worked!");
        }

        else{
            System.out.println("Merkle root method for 8 items failed!");
            System.out.println("Expected: " + sExpectedMerkleRoot_8Items);
            System.out.println("Received: " + sMerkleRoot);

        }


    }
}
