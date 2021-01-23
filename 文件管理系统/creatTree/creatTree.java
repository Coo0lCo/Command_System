package 算法and数据结构.数据结构.多叉树.文件管理系统.creatTree;

import 算法and数据结构.数据结构.多叉树.文件管理系统.Node.Node;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author Doug Li Yong Jie
 *  --->操作实现
 *          1.添加
 *          2.删除
 *          3.查找
 *          4.路径--->遍历----->存储路径结点---->选取怎样的数据结构存储
 */
public class creatTree {
    //树根
    Node root;

    //当前目录结点
    Node thisNode;

    //用栈存储路径 弹栈输出路径
    Stack<Node> stack;

    //违法命名哈希表
    HashMap<String,Boolean> IllegalCommand;

    public creatTree(){
        //根结点
        this.root=new Node("My Computer",null,1);
        //创建一个栈
        this.stack=new Stack<>();
        //这里相当于把指向root的指针多添加了一个-->thisNode
        this.thisNode=root;
        root.pre=null;
        IllegalCommand=new HashMap<>();
        IllegalCommand.put("..",true);
        thisNode.children.add(new Node("A",thisNode,1));
        thisNode.children.add(new Node("B",thisNode,1));
        thisNode.children.add(new Node("C",thisNode,1));
        thisNode.children.add(new Node("D",thisNode,1));
    }
    public boolean isIllegalName(String Name){
        if(IllegalCommand.get(Name)!=null){
            return true;
        }else {
            return false;
        }
    }
    /**
     * cd xxx:切换文件夹
     * @param date 待切换目录
     */
    public void cd(String date){
            for(int i = 0 ; i < thisNode.children.size() ;i++){
                if(date.equals(thisNode.children.get(i).date)){
                    if(isFolder(thisNode.children.get(i))){
                        thisNode=thisNode.children.get(i);
                        System.out.println("已移动至-->"+date+" 文件夹下！");
                    }else {
                        System.out.println("cd: not a directory: "+date);
                    }
                    return;
                }
            }
            System.out.println("cd: not a directory: "+date);
    }
    /**
     * cd .. :返回上一个文件夹
     */
    public void cd__(){
        if (thisNode.pre!=null){
            thisNode=thisNode.pre;
            System.out.println("已返回上一级！");
        }
    }

    /**
     * ls -l:罗列当前为文件夹的文件或文件夹
     */
    public void ls_l(){
        if(root == thisNode){
            System.out.print(root.date+"/ ");
        }else {
            SearchThisNode(thisNode);
        }
        System.out.print(" :");
        for (int i = 0; i < thisNode.children.size(); i++) {
            System.out.print(thisNode.children.get(i).date+", ");
        }
        System.out.println();
    }
    private void SearchThisNode(Node thisNode){
        if(thisNode == null){
            return;
        }
        stack.push(thisNode);
        SearchThisNode(thisNode.pre);
        pop(stack);
    }
    private void pop(Stack<Node> stack){
        while(!stack.isEmpty()){
            System.out.print(stack.pop().date+"/ ");
        }
    }
    /**
     * rm xxx:删除文件夹
     * @param date  待删除文件夹
     */
    public void rm(String date){
        if (thisNode.children.size() == 0){
            System.out.println("cd: not a directory: "+date);
            return;
        }
        for (int i = 0; i < thisNode.children.size(); i++) {
            if (date.equals(thisNode.children.get(i).date)){
                if (isFolder(thisNode.children.get(i))){
                    thisNode.children.remove(i);
                    System.out.println("文件夹删除成功");
                    return;
                }
                System.out.println("该命令无法删除文件，请输入（rm -r 文件名） ，删除文件");
                return;
            }
        }
        System.out.println("cd: not a directory: "+date);
    }

    /**
     * rm -r xxx:删除文件
     * @param date  待删除文件
     */
    public void rm_r(String date){
        if (thisNode.children.size() == 0){
            System.out.println("cd: not a directory: "+date);
            return;
        }
        for (int i = 0; i < thisNode.children.size(); i++) {
            if (date.equals(thisNode.children.get(i).date)){
                if (isFolder(thisNode.children.get(i))){
                    System.out.println("该命令无法删除文件夹，请输入（rm 文件名） ，删除文件");
                    return;
                }
                thisNode.children.remove(i);
                System.out.println("文件删除成功");
                return;
            }
        }
        System.out.println("cd: not a directory: "+date);
    }

    /**
     * mkdir xxx:创建文件夹
     * @param date  待创建文件夹
     */
    public void mkdir(String date){
        if(isIllegalName(date)){
            System.out.println("非法的文件名");
            return;
        }
        if (!isDuplicateName(thisNode,date)){
            this.thisNode.children.add(new Node(date,thisNode,1));
            System.out.println("文件夹创建成功！");
        }else {
            System.out.println("文件夹重名");
        }
    }

    /**
     * touch xxx:创建文件
     * @param date  待创建文件
     */
    public void touch(String date){
        if(isIllegalName(date)){
            System.out.println("非法的文件名");
            return;
        }
        if (!isDuplicateName(thisNode,date)){
            this.thisNode.children.add(new Node(date,thisNode,0));
            System.out.println("文件添加成功！");
        }else {
            System.out.println("文件重名");
        }
    }

    /**
     * whereIs xxx:全局查找xxx的文件路径（可以是多个路径）
      * @param date 待查找文件或者文件夹
     */
    public void whereIs(String date){
        SearchDate(root,date);
        System.out.println();
    }
    private void SearchDate(Node root,String date){
        if(root == null)return;
        if(date.equals(root.date)){
            SearchThisNode(root);
            return;
        }
        for (int i = 0; i < root.children.size(); i++) {
            SearchDate(root.children.get(i),date);
        }
    }
    /**
     * pwd:显示当前路径
     */
    public void pwd(){
        SearchThisNode(thisNode);
        System.out.println();
    }

    /**
     * isFolder 用于判断该结点是否为文件夹
     * @param node 待判断结点
     * @return true-->是文件夹 false-->是文件
     */
    public boolean isFolder(Node node){
        return node.i==1;
    }

    /**
     * isDuplicateName 用于判断文件是否重名
     * @param node  待查询文件的父亲
     * @param Name  需要添加的文件或者文件夹名
     * @return  true-->文件重名 false-->文件微重名
     */
    public boolean isDuplicateName(Node node,String Name){
        if (node.children.size()==0)return false;
        for(int i = 0 ; i < node.children.size() ;i++){
            if(Name.equals(node.children.get(i).date)){
                return true;
            }
        }
        return false;
    }
}
