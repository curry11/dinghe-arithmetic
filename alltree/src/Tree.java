import java.util.LinkedList;
import java.util.List;

/**
 * @program: dinghe-arithmetic
 * @description:  普通二叉树
 * @author: wl.ding
 * @create: 2020-12-04 13:46
 **/
public class Tree {


    //使用一维数组层序构建一棵完全二叉树  递归
    //初始i为0则左子树为2i+1右子树为2i+2 初始i为1则左子树为2i右子树为2i+1
    public static  Node creatTreeRecursion(int[] arr,int i){
        Node node = null;

        if(i >= arr.length)
            return null;

        node = new Node(arr[i]);
        node.LeftNode = creatTreeRecursion(arr,i*2+1);    //递归构建左子树
        node.RightNode = creatTreeRecursion(arr,i*2+2);   //递归构建右子树

        return node;
    }

    //使用一维数组层序构建一棵完全二叉树  非递归  通过list维护对象之间的关联
    public static Node createTree(int[] array) {
        List<Node> nodeList  = new LinkedList<Node>();
        // 将一个数组的值依次转换为Node节点
        for (int nodeIndex = 0; nodeIndex < array.length; nodeIndex++) {
            nodeList.add(new Node(array[nodeIndex]));
        }
        // 对前lastParentIndex-1个父节点按照父节点与孩子节点的数字关系建立二叉树
        for (int parentIndex = 0; parentIndex < array.length / 2 - 1; parentIndex++) {
            // 左孩子
            nodeList.get(parentIndex).LeftNode = nodeList
                    .get(parentIndex * 2 + 1);
            // 右孩子
            nodeList.get(parentIndex).RightNode = nodeList
                    .get(parentIndex * 2 + 2);
        }
        // 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理
        int lastParentIndex = array.length / 2 - 1;
        // 左孩子
        nodeList.get(lastParentIndex).LeftNode = nodeList
                .get(lastParentIndex * 2 + 1);
        // 右孩子,如果数组的长度为奇数才建立右孩子
        if (array.length % 2 == 1) {
            nodeList.get(lastParentIndex).RightNode = nodeList
                    .get(lastParentIndex * 2 + 2);
        }

        return nodeList.get(0);
    }

    //先序遍历(前序) 递归  先访问根节点，左子树右子树
    public static void preOrderRecursion(Node node){
        if (node == null)
            return;
        System.out.print(node.data);
        System.out.print(" ");
        preOrderRecursion(node.LeftNode);
        preOrderRecursion(node.RightNode);
    }

    //先序遍历(前序) 非递归 通过栈来操作
    /*
    * 1.先访问node节点，将node节点入栈
    * 2.判断node节点是否有左子树如果有则将该节点输出入队列，并且将该左子树置为node继续判断，如果没有则将之前入栈的node出栈，判断其是否有右子树，如果有则
    * 将该右子树置为node继续判断该右子树是否有左子树。
    * */
    public static void preOrder(Node node){
        if(node == null)
            return;
        System.out.println();
        while(node != null || Inn.getTop()!=0){  //节点不为空 或者栈顶不为0
           if(node != null){
                System.out.print(node.data);
                System.out.print(" ");
                Inn.push(node); //入栈
                node = node.LeftNode;
            }else {
                Node temp = Inn.pop();//出栈
                node = temp.RightNode;
            }
        }
    }

    //中序遍历 递归  先访问左子树，根节点右子树
    public static void inOrderRecursion(Node node){
        if (node == null)
            return;
        inOrderRecursion(node.LeftNode);
        System.out.print(node.data);
        System.out.print(" ");
        inOrderRecursion(node.RightNode);
    }

    //中序遍历 递归  先访问左子树，根节点右子树
    /*
     * 1.先访问node节点，将node节点入栈
     * 2.判断node节点是否有左子树如果有则将该节点入队列，并且将该左子树置为node继续判断，如果没有则将之前入栈的node出栈，输出，判断其是否有右子树，如果有则
     * 将该右子树置为node继续判断该右子树是否有左子树。
     * */
    public static void inOrder(Node node){
        if(node == null)
            return;
        System.out.println();
        while(node != null || Inn.getTop()!=0){   //节点不为空 或者栈顶不为0
            if(node != null){
                Inn.push(node);  //入栈
                node = node.LeftNode;
            }else {
                Node temp = Inn.pop(); //出栈
                System.out.print(temp.data);
                System.out.print(" ");
                node = temp.RightNode;
            }
        }
    }

    //后序遍历 递归  先访问左子树，右子树 根节点
    public static void postOrderRecursion(Node node){
        if (node == null)
            return;
        postOrderRecursion(node.LeftNode);
        postOrderRecursion(node.RightNode);
        System.out.print(node.data);
        System.out.print(" ");
    }

    //后序遍历 非递归  先访问左子树，右子树 根节点
    /*
    * 任一结点P，先将其入栈。如果P不存在左孩子和右孩子，则可以直接访问它；
    * 如果P存在左孩子或者右孩子，但是其左孩子和右孩子都已被访问过了(通过preNode记录是否访问过子节点)，则同样可以直接访问该结点。若非上述两种情况，
    * 则将P的右孩子和左孩子依次入栈，这样就保证了每次取栈顶元素的时候，左孩子在右孩子前面被访问，左孩子和右孩子都在根结点前面被访问。
    * */
    public static void postOrder(Node node){
        if (node == null)
            return;
        System.out.println();
        Inn.push(node);

        Node preNode = null;

        while (Inn.getTop() != 0){     //栈顶不为0
            Node curNode = Inn.getNode();
            if(curNode.RightNode == null && curNode.LeftNode == null ||    //左右节点为空
                    (preNode != null && (curNode.LeftNode == preNode || curNode.RightNode == preNode))){//该节点的左右节点被访问过，用pre记录最后被访问的节点
                Node node1 = Inn.pop();  //出栈
                System.out.print(node1.data);
                System.out.print(" ");
                preNode = node1;
            }else {
                if(curNode.RightNode != null){
                    Inn.push(curNode.RightNode);  //入栈
                }
                if (curNode.LeftNode != null){   //入栈
                    Inn.push(curNode.LeftNode);
                }
            }
        }
    }

    //层遍历 非递归  一层一层的访问
    /*
     * 先将二叉树根节点入队，然后出队，判断是否有左子树或者右子树，然后入队。依次类推
     * */
    public static void levelOrder(Node node){

        if (node == null)
            return;

        Queue.enQueue(node);

        while (Queue.getStatus()){   //如果头不等于尾
            Node node1 = Queue.deQueue();  //出队列
            System.out.print(node1.data);
            System.out.print(" ");
            if(node1.LeftNode != null)
                Queue.enQueue(node1.LeftNode);  //入队列
            if(node1.RightNode != null)
                Queue.enQueue(node1.RightNode);   //入队列
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11};
        //Node node = creatTreeRecursion(arr,0);
        Node node = createTree(arr);
        System.out.println("前序遍历");
        preOrderRecursion(node);
        preOrder(node);
        System.out.println();
        System.out.println("中续遍历");
        inOrderRecursion(node);
        inOrder(node);
        System.out.println();
        System.out.println("后续遍历");
        postOrderRecursion(node);
        postOrder(node);
        System.out.println();
        System.out.println("层序遍历");
        levelOrder(node);
    }
}

