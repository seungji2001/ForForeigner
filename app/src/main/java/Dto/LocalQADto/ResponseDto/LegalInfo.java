
package Dto.LocalQADto.ResponseDto;

import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class LegalInfo {

    @SerializedName("AnswerInfo")
    @Expose
    private List<AnswerInfo> answerInfo;
    @SerializedName("RelatedQs")
    @Expose
    private List<Object> relatedQs;

    public List<AnswerInfo> getAnswerInfo() {
        return answerInfo;
    }

    public void setAnswerInfo(List<AnswerInfo> answerInfo) {
        this.answerInfo = answerInfo;
    }

    public List<Object> getRelatedQs() {
        return relatedQs;
    }

    public void setRelatedQs(List<Object> relatedQs) {
        this.relatedQs = relatedQs;
    }

}
