package cc.cellcoin.cellcoin.network.model.response;

import com.google.gson.annotations.SerializedName;

import cc.cellcoin.cellcoin.network.model.BaseResponse;

/**
 * Error response from service
 */

public class WarningResponse extends BaseResponse {
    @SerializedName("warning")
    private String warning;

    public WarningResponse() {
    }

    public WarningResponse(String warning) {
        this.warning = warning;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }
}
