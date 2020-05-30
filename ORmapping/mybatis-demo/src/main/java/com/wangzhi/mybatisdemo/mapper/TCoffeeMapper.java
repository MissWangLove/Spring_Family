package com.wangzhi.mybatisdemo.mapper;

import com.wangzhi.mybatisdemo.domain.TCoffee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TCoffeeMapper {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(TCoffee record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(TCoffee record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    TCoffee selectByPrimaryKey(Long id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(TCoffee record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(TCoffee record);
}