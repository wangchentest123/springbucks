package geektime.spring.springbucks.getJsonOrXMLData;

import geektime.spring.springbucks.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JsonOrXmlController {

    @Autowired
    private CoffeeService coffeeService;


    /**
     * 返回json格式数据
     */
    @GetMapping(value = "/json")
    @ResponseBody
    public String getJson(@RequestBody Map map) {
       return coffeeService.getCoffeeList(map).toString();
    }

    /**
     * 返回xml格式数据
     */
    @GetMapping(value = "/xml", produces = "application/xml;charset=utf-8")
    @ResponseBody
    public String getXml(@RequestBody Map map) {
        return coffeeService.getCoffeeList(map).toString();
    }
}
