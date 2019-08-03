/**
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.admin.modules.remind.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import io.admin.modules.remind.dao.RemindScrapDao;
import io.admin.modules.remind.entity.ViewRemindScrapEntity;
import io.admin.modules.remind.service.RemindScrapService;
import io.admin.modules.sys.dao.SysLogDao;
import io.admin.modules.sys.entity.SysLogEntity;
import io.admin.modules.sys.service.SysLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("remindScrapService")
public class RemindScrapServiceImpl extends ServiceImpl<RemindScrapDao, ViewRemindScrapEntity> implements RemindScrapService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String zbmc=(String)params.get("zbmc");

        String ssbmmc = (String)params.get("ssbmmc");

        Page<ViewRemindScrapEntity> page = this.selectPage(
            new Query<ViewRemindScrapEntity>(params).getPage(),
            new EntityWrapper<ViewRemindScrapEntity>()
                    .like(StringUtils.isNotBlank(zbmc),"zbmc", zbmc)
                    .like(StringUtils.isNotBlank(ssbmmc),"ssbmmc", ssbmmc)
        );

        return new PageUtils(page);
    }
}
