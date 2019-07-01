package io.admin.modules.dic.xfzzbpb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.dic.xfzzbpb.dao.ZdXfzbzpbxxbDao;
import io.admin.modules.dic.xfzzbpb.entity.ZdXfzbzpbxxbEntity;
import io.admin.modules.dic.xfzzbpb.service.ZdXfzbzpbxxbService;


@Service("zdXfzbzpbxxbService")
public class ZdXfzbzpbxxbServiceImpl extends ServiceImpl<ZdXfzbzpbxxbDao, ZdXfzbzpbxxbEntity> implements ZdXfzbzpbxxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdXfzbzpbxxbEntity> page = this.selectPage(
                new Query<ZdXfzbzpbxxbEntity>(params).getPage(),
                new EntityWrapper<ZdXfzbzpbxxbEntity>()
        );

        return new PageUtils(page);
    }

}
