package com.example.prAnalyzerApplication.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GitRepository {
    @JsonProperty("full_name")
    private String fullName;
}
