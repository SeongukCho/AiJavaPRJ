package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NlpDTO {
    private String text;
    private String result;
    private List<String> nouns;
}
