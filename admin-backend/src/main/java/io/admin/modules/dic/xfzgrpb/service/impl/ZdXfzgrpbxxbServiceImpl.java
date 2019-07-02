package io.admin.modules.dic.xfzgrpb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.dic.xfzgrpb.dao.ZdXfzgrpbxxbDao;
import io.admin.modules.dic.xfzgrpb.entity.ZdXfzgrpbxxbEntity;
import io.admin.modules.dic.xfzgrpb.service.ZdXfzgrpbxxbService;


@Service("zdXfzgrpbxxbService")
public class ZdXfzgrpbxxbServiceImpl extends ServiceImpl<ZdXfzgrpbxxbDao, ZdXfzgrpbxxbEntity> implements ZdXfzgrpbxxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdXfzgrpbxxbEntity> page = this.selectPage(
                new Query<ZdXfzgrpbxxbEntity>(params).getPage(),
                new EntityWrapper<ZdXfzgrpbxxbEntity>()
        );

        return new PageUtils(page);
    }

}
