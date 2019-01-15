package bean.result;

public class OrgaChooseResBean {
    private int status;
    private String info;
    private int o_ids[];

    public String getInfo() {
        return info;
    }

    public int getStatus() {
        return status;
    }

    public int[] getO_ids() {
        return o_ids;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setO_ids(int[] o_ids) {
        this.o_ids = o_ids;
    }
}
