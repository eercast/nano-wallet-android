package cc.cellcoin.cellcoin.network.model.response;

import com.google.gson.annotations.SerializedName;

import cc.cellcoin.cellcoin.network.model.BaseResponse;

/**
 * Error response from service
 */

public class AccountCheckResponse extends BaseResponse {
    @SerializedName("ready")
    private Boolean ready;

    public AccountCheckResponse() {
    }

    public AccountCheckResponse(Boolean ready) {
        this.ready = ready;
    }

    public Boolean getReady() {
        return ready;
    }

    public void setReady(Boolean ready) {
        this.ready = ready;
    }

    @Override
    public String toString() {
        return "AccountCheckResponse{" +
                "ready=" + ready +
                '}';
    }
}
