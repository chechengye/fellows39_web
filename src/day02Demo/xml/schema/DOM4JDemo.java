package day02Demo.xml.schema;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

public class DOM4JDemo {

    @Test
    public void testFn(){
        //创建xml文档流对象
        SAXReader saxReader = new SAXReader();
        try {
            //获取文档对象
            Document document = saxReader.read("src/myxml.xml");
            Element rootElement = document.getRootElement();
            System.out.println(rootElement.getName());
            List<Element> elements = rootElement.elements();
            for(Element e : elements){
                if(e.getName().equals("person")){

                    if(e.attributeValue("id").equals("1")){
                        List<Element> person = e.elements();
                        for(Element p : person){

                            if(p.getName().equals("name")){
                                System.out.println("姓名:" + p.getText());
                            }
                            if(p.getName().equals("age")){
                                System.out.println("年龄 : " + p.getText());
                            }

                        }
                    }

                }

            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
