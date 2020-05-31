package com.wangzhi.mybatisdemo.mapper;

import com.wangzhi.mybatisdemo.domain.TCoffee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface TCoffeeMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(TCoffee record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(TCoffee record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    TCoffee selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(TCoffee record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(TCoffee record);

    /**
     * select list with rowBounds
     *
     * @param rowBounds pageHelper param
     * @return page data
     */
    List<TCoffee> listWithRowBounds(RowBounds rowBounds);

    /**
     * select list with params
     *
     * @param pageNum  第几页
     * @param pageSize 每页大小
     * @return page data
     */
    List<TCoffee> listWithParams(@Param("pageNum") int pageNum,
                                 @Param("pageSize") int pageSize);

    List<TCoffee> listAllWithPageInfo();
}