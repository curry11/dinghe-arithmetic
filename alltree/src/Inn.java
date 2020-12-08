

/**
 * @program: dinghe-arithmetic
 * @description:
 * @author: wl.ding
 * @create: 2020-12-07 11:45
 **/

public class Inn {

    static  class  Inns{   //模拟结构体方便扩展
        static int datas = 100; //队列的主体用来存储内容
        static int top = 0;  //栈顶
    }

    private static Node[] nodesArr = new Node[Inns.datas];

    //入栈
    static Node[] push(Node node){
        Inns.top++;   //栈底0不存数据  所以要先+
        nodesArr[Inns.top] = node;  //从栈顶人栈
        return nodesArr;
    }

    //出栈
    static Node pop(){
        Node node1 = nodesArr[Inns.top];
        Inns.top--;
        return node1;
    }

    //判断是不是top=0
    static int getTop(){
        return Inns.top;
    }

    //获取栈顶Node
    static Node getNode(){
        Node node = nodesArr[Inns.top];
        return node;
    }
}