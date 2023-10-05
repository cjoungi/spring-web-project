package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

    @Select("SELECT sysdate FROM dual")
    public String getTime();

    //TimeMapper.xml에서 처리
    public String getTime2();

}
