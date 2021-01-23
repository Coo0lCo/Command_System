package 算法and数据结构.数据结构.多叉树.文件管理系统.命令解析;

/**
 * @author Li Yong Jie
 *     -->命令解析:
 *                  1.ls -l   罗列出当前结点下的所有文件
 *                  2.rm xxx  删除文件夹
 *                  3.rm -r xxx  删除文件
 *                  3.cd xxx  切换当前位置
 *                  4.mkdir xxx  创建文件夹
 *                  5.touch xxx  创建文件
 *                  6.whereis xxx 查找xxx的文件路径
 *                  7.pwd 显示当前路径
 */
public class command_parsing {
    public String date;
    public command_parsing(){
        this.date=null;
    }
    /**
     *
     * @param command   待处理字符串
     * @return 返回处理好的字符串
     */
     private String strprocess(String command) {
        String s=" ";
        int indexEnd = command.length()-1;
        for(int i = command.length()-1 ; i > -1 ; i--){
            if(s.equals(command.charAt(i)+"")){
                indexEnd = i;
                break;
            }
        }
        this.date=command.substring(indexEnd+1);
        return command.substring(0,indexEnd);
    }
//========================================================
    public boolean  ls_l(String command){
        String s="ls -l";
        return s.equals(command);
    }

    public boolean rmxxx(String command){
        String s="rm";
        return s.equals(strprocess(command));
    }

    public boolean rm_rxxx(String command){
         String s="rm -r";
         return s.equals(strprocess(command));
    }

    public boolean cdxxx(String command){
        String s="cd";
        return s.equals(strprocess(command));
    }

    public boolean cd__(String command){
         String s="cd ..";
         return s.equals(command);
    }

    public boolean mkdirxxx(String command){
        String s="mkdir";
        return s.equals(strprocess(command));
    }

    public boolean touchxxx(String command){
        String s="touch";
        return s.equals(strprocess(command));
    }

    public boolean whereisxxx(String command){
        String s="whereis";
        return s.equals(strprocess(command));
    }

    public boolean pwd(String command){
        String s="pwd";
        return s.equals(command);
    }

}
