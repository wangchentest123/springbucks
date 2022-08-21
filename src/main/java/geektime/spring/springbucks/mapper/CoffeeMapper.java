package geektime.spring.springbucks.mapper;

import geektime.spring.springbucks.model.Coffee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
@MapperScan(value = "mapper")
public interface CoffeeMapper {
    @Select("select * from t_coffee order by id")
    List<Coffee> findAllWithRowBounds(RowBounds rowBounds);

    @Select("select * from t_coffee order by id")
    List<Coffee> findAllWithParam(@Param("pageNum") int pageNum,
                                  @Param("pageSize") int pageSize);

    List<Coffee> getCoffeeList(List<Long> ids);

    int deleteById(@Param("id") int id);

    int addCoffee(@Param("coffee") Coffee coffee);

    int editCoffee(@Param("coffee") Coffee coffee);
}
