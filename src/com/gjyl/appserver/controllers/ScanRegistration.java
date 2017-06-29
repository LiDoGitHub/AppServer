package com.gjyl.appserver.controllers;

import com.gjyl.appserver.pojo.Registration;
import com.gjyl.appserver.service.RegistrationService;
import com.gjyl.appserver.utils.DateUtils;
import com.gjyl.appserver.utils.SMSUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * Created by LiD on 2017/6/21.
 */
@Controller
public class ScanRegistration {

    @Resource
    private RegistrationService registrationService;

    /**
     * 每隔1分钟,获取挂号列表,发送短信
     */
    @Scheduled(fixedRate = 60*1000)
    public void scanRegistration() throws Exception {
        Calendar c=Calendar.getInstance();
        System.out.println("开始扫描挂号信息.......");
        List<Registration> list = registrationService.getRegList();
        for (Registration reg : list) {
            System.out.println("预约挂号时间差.............:"+DateUtils.dateDiff(reg.getReservationdate(),c.getTime()));
            if (DateUtils.dateDiff(reg.getReservationdate(),c.getTime())>=30&&DateUtils.dateDiff(reg.getReservationdate(),c.getTime())<=45){
                StringBuilder sb=new StringBuilder();
                sb.append("【儿医天使】您预约的")
                        .append(reg.getSection()+" "+reg.getDocname()+"医生的挂号,将于")
                        .append(DateUtils.getDateStr(reg.getReservationdate()))
                        .append("开始。别忘了准时到达医院哦，谢谢！如有疑问请致电028-85056688 ");
                Boolean isSent = SMSUtils.SendMsg(reg.getPhone(), sb.toString());
                if (isSent){//短信已发送,更新发送状态
                    System.out.println("手机"+reg.getPhone()+"的挂号提醒已发送。");
                    reg.setHassent(1);
                    Boolean rst = registrationService.updateRegistration(reg);
                    if (rst){
                        System.out.println("手机"+reg.getPhone()+"的挂号提醒已更新。");
                    }else {
                        sb.delete(0,sb.length());
                        sb.append("手机号为"+reg.getPhone()+"的挂号提醒已发送,但数据更新失败,请通过后台即时处理!");
                        System.out.println(sb.toString());
                    }
                }
            }
        }
    }
}
