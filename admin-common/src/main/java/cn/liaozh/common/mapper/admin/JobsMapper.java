package cn.liaozh.common.mapper.admin;

import cn.liaozh.common.core.basics.IBaseMapper;
import cn.liaozh.common.entity.admin.Jobs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 岗位表Mapper
 */
@Mapper
public interface JobsMapper extends IBaseMapper<Jobs> {

    @Select("SELECT * FROM la_jobs jobs INNER JOIN la_admin_jobs laj ON laj.jobs_id = jobs.id WHERE laj.admin_id = #{adminId} AND jobs.delete_time IS NULL")
    List<Jobs> getByAdminId(@Param("adminId") Integer adminId);

}
