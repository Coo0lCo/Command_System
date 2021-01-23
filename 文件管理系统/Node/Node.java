package 算法and数据结构.数据结构.多叉树.文件管理系统.Node;

import java.util.LinkedList;

/**
 * @author Doug Li Yong Jie
 *      --->结点的数据存储结构
 *
 */
public class Node {
    //储存孩子结点
    public LinkedList<Node> children;

    //指向双亲的指针
    public Node pre;

    //储存当前结点的数据
    public String date;
    //用于区分当前结点是为文件还是文件夹{文件-->0文件夹->1}
    public int i;

    public Node (){
        this.date=null;
        this.children=null;
        this.pre=null;
        this.i=1;
    }
    public Node(String date,Node pre,int i){
        this.date=date;
        this.i=i;
        this.children=new LinkedList<>();
        this.pre=pre;
    }
}