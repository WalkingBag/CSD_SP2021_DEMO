/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;

public class BSTree {

    Node root;

    BSTree() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    void clear() {
        root = null;
    }

    void visit(Node p) {
        System.out.print("p.info: ");
        if (p != null) {
            System.out.println(p.info + " ");
        }
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void breadth(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void preOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    void inOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    void postOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder(p.left, f);
        postOrder(p.right, f);
        fvisit(p, f);
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void insert(String xForest, int xRate, int xSound) {
        //You should insert here statements to complete this function
        if (xForest.charAt(0) == 'B') {
            return;
        }
        if (this.isEmpty()) {
            root = new Node(new Boo(xForest, xRate, xSound));

        }

        Node temp = root;
        int bstDiv;
        while (true) {
            bstDiv = xRate - temp.info.rate;
            if (bstDiv == 0) {
                return;
            } else if (bstDiv < 0) {
                if (temp.left == null) {
                    temp.left = new Node(new Boo(xForest, xRate, xSound));
                    return;
                }
                temp = temp.left;
            } else if (bstDiv > 0) {
                if (temp.right == null) {
                    temp.right = new Node(new Boo(xForest, xRate, xSound));
                    return;
                }
                temp = temp.right;
            }
        }

    }

//Do not edit this function. Your task is to complete insert function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        inOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void fvisit2(Node p, RandomAccessFile f) throws Exception {
        if (p != null && p.info.sound < 6) {
            f.writeBytes(p.info + " ");
        }
    }

    void preOrder2(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        fvisit2(p, f);
        preOrder2(p.left, f);
        preOrder2(p.right, f);
    }

    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        preOrder2(root, f);
        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    int counter = 0;
    Node fourthNode;

    void traverseTilFourth(Node p) throws Exception {
        if (p == null) {
            return;
        }

        if (counter >= 4) {
            return;
        }

        if (++counter == 4) {
            fourthNode = p;
            return;
        }

        //traverse
        traverseTilFourth(p.left);
        traverseTilFourth(p.right);
    }

    Node deleteNode(Node node, int rate) {
        if (node == null) {
            return null;
        }
        int bstDiv = rate - node.info.rate;
        //traverse til meet 0.
        if (bstDiv < 0) {
            node.left = deleteNode(node.left, rate);
            return node;
        } else if (bstDiv > 0) {
            node.right = deleteNode(node.right, rate);
            return node;
        } else {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left != null && node.right == null) {
                return node.left;
            } else if (node.left == null && node.right != null) {
                return node.right;
            } else {
                Node rightMost = node.left;
                while (rightMost.right != null) {
                    rightMost = rightMost.right;
                }
                node.info = rightMost.info;
                node.left = deleteNode(node.left, rightMost.info.rate);
                return node;
            }
        }
    }

    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        traverseTilFourth(root);
        deleteNode(root, fourthNode.info.rate);
        //------------------------------------------------------------------------------------
        preOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void rotateRight(Node node) {
        Node movedRoot = new Node(node.info);
        movedRoot.left = node.left.right;
        movedRoot.right = node.right;

        node.info = node.left.info;
        node.left = node.left.left;
        node.right = movedRoot;
    }

    void f4() throws Exception {
        clear();
        loadData(13);;
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        counter = 0;
        traverseTilFourth(root);
        if (fourthNode.left != null){
            rotateRight(fourthNode);
        }
        //------------------------------------------------------------------------------------
        preOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

}
