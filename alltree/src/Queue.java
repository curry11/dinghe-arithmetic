/**
 * @program: dinghe-arithmetic
 * @description:
 * @author: wl.ding
 * @create: 2020-12-08 09:30
 **/
public class Queue {

    static class queues {   //模拟结构体方便扩展
        static int datas = 100; //队列的主体用来存储内容
        static int head = 1;  //队首
        static int tail = 1; // 队尾
    }

    static Node[] nodes = new Node[queues.datas]; //初始化存储体大小

    public static void enQueue(Node node){
        //入队
        nodes[queues.tail] = node;  //从队尾进队列
        queues.tail++;
    }

    public static Node deQueue(){
        Node node = nodes[queues.head];
        queues.head++;
        return node;
    }

    public static boolean getStatus(){
        if(queues.head == queues.tail){
            return false;
        }else {
            return true;
        }
    }

}
