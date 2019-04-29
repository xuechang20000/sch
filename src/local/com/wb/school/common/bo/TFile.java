package com.wb.school.common.bo;

import com.wb.jdbcutils.annos.Column;
import com.wb.jdbcutils.annos.Sequence;
import com.wb.jdbcutils.annos.Table;

import java.util.Date;

@Table(name = "t_file")
public class TFile {

    private Long fileid;//	int	11	0	0	0	0	0	0		0					-1	0
    private String userid;//	int	11	0	-1	0	0	0	0		0					0	0
    private String filepath	;//varchar	255	0	-1	0	0	0	0		0		utf8mb4	utf8mb4_general_ci		0	0
    private Date ctime;//	datetime	0	0	-1	0	0	0	0		-1					0	0
    private String busitype	;//varchar	255	0	-1	0	0	0	0		0		utf8mb4	utf8mb4_general_ci		0	0
    private String iscomplete;
    @Column(name="fileid",id=true)
    @Sequence(name="INCREMENT")
    public Long getFileid() {
        return fileid;
    }

    public void setFileid(Long fileid) {
        this.fileid = fileid;
    }

    @Column(name="userid")
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Column(name="filepath")
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Column(name="ctime")
    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Column(name="busitype")
    public String getBusitype() {
        return busitype;
    }

    public void setBusitype(String busitype) {
        this.busitype = busitype;
    }
    @Column(name="iscomplete")
    public String getIscomplete() {
        return iscomplete;
    }

    public void setIscomplete(String iscomplete) {
        this.iscomplete = iscomplete;
    }
}
