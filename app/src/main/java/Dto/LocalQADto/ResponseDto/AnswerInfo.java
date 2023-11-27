
package Dto.LocalQADto.ResponseDto;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class AnswerInfo {

    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("clause")
    @Expose
    private String clause;
    @SerializedName("confidence")
    @Expose
    private Double confidence;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getClause() {
        return clause;
    }

    public void setClause(String clause) {
        this.clause = clause;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

}
