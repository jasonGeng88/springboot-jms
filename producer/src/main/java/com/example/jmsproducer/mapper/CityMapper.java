package com.example.jmsproducer.mapper;

import com.example.jmsproducer.domain.City;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by jason-geng on 5/26/17.
 */
@Mapper
public interface CityMapper {

    @Select("SELECT * FROM CITY2 WHERE state = #{state}")
    City findByState(String state);

    @Insert("insert into city (name, state, country) values ('San Francisco', #{state}, 'US');")
    void insert(String state);

    @Select("SELECT count(*) FROM CITY")
    int count();

}
