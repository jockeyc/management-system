package demo.demo.model;

import java.util.List;

public class failsucess {
    List<SelectInfo> failure;
    List <SelectInfo> success;

    public failsucess(List<SelectInfo> failure,List<SelectInfo>success){
        this.failure=failure;
        this.success=success;
    }
    public List<SelectInfo> getFailure() {
        return failure;
    }

    public void setFailure(List<SelectInfo> failure) {
        this.failure = failure;
    }

    public List<SelectInfo> getSuccess() {
        return success;
    }

    public void setSuccess(List<SelectInfo> success) {
        this.success = success;
    }


}
