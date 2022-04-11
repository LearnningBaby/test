package com.teacher.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teacher.domain.Class;
import com.teacher.domain.Ins;
import com.teacher.domain.Student;
import com.teacher.domain.StudentDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StuMapper extends BaseMapper<StudentDetail> {

    @Select("select cls_numb from tea_cls_tb where tea_numb=#{teaNumb}")
    List<Long> selectClsNumb(Long teaNumb);

    @Select("select * from cls_table where cls_numb=#{aLong}")
    Class selectClass(Long aLong);

    @Select("select cls_name from cls_table where cls_numb=#{clsNumb}")
    String getClassName(Long clsNumb);

    @Select("select * from ins_table")
    List<Ins> getIns();

    @Insert("insert into stu_init_tb (ins_numb,stu_id,cls_numb,password,nick_name,nick_photo,online_status) values (#{insNumb},#{stuId},#{clsNumb},#{password},#{nickName},#{nickPhoto},#{onlineStatus})")
    boolean addStu(Student student);

    @Delete("delete from stu_init_tb where ins_numb=#{ins_numb} and stu_id=#{stu_id}")
    boolean delStu(Long ins_numb, Long stu_id);
}
