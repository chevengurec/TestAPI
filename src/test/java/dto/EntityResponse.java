package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityResponse {
    private Long id;
    private Addition addition;
    private List<Integer> importantNumbers;
    private String title;
    private Boolean verified;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Addition {
        private Long id;
        private String additionalInfo;
        private Integer additionalNumber;
    }
}