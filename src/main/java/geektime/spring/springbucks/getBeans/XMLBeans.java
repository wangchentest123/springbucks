package geektime.spring.springbucks.getBeans;

import geektime.spring.springbucks.model.Coffee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XMLBeans {


    /*
    * xml方式获取beans
    * */
    public void xmlGetBeans() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("xmlBeans.xml");
        Coffee coffee = applicationContext.getBean("coffee", Coffee.class);
        System.out.println(coffee.getName());
    }
}
