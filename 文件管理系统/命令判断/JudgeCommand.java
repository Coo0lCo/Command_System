package 算法and数据结构.数据结构.多叉树.文件管理系统.命令判断;

import 算法and数据结构.数据结构.多叉树.文件管理系统.creatTree.creatTree;
import 算法and数据结构.数据结构.多叉树.文件管理系统.命令解析.command_parsing;

import java.util.Scanner;

/**
 * @author Doug Li Yong Jie
 *      --->用户输出
 *          以及输入内容的解析
 *          判断是否为正确命令
 *          做出相应回馈
 *          注意！！！--->该操作是可重复允许的
 *
 */
public class JudgeCommand{
    public JudgeCommand(){
        Scanner sc=new Scanner(System.in);
        command_parsing command=new command_parsing();
        creatTree creat=new creatTree();
        while (true){
            String commandString=sc.nextLine();
            if ("".equals(commandString)){
                System.out.println("Error: command not found: "+"");
                continue;
            }
            if (command.cd__(commandString)){
                    creat.cd__();
            }else if(command.cdxxx(commandString)){
                    creat.cd(command.date);
            }else if (command.mkdirxxx(commandString)){
                    creat.mkdir(command.date);
            }else if (command.touchxxx(commandString)){
                    creat.touch(command.date);
            }else if (command.ls_l(commandString)){
                    creat.ls_l();
            }else if (command.rmxxx(commandString)){
                    creat.rm(command.date);
            }else if (command.rm_rxxx(commandString)){
                    creat.rm_r(command.date);
            }else if (command.whereisxxx(commandString)){
                    creat.whereIs(command.date);
            }else if (command.pwd(commandString)){
                    creat.pwd();
            }else {
                System.out.println("Error: command not found: "+commandString);
            }
        }
    }
}
