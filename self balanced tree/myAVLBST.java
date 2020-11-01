import static org.junit.Assert.fail;

public class myAVLBST {
	public static void main(String args[]) throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
		BALST<Integer,String> balst2 = new BALST<Integer,String>();	

		balst2.insert(10, "30");
        balst2.insert(20, "20");
        balst2.insert(30, "40");
        balst2.insert(40, "40");
        balst2.insert(35, "40");
        balst2.insert(50, "40");
        //record the number of keys and tree height right after insertion
        int sizeBeforRemove = balst2.numKeys();
        int heightBeforeRemove = balst2.getHeight();
        //remove two of the nodes
        balst2.remove(20);
        balst2.remove(35);
        //record the number of keys and height now
        int heightAfterRemove = balst2.getHeight();
        int sizeAfterRemove = balst2.numKeys();
        //check the number of keys and height 
        
        }
        
	}

