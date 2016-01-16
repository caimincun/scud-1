import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/22.
 */
public class GenericFgw {public static void main(String[] args) throws Exception{

    StringBuffer abstr = new StringBuffer();
    BufferedReader reader= new BufferedReader(new InputStreamReader(new FileInputStream(new File("f://fgw.txt")),"GB2312"));
    String temp;
    while((temp=reader.readLine())!=null){
        abstr.append(temp);
        abstr.append("\r\n");
    }
    Parser parser = Parser.createParser(abstr.toString(), "utf-8");
    NodeList list = parser.parse(new TagNameFilter("TABLE"));
    Node node = list.elementAt(0);
    NodeList trs = node.getChildren();
    List<Map<String,Object>> maps = new LinkedList<>();
    for (SimpleNodeIterator trIterator = trs.elements(); trIterator.hasMoreNodes(); ){
        Node tr = trIterator.nextNode();
        if ((tr.getText().indexOf("TR"))!=-1) {
            NodeList tdList = tr.getChildren();
            if (tdList != null&&tdList.size()>1) {
                Map<String,Object> map = new LinkedHashMap<>();
                map.put("name",tdList.elementAt(0).toPlainTextString());
                map.put("biaozhun",tdList.elementAt(1).toPlainTextString());
                map.put("danwei",tdList.elementAt(2).toPlainTextString());
                double max = 0 ,min=100;
                for (int i  = 3 ;i<tdList.size();i++) {
                    Node td = tdList.elementAt(i);
                    String str =td.toPlainTextString();
                    if (str.equals("_"))
                        continue;
                    Double num = Double.parseDouble(str);
                    if (num>max)
                        max=num;
                    if (num<min)
                        min=num;
                }
                map.put("max",max);
                map.put("min",min);
                maps.add(map);
            }
        }
    }
    StringBuilder html=new StringBuilder();
    System.out.println(maps.size());
    for (Map<String,Object> map : maps){
        html.append("<tr>");
        for (Map.Entry<String,Object> entry : map.entrySet()){
            html.append("<td>");
            html.append(entry.getValue());
            html.append("</td>");
        }
        html.append("</tr>\n\r");

    }

    System.out.println(html.toString());
}

}


