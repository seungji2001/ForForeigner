
package Dto.LocalQADto.ResponseDto;

import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Example {

    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("return_object")
    @Expose
    private ReturnObject returnObject;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public ReturnObject getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(ReturnObject returnObject) {
        this.returnObject = returnObject;
    }

}
