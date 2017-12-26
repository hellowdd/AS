package com.bocom.dto.resp;


public class AppStoreResponse
{
    private boolean success;
    private String message;
    private AppStoreGetAppRespDto data;
    public boolean isSuccess()
    {
        return success;
    }
    public void setSuccess(boolean success)
    {
        this.success = success;
    }
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    public AppStoreGetAppRespDto getData()
    {
        return data;
    }
    public void setData(AppStoreGetAppRespDto data)
    {
        this.data = data;
    }
    public AppStoreResponse(boolean success, String message, AppStoreGetAppRespDto data)
    {
        super();
        this.success = success;
        this.message = message;
        this.data = data;
    }
    public AppStoreResponse()
    {
        super();
    }
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("AppStoreResponse [success=");
        builder.append(success);
        builder.append(", message=");
        builder.append(message);
        builder.append(", data=");
        builder.append(data);
        builder.append("]");
        return builder.toString();
    }
    
    
}
