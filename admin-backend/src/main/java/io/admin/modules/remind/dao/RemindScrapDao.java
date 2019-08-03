package io.admin.modules.remind.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import io.admin.modules.remind.entity.ViewRemindScrapEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RemindScrapDao extends BaseMapper<ViewRemindScrapEntity> {
	
}
