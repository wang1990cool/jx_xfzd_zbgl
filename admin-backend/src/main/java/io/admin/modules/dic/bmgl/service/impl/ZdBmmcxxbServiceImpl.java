package io.admin.modules.dic.bmgl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.dic.bmgl.dao.ZdBmmcxxbDao;
import io.admin.modules.dic.bmgl.entity.ZdBmmcxxbEntity;
import io.admin.modules.dic.bmgl.service.ZdBmmcxxbService;


@Service("zdBmmcxxbService")
public class ZdBmmcxxbServiceImpl extends ServiceImpl<ZdBmmcxxbDao, ZdBmmcxxbEntity> implements ZdBmmcxxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdBmmcxxbEntity> page = this.selectPage(
                new Query<ZdBmmcxxbEntity>(params).getPage(),
                new EntityWrapper<ZdBmmcxxbEntity>()
        );

        return new PageUtils(page);
    }

}
