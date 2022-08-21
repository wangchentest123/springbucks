package geektime.spring.springbucks.service;

import com.github.pagehelper.PageHelper;
import geektime.spring.springbucks.mapper.CoffeeMapper;
import geektime.spring.springbucks.model.Coffee;
import geektime.spring.springbucks.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeMapper coffeeMapper;

    @Autowired
    private RedisTemplate<String, List<Coffee>> redisTemplate;

    public Optional<Coffee> findOneCoffee(String name) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", exact().ignoreCase());
        Optional<Coffee> coffee = coffeeRepository.findOne(
                Example.of(Coffee.builder().name(name).build(), matcher));
        log.info("Coffee Found: {}", coffee);
        return coffee;
    }

    public List<Coffee> getCoffeeList(Map map) {
        //todo 参数校验
        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");
        // 分页插件
        PageHelper.startPage(pageNum, pageSize);

        String ids = (String) map.get("ids");
        List<Long> longIds = null;
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            if (split.length <= 10) {
                longIds = Arrays.stream(split)
                        .map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
            }
        }
        List<Coffee> coffeeList = coffeeMapper.getCoffeeList(longIds);
        //加入缓存
        redisTemplate.opsForValue().set("ids", coffeeList);
        return coffeeList;
    }

    @Transactional
    public void addCoffee(Coffee coffee) {
        coffeeMapper.addCoffee(coffee);
    }
    @Transactional
    public void editCoffee(Coffee coffee) {
        coffeeMapper.editCoffee(coffee);
    }

    @Transactional
    public void delCoffee(int id) {
        coffeeMapper.deleteById(id);
    }

}
