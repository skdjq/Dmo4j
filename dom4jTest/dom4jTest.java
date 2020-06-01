package dom4jTest;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.util.EventListener;
import java.util.List;
public class dom4jTest {
    public static void main(String[] args) throws Exception {
        deleteName(0,0);
    }
    public static void selectName() throws Exception {
        /*
         * 得到xml文件所有的name 标签
         * 1.创建解析器
         * 2.得到document文件
         * 3.得到根节点
         *
         * 4.得到<p1>
         * 5.得到<name>
         * 6.得到name里面的值
         * */
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src/day04/day04_1.xml");
        Element root = document.getRootElement();
        List<Element> list1 = root.elements("p1");
        for(Element e1:list1){
            List<Element> list2 = e1.elements("name");
            for(int i = 0;i < list2.size();++i){
                Element name = list2.get(i);
                String s = name.getText();
                System.out.println(s);
            }
        }
    }
    public static void addWeight(int n) throws Exception {
        /*
         * 在第n个p1标签的末尾添加一个Weight标签
         * */
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src/day04/day04_1.xml");
        Element root = document.getRootElement();
        List<Element> list = root.elements("p1");
        Element p1 = list.get(n);
        Element Weight1 = p1.addElement("Weight");      //addElement 添加标签
        Weight1.setText("140");     //setText 设置文本
        //回写
        OutputFormat format = OutputFormat.createPrettyPrint();     //有缩进的效果
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("src/day04/day04_1.xml"),format);
        xmlWriter.write(document);
        xmlWriter.close();
    }

    public static void addAgeBefore() throws Exception {
        /*
         * 在第一个p1中特定的标签前添加标签（本例在age标签前添加新标签School）
         * */
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src/day04/day04_1.xml");
        Element root = document.getRootElement();
        Element p1 = root.element("p1");
        //得到p1下所有标签
        List<Element> list = p1.elements();
        //创建School标签
        Element school = DocumentHelper.createElement("School");
        //添加文本
        school.setText("AHUT");
        //特定位置添加
        list.add(2,school);
        //回写
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("src/day04/day04_1.xml"),format);
        xmlWriter.write(document);
        xmlWriter.close();
    }
    public static void deleteName(int m,int n) throws Exception {
        /*
         * 删除第m+1个p1下的第n+1个name元素
         * */
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src/day04/day04_1.xml");
        Element root = document.getRootElement();
        List<Element> list1 = root.elements("p1");
        Element p1 = list1.get(m);
        List<Element> list = p1.elements("name");
        Element name1 = list.get(n);
        p1.remove(name1);       //删除节点
        //重写xml
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("src/day04/day04_1.xml"), format);
        xmlWriter.write(document);
        xmlWriter.close();
    }
}
