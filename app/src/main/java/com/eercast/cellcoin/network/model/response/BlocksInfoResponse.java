package com.eercast.cellcoin.network.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

import com.eercast.cellcoin.network.model.BaseResponse;

public class BlocksInfoResponse extends BaseResponse {
    @SerializedName("blocks")
    private HashMap<String, BlockInfoItem> blocks;

    public BlocksInfoResponse() {
    }

    public HashMap<String, BlockInfoItem> getBlocks() {
        return blocks;
    }

    public void setBlocks(HashMap<String, BlockInfoItem> blocks) {
        this.blocks = blocks;
    }
}
