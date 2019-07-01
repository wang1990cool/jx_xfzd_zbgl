package io.admin.modules.dic.ryxx.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.dic.ryxx.entity.ZdRyxxbEntity;

import java.util.List;
import java.util.Map;

/**
 * 人员信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-06-30 22:50:14
 */
public interface ZdRyxxbService extends IService<ZdRyxxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List<ZdRyxxbEntity> selectByssbmid(String ssbmid);
}

