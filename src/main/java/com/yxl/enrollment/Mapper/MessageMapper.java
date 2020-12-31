package com.yxl.enrollment.Mapper;

import com.yxl.enrollment.Module.MySql.Message;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MessageMapper extends Mapper<Message> {

    @Select("select * from message where to_email=#{email}")
    List<Message> selectAllByEmail(String email);

    @Select("select * from message where to_email=#{email} and is_read=0")
    List<Message> selectNewByEmail(String email);

    @Update("update message set is_read=1 where is_read=0 and to_email=#{email}")
    void readAllNewByEmail(String email);
}
