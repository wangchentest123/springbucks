package geektime.spring.springbucks.getBeans;

import geektime.spring.springbucks.model.Coffee;
import geektime.spring.springbucks.model.CoffeeOrder;
import org.springframework.beans.factory.annotation.Autowired;


public class AnnotationBeans {

    @Autowired
    private Coffee coffee;
    @Autowired
    private CoffeeOrder coffeeOrder;


    /*
     * 注解方式获取beans
     * */
    public void annotationGetBeans() {
        System.out.println(coffee.getName());
        System.out.println(coffeeOrder.toString());
    }
}
