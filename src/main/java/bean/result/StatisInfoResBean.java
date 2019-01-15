package bean.result;

import java.util.List;

public class StatisInfoResBean {

    private int status;
    private String info;
    private List<String> orgaNames;
    private  List<Integer> orgaMemCount;

    private int maleNum;
    private int formaleNum;


    public String getInfo() {
        return info;
    }

    public int getStatus() {
        return status;
    }

    public int getMaleNum() {
        return maleNum;
    }

    public int getFormaleNum() {
        return formaleNum;
    }

    public List<Integer> getOrgaMemCount() {
        return orgaMemCount;
    }

    public List<String> getOrgaNames() {
        return orgaNames;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setFormaleNum(int formaleNum) {
        this.formaleNum = formaleNum;
    }

    public void setMaleNum(int maleNum) {
        this.maleNum = maleNum;
    }

    public void setOrgaMemCount(List<Integer> orgaMemCount) {
        this.orgaMemCount = orgaMemCount;
    }

    public void setOrgaNames(List<String> orgaNames) {
        this.orgaNames = orgaNames;
    }

}
