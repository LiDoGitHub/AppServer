package com.gjyl.appserver.serviceimpl;

import com.gjyl.appserver.dao.DocArrangementMapper;
import com.gjyl.appserver.dao.DoctorMapper;
import com.gjyl.appserver.pojo.DocArrangement;
import com.gjyl.appserver.pojo.Doctor;
import com.gjyl.appserver.service.DocArrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by LiD on 2017/4/27.
 */
@Service("docArrService")
public class DocArrServiceImpl implements DocArrService {

    @Resource
    private DocArrangementMapper dao;
    @Resource
    protected DoctorMapper docDao;

    public DocArrangement getDocArrByDocId(String docid) {
        DocArrangement arrangement = dao.getArrangeByDocId(docid);
        Doctor doctor = docDao.getDrInfo(docid);
        if (arrangement!=null&&!arrangement.getDocid().equals("")) {
            arrangement.setDoctor(doctor);
        }else {
            arrangement=new DocArrangement();
            arrangement.setDoctor(doctor);
        }
        return arrangement;
    }

    public DocArrangement getArrById(String arrid) {
        return dao.selectByPrimaryKey(arrid);
    }

    public Boolean addDocArrangement(DocArrangement arrangement) {
        int rst=dao.insertSelective(arrangement);
        if (rst>0)
            return true;
        return false;
    }

    public Boolean updateDocArrangement(DocArrangement arr) {
        int rst=dao.updateByPrimaryKeySelective(arr);
        if (rst>0)
            return true;
        return false;
    }
}
